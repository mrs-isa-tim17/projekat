package com.project.mrsisa.controller;

import com.project.mrsisa.domain.*;
import com.project.mrsisa.dto.ComplaintDTO;
import com.project.mrsisa.dto.client.ComplaintOnOfferDTO;
import com.project.mrsisa.service.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/complaint", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class ComplaintController {


    @Autowired
    private ComplaintService complaintService;
    @Autowired
    private CottageService cottageService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private AdventureService adventureService;
    @Autowired
    private ShipService shipService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private UserService userService;
    

    @PostMapping(value = "/offer", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<HttpStatus> cottageReviewed(@RequestBody ComplaintOnOfferDTO complaintOnOfferDTO){
        Complaint complaint = new Complaint();
        complaint.setStatus(ComplaintStatus.UNPROCESSED);
        System.out.println("offer id" + complaintOnOfferDTO.getOfferId());
        Offer o = null;
        if (complaintOnOfferDTO.getOfferType() == OfferType.COTTAGE.getValue())
            o = cottageService.findOne(complaintOnOfferDTO.getOfferId());
        else if (complaintOnOfferDTO.getOfferType() == OfferType.SHIP.getValue())
            o = shipService.findOne(complaintOnOfferDTO.getOfferId());
        else if (complaintOnOfferDTO.getOfferType() == OfferType.ADVENTURE.getValue())
            o = adventureService.findOneById(complaintOnOfferDTO.getOfferId());
        complaint.setOffer(o);
        complaint.setOfferType(OfferType.valueOf(complaintOnOfferDTO.getOfferType()));

        Client cl = clientService.findOne(complaintOnOfferDTO.getClientID());
        complaint.setClient(cl);
        if (complaintOnOfferDTO.getText() == "") complaint.setText(null);
        else complaint.setText(complaintOnOfferDTO.getText());
        complaintService.save(complaint);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
    @GetMapping(value="/unprocessed")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ComplaintDTO>> getUnprocessedComplaints(){
    	List<Complaint> complaints =  complaintService.findUnprocessedComplaint();
    	List<ComplaintDTO> complaintsDTO= new ArrayList<ComplaintDTO>();
    	
    	for(Complaint c : complaints) {
    		Client client = clientService.findOne(c.getClient().getId());
    		Reservation reservation = reservationService.getReservationById(c.getReservation().getId());
    	
            Cottage cottage;
            Ship ship;
            Adventure adventure;
            User owner = null;
            String offerName = null;
			if (c.getOfferType() == OfferType.COTTAGE) {
				System.out.println("VIKENDICA: " + c.getOffer().getId());
				
                cottage = cottageService.findOne(c.getOffer().getId());
                offerName = cottage.getName();
            	owner =  userService.findById(cottage.getId());
			}else if (c.getOfferType() == OfferType.SHIP) {
                ship = (Ship) shipService.findOne(c.getOffer().getId());
                offerName = ship.getName();
				owner =  userService.findById(ship.getId());
			}
            else if (c.getOfferType() == OfferType.ADVENTURE) {
                adventure = adventureService.findOneById(c.getOffer().getId());
                offerName = adventure.getName();
    			owner = userService.findById(adventure.getOwner().getId());
            }
			
			ComplaintDTO complaintDTO = new ComplaintDTO(c.getId(), c.getText(), client.getName(), client.getSurname(), client.getEmail(),
					owner.getName(), owner.getSurname(), owner.getEmail(),offerName, reservation.getStartDate().toString(), reservation.getEndDate().toString());
			complaintsDTO.add(complaintDTO);
			
    	}
		return new ResponseEntity<>(complaintsDTO, HttpStatus.OK);
    }
    
    
    @PostMapping(value="/answer/{id}", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> answerComplaint(@PathVariable Long id, @RequestBody String textAnswer ) {

		System.out.println("@reject ");
		
		Complaint complaint = complaintService.findOne(id);
	
		
		if (complaint == null) {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}

		System.out.println("ID: " + complaint.getId());
		System.out.println("TEXT: " +textAnswer);
		
		Client client = (Client) userService.findById(complaint.getClient().getId());
		Cottage cottage;
        Ship ship;
        Adventure adventure;
        User owner = null;
		if (complaint.getOfferType() == OfferType.COTTAGE) {
			System.out.println("VIKENDICA: " + complaint.getOffer().getId());
			
            cottage = cottageService.findOne(complaint.getOffer().getId());
        	owner =  userService.findById(cottage.getId());
		}else if (complaint.getOfferType() == OfferType.SHIP) {
            ship = (Ship) shipService.findOne(complaint.getOffer().getId());
			owner =  userService.findById(ship.getId());
		}
        else if (complaint.getOfferType() == OfferType.ADVENTURE) {
            adventure = adventureService.findOneById(complaint.getOffer().getId());
			owner = userService.findById(adventure.getOwner().getId());
        }
		complaint.setStatus(ComplaintStatus.ANSWERED);
		
		complaint = complaintService.save(complaint, client.getEmail(), client.getName(), client.getSurname(), owner.getEmail(), textAnswer);
		
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
    
}
