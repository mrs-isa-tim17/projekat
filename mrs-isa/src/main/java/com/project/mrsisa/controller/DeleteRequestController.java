package com.project.mrsisa.controller;

import com.project.mrsisa.domain.*;
import com.project.mrsisa.dto.AdminDeletingRequestDTO;
import com.project.mrsisa.dto.DeleteRequestDTO;
import com.project.mrsisa.dto.TextDTO;
import com.project.mrsisa.service.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class DeleteRequestController {

    @Autowired
    private UserService userService;
    @Autowired
    private DeleteRequestService deleteRequestService;
    @Autowired
    private RoleService roleService;
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private CottageService cottageService;
	@Autowired
	private CottageOwnerService cottageOwnerService;
	@Autowired
	private ShipOwnerService shipOwnerService;
	@Autowired
	private ShipService shipService;
	@Autowired
	private FishingInstructorService fishingInstructorService;
	@Autowired
	private AdventureService adventureService;
    
    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('CLIENT') or hasRole('FISHINSTRUCTOR') or hasRole('SHIP_OWNER') or hasRole('COTTAGE_OWNER')")
    public ResponseEntity<Boolean> deleteAccountRequest(@PathVariable("id") long id, @RequestBody DeleteRequestDTO deleteRequestDTO) {
        User u = userService.findById(id);
		if (u.getRoleId() == 1){
			if (reservationService.getFutureActiveReservationsForClient(u.getId()).size() > 0){
				return ResponseEntity.ok(false);
			}
		}
		else if (u.getRoleId() == 3){ // co
			CottageOwner owner = cottageOwnerService.findOne(u.getId());
			List<Cottage> cottages = cottageService.getCottagesByOwner(owner);
			for (Cottage c : cottages){
				if(reservationService.getFutureHistoryReservation(c.getId()).size()>0){
					return ResponseEntity.ok(false);
				}
			}
		}
		else if (u.getRoleId() == 4){ // ship
			ShipOwner shipOwner = shipOwnerService.findOne(u.getId());
			List<Ship> ships = shipService.getShipsByOwner(shipOwner);
			for (Ship c : ships){
				if(reservationService.getFutureHistoryReservation(c.getId()).size()>0){
					return ResponseEntity.ok(false);
				}
			}
		}
		else if (u.getRoleId() == 5){ // fi
			FishingInstructor instructor = fishingInstructorService.findOne(u.getId());
			List<Adventure> adventures = adventureService.getAdventuresByOwner(instructor);
			for (Adventure c : adventures){
				if(reservationService.getFutureHistoryReservation(c.getId()).size()>0){
					return ResponseEntity.ok(false);
				}
			}
		}

        DeleteRequest old = deleteRequestService.findOneByUser(id);
        if (old != null)
            return ResponseEntity.ok(false);
        DeleteRequest dr = new DeleteRequest();
        dr.setUserRef(u);
        dr.setStatus(ProcessingStatus.UNPROCESSED);
        String reason = deleteRequestDTO.getReason();
        if (reason == "")
            return ResponseEntity.ok(false);
        System.out.println(reason);
        dr.setText(reason);//deleting = from the end of the string

        deleteRequestService.save(dr);
        return ResponseEntity.ok(true);
    }
    
    @GetMapping("/delete/unprocessed/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AdminDeletingRequestDTO>> getUnprocessedDeleteRequests(){
    	List<DeleteRequest> deleteREquests = deleteRequestService.getUnprocessedDeleteRequest();
    	
    	List<AdminDeletingRequestDTO> deleteRequestsDTO = new ArrayList<AdminDeletingRequestDTO>();
    	
    	User user = null;
    	for(DeleteRequest deleteRequest : deleteREquests) {
    		user = userService.findById(deleteRequest.getUserRef().getId());
    		Role role = roleService.findOneById(user.getRoleId());
    		System.out.println(role.getName());
    		
    		AdminDeletingRequestDTO requestDTO = new AdminDeletingRequestDTO(deleteRequest.getId(), user.getName(), user.getSurname(), 
    				deleteRequest.getText(), user.getUserType().toString(), role.getName());
    		deleteRequestsDTO.add(requestDTO);
    	}
    	return  new ResponseEntity<List<AdminDeletingRequestDTO>>(deleteRequestsDTO, HttpStatus.OK);
    }
    
    @PostMapping(value = "/delete/reject/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TextDTO> rejectDeleteRequest(@PathVariable Long id, @RequestBody TextDTO textDTO){
		DeleteRequest deleteRequest = deleteRequestService.findOneById(id);

		if (deleteRequest == null) {
			TextDTO t = new TextDTO("Ne postoji zahtev za brisanje naloga.");
			t.setSuccessfull(false);
			return new ResponseEntity<>(t , HttpStatus.BAD_REQUEST);
		}
		
		User u = userService.findById(deleteRequest.getUserRef().getId());
		deleteRequest.setStatus(ProcessingStatus.REJECTED);
		
		try {
			deleteRequestService.save(deleteRequest);
			deleteRequestService.sendMailsAboutDeleteRequest(u.getEmail(), textDTO.getText(), false);
		}catch (ObjectOptimisticLockingFailureException e) {
			// TODO: handle exception
			TextDTO t = new TextDTO("Drugi administrator je ve?? odgovorio na ovaj zahtev za brisanje naloga.");
			t.setSuccessfull(false);
			return new ResponseEntity<>(t, HttpStatus.OK);
		}
    	
		TextDTO t = new TextDTO("Odbili ste zahtev za brisanje naloga.");
		t.setSuccessfull(true);
    	return new ResponseEntity<>(t, HttpStatus.OK);
    }
    
    @PostMapping(value = "/delete/accept/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TextDTO> acceptDeleteRequest(@PathVariable Long id, @RequestBody TextDTO textDTO){
		DeleteRequest deleteRequest = deleteRequestService.findOneById(id);

		if (deleteRequest == null) {
			TextDTO t = new TextDTO("Ne postoji zahtev za brisanje naloga.");
			t.setSuccessfull(false);
			return new ResponseEntity<>(t, HttpStatus.BAD_REQUEST);
		}
		
		User u = userService.findById(deleteRequest.getUserRef().getId());
		u.setDeleted(true);
		
		
		deleteRequest.setStatus(ProcessingStatus.APPROVED);
		try {		
			deleteRequestService.save(deleteRequest);
			userService.save(u);
			deleteRequestService.sendMailsAboutDeleteRequest(u.getEmail(), textDTO.getText(), true);
		}catch (ObjectOptimisticLockingFailureException e) {
			TextDTO t = new TextDTO("Drugi administrator je ve?? odgovorio na ovaj zahtev za brisanje naloga.");
			t.setSuccessfull(false);
			return new ResponseEntity<>(t, HttpStatus.OK);
		}
		TextDTO t = new TextDTO("Prihvatili ste zahtev za brisanje naloga.");
		t.setSuccessfull(true);
    	return new ResponseEntity<>(t, HttpStatus.OK);
    }
    
    
    
}