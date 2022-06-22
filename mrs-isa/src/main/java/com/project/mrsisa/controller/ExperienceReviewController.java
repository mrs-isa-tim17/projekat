package com.project.mrsisa.controller;

import com.project.mrsisa.domain.*;
import com.project.mrsisa.dto.ExperienceReviewAdminDTO;
import com.project.mrsisa.dto.ExperienceReviewDTO;
import com.project.mrsisa.dto.client.OfferReviewedDTO;
import com.project.mrsisa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/review", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class ExperienceReviewController {

    @Autowired
    private ExperienceReviewService experienceReviewService;
    @Autowired
    private CottageService cottageService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ShipService shipService;
    @Autowired
    private AdventureService adventureService;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/offer", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<HttpStatus> cottageReviewed(@RequestBody OfferReviewedDTO offerReviewedDTO){
        ExperienceReview er = new ExperienceReview();
        er.setStatus(ProcessingStatus.UNPROCESSED);

        Offer o = null;
        if (offerReviewedDTO.getOfferType() == OfferType.COTTAGE.getValue())
            o = cottageService.findOne(offerReviewedDTO.getCottageId());
        else if (offerReviewedDTO.getOfferType() == OfferType.SHIP.getValue())
            o = shipService.findOne(offerReviewedDTO.getCottageId());
        else if (offerReviewedDTO.getOfferType() == OfferType.ADVENTURE.getValue())
            o = adventureService.findOneById(offerReviewedDTO.getCottageId());
        er.setOffer(o);
        er.setOfferType(OfferType.valueOf(offerReviewedDTO.getOfferType()));

        Client cl = clientService.findOne(offerReviewedDTO.getClientID());
        er.setClient(cl);
        if (offerReviewedDTO.getText() == "") er.setText(null);
        else er.setText(offerReviewedDTO.getText());
        if (offerReviewedDTO.getRating() == "") er.setRate(-1);
        else er.setRate(Integer.parseInt(offerReviewedDTO.getRating()));
        experienceReviewService.save(er);
        Reservation r = reservationService.getReservationById(offerReviewedDTO.getReservationId());
        r.setReviewed(true);
        reservationService.save(r);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value="/all/{id}")
    @PreAuthorize("hasRole('COTTAGE_OWNER')  or hasRole('SHIP_OWNER') or hasRole('FISHING_INSTRUCTOR')")
    public ResponseEntity<List<ExperienceReviewDTO>>getExperienceReviews(@PathVariable Long id){
        List<ExperienceReviewDTO> dto = new ArrayList<>();
        List<ExperienceReview> reviews = experienceReviewService.findAllApprovedByOfferId(id);
        for(ExperienceReview er : reviews){
            Client client = clientService.findOne(er.getClient().getId());
            dto.add(new ExperienceReviewDTO(er,client));
        }

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value="/rate/{id}")
    @PreAuthorize("hasRole('COTTAGE_OWNER') or hasRole('SHIP_OWNER') or hasRole('FISHINSTRUCTOR')")
    public ResponseEntity<Double>getRating(@PathVariable Long id){
        double rating = 0;
        List<ExperienceReview> reviews = experienceReviewService.findAllApprovedByOfferId(id);
        for(ExperienceReview er : reviews){
            rating += er.getRate();
        }
        rating = rating / reviews.size();
        if(reviews.size() == 0) {
        	return new ResponseEntity<>(0.0, HttpStatus.OK);
        }
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }
    
    
    @GetMapping(value="/unprocessed")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ExperienceReviewAdminDTO>> getUnprocessedExperienceReview(){
        List<ExperienceReview> reviews = experienceReviewService.findAllUnprocessedExperienceReview();

        List<ExperienceReviewAdminDTO> reviewsDTO = new ArrayList<ExperienceReviewAdminDTO>();
        
        User owner=null;
        Ship ship;
        Cottage cottage;
        Adventure adventure;
        Client client;
        for(ExperienceReview ex : reviews) {
          
             client = clientService.findOne(ex.getClient().getId());
             
             System.out.println("EVOOO " + ex.getOffer().getId() + "    " + ex.getOfferType() );
             
    		if (ex.getOfferType() == OfferType.COTTAGE) {
    			cottage = (Cottage) cottageService.findOne(ex.getOffer().getId());
    			owner = userService.findById(cottage.getOwner().getId());
    		}else if(ex.getOfferType() == OfferType.SHIP){
    			ship = (Ship)shipService.findOne(ex.getOffer().getId());
    			owner = userService.findById(ship.getOwner().getId());
    		}else if(ex.getOfferType()==OfferType.ADVENTURE) {
    			adventure = (Adventure)adventureService.findOneById(ex.getOffer().getId());
    			owner = userService.findById(adventure.getOwner().getId());
    		}
    		
    		reviewsDTO.add(new ExperienceReviewAdminDTO(ex.getId(), client.getName(), client.getSurname(), owner.getName(),
    				owner.getSurname(), ex.getRate(), ex.getOfferType().toString(), ex.getText(), client.getEmail(), owner.getEmail()));
    		
        }
        return new ResponseEntity<List<ExperienceReviewAdminDTO>>(reviewsDTO, HttpStatus.OK);
    	
    }
    
    
    @GetMapping(value="/accept/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> acceptExperienceReview(@PathVariable Long id){
		
		ExperienceReview experience = experienceReviewService.findOneById(id);	
		
		if (experience == null) {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}

		System.out.println("ID: " + experience.getId());
		System.out.println("TEXT: " +experience.getText());
		
		Client client = clientService.findOne(experience.getClient().getId());
		User owner = null;
		if (experience.getOfferType() == OfferType.COTTAGE) {
			Cottage cottage = (Cottage) cottageService.findOne(experience.getOffer().getId());
			owner = userService.findById(cottage.getOwner().getId());
		}else if(experience.getOfferType() == OfferType.SHIP){
			Ship ship = (Ship)shipService.findOne(experience.getOffer().getId());
			owner = userService.findById(ship.getOwner().getId());
		}else if(experience.getOfferType()==OfferType.ADVENTURE) {
			Adventure adventure = (Adventure)adventureService.findOneById(experience.getOffer().getId());
			owner = userService.findById(adventure.getOwner().getId());
		}
		
		
		experience.setStatus(ProcessingStatus.APPROVED);
		
		experience = experienceReviewService.save(experience, owner.getEmail(), client);
		
		return new ResponseEntity<>(true, HttpStatus.OK);
    }
    
    @GetMapping(value="/reject/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> rejectExperienceReview(@PathVariable Long id){
		
		ExperienceReview experience = experienceReviewService.findOneById(id);	
		
		if (experience == null) {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		
		experience.setStatus(ProcessingStatus.REJECTED);
		
		experience = experienceReviewService.save(experience);
		
		return new ResponseEntity<>(true, HttpStatus.OK);
    }
    
    
    
}
