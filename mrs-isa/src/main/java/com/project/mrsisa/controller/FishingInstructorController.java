package com.project.mrsisa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.mrsisa.domain.FishingInstructor;
import com.project.mrsisa.domain.LoyaltyScale;
import com.project.mrsisa.dto.owner.CottageOwnerProfileResponseDTO;
import com.project.mrsisa.service.FishingInstructorService;
import com.project.mrsisa.service.LoyaltyScaleService;
import com.project.mrsisa.service.ReservationService;
import com.project.mrsisa.util.TokenUtils;


@RestController
@RequestMapping(value = "/fishing/instructor", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class FishingInstructorController {
	@Autowired
    public ReservationService reservationService;
	@Autowired
	private FishingInstructorService fishingInstructorService;
	@Autowired
	private LoyaltyScaleService loyaltyScaleService;
    @Autowired
    private TokenUtils tokenUtils;

	@GetMapping("/profile/{id}")
    @PreAuthorize("hasRole('FISHINSTRUCTOR')")
    public ResponseEntity<CottageOwnerProfileResponseDTO> getCottageOwner(@PathVariable Long id){
        
        FishingInstructor fishingInstructor = null;
       try {
    	   fishingInstructor = fishingInstructorService.findOne(id);
        if (fishingInstructor == null) {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
       
       }catch (Exception e) {
		e.printStackTrace();
       }
        
        LoyaltyScale ls = loyaltyScaleService.loyaltyScalesGreaterMinimumTrashhold(fishingInstructor.getLoyaltyPoints());
        int discount;
        if (ls == null) {
        	discount = 0;
        }else {
        	discount = ls.getDiscount();
        }
 
        return new ResponseEntity<CottageOwnerProfileResponseDTO>(new CottageOwnerProfileResponseDTO(fishingInstructor, discount), HttpStatus.OK);
    }
	
	@PostMapping(value = "update/profile/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @PreAuthorize("hasRole('FISHINSTRUCTOR')")
    public ResponseEntity<CottageOwnerProfileResponseDTO> updateCottageOwner(@PathVariable Long id, @RequestBody CottageOwnerProfileResponseDTO clientDTO){
        
		FishingInstructor fishingInstructor = null;
       try {
    	   fishingInstructor = fishingInstructorService.findOne(id);
        if (fishingInstructor == null) {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        fishingInstructor.setName(clientDTO.getName());
        
        fishingInstructor.setSurname(clientDTO.getSurname());
        
        fishingInstructor.setPhoneNumber(clientDTO.getPhoneNumber());
        
        if (clientDTO.getPassword() != "") {
        	fishingInstructor.setPassword(clientDTO.getPassword());
        }

        fishingInstructor.getAddress().setLatitude(clientDTO.getLatitude());
        fishingInstructor.getAddress().setLongitude(clientDTO.getLongitude());

        fishingInstructorService.save(fishingInstructor);
        
       }catch (Exception e) {
		e.printStackTrace();
       }

       return new ResponseEntity<CottageOwnerProfileResponseDTO>(new CottageOwnerProfileResponseDTO(fishingInstructor, 5), HttpStatus.OK);
    }




}

