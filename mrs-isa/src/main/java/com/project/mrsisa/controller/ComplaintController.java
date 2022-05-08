package com.project.mrsisa.controller;

import com.project.mrsisa.domain.*;
import com.project.mrsisa.dto.client.ComplaintOnOfferDTO;
import com.project.mrsisa.dto.client.OfferReviewedDTO;
import com.project.mrsisa.service.*;
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

    @PostMapping(value = "/offer", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<HttpStatus> cottageReviewed(@RequestBody ComplaintOnOfferDTO complaintOnOfferDTO){
        Complaint complaint = new Complaint();
        complaint.setStatus(ProcessingStatus.UNPROCESSED);
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
}
