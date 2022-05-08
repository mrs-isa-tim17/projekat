package com.project.mrsisa.controller;

import com.project.mrsisa.domain.*;
import com.project.mrsisa.dto.ClientProfileResponseDTO;
import com.project.mrsisa.dto.client.CottageReviewedDTO;
import com.project.mrsisa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/cottage", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<HttpStatus> cottageReviewed(@RequestBody CottageReviewedDTO cottageReviewedDTO){
        ExperienceReview er = new ExperienceReview();
        er.setStatus(ProcessingStatus.UNPROCESSED);

        Offer o = null;
        if (cottageReviewedDTO.getOfferType() == OfferType.COTTAGE.getValue())
            o = cottageService.findOne(cottageReviewedDTO.getCottageId());
        else if (cottageReviewedDTO.getOfferType() == OfferType.SHIP.getValue())
            o = shipService.findOne(cottageReviewedDTO.getCottageId());
        else if (cottageReviewedDTO.getOfferType() == OfferType.ADVENTURE.getValue())
            o = adventureService.findOneById(cottageReviewedDTO.getCottageId());
        er.setOffer(o);
        er.setOfferType(OfferType.valueOf(cottageReviewedDTO.getOfferType()));

        Client cl = clientService.findOne(cottageReviewedDTO.getClientID());
        er.setClient(cl);
        if (cottageReviewedDTO.getText() == "") er.setText(null);
        else er.setText(cottageReviewedDTO.getText());
        if (cottageReviewedDTO.getRating() == "") er.setRate(-1);
        else er.setRate(Integer.parseInt(cottageReviewedDTO.getRating()));
        experienceReviewService.save(er);
        Reservation r = reservationService.getReservationById(cottageReviewedDTO.getReservationId());
        r.setReviewed(true);
        reservationService.save(r);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
