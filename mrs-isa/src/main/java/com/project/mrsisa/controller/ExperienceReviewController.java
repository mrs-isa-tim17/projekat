package com.project.mrsisa.controller;

import com.project.mrsisa.domain.Client;
import com.project.mrsisa.domain.Cottage;
import com.project.mrsisa.domain.ExperienceReview;
import com.project.mrsisa.domain.ProcessingStatus;
import com.project.mrsisa.dto.ClientProfileResponseDTO;
import com.project.mrsisa.dto.client.CottageReviewedDTO;
import com.project.mrsisa.service.ClientService;
import com.project.mrsisa.service.CottageService;
import com.project.mrsisa.service.ExperienceReviewService;
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

    @PostMapping(value = "/cottage", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<HttpStatus> updateClient(@RequestBody CottageReviewedDTO cottageReviewedDTO){
        ExperienceReview er = new ExperienceReview();
        er.setStatus(ProcessingStatus.UNPROCESSED);
        Cottage c = cottageService.findOne(cottageReviewedDTO.getCottageId());
        er.setOffer(c);
        Client cl = clientService.findOne(cottageReviewedDTO.getClientID());
        er.setClient(cl);
        if (cottageReviewedDTO.getText() == "") er.setText(null);
        else er.setText(cottageReviewedDTO.getText());
        if (cottageReviewedDTO.getRating() == "") er.setRate(-1);
        else er.setRate(Integer.parseInt(cottageReviewedDTO.getRating()));
        experienceReviewService.save(er);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
