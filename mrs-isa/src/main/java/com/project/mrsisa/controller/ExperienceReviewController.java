package com.project.mrsisa.controller;

import com.project.mrsisa.domain.*;
import com.project.mrsisa.dto.ExperienceReviewDTO;
import com.project.mrsisa.dto.client.OfferReviewedDTO;
import com.project.mrsisa.dto.cottage.FindCottagesDTO;
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
    @PreAuthorize("hasRole('COTTAGE_OWNER')")
    public ResponseEntity<List<ExperienceReviewDTO>>getExperienceReviews(@PathVariable Long id){
        List<ExperienceReviewDTO> dto = new ArrayList<>();
        List<ExperienceReview> reviews = experienceReviewService.findAllByOfferId(id);
        for(ExperienceReview er : reviews){
            Client client = clientService.findOne(er.getClient().getId());
            dto.add(new ExperienceReviewDTO(er,client));
        }

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value="/rate/{id}")
    @PreAuthorize("hasRole('COTTAGE_OWNER') or hasRole('SHIP_OWNER') or hasRole('FISHING_INSTRUCTOR')")
    public ResponseEntity<Double>getRating(@PathVariable Long id){
        double rating = 0;
        List<ExperienceReview> reviews = experienceReviewService.findAllByOfferId(id);
        for(ExperienceReview er : reviews){
            rating += er.getRate();
        }
        rating = rating / reviews.size();
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }
}
