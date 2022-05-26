package com.project.mrsisa.controller;

import com.project.mrsisa.domain.Adventure;
import com.project.mrsisa.dto.client.ReservationPriceRequestDTO;
import com.project.mrsisa.dto.simple_user.AdventureForListViewDTO;
import com.project.mrsisa.dto.simple_user.SearchParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping(value = "/offer", produces = MediaType.APPLICATION_JSON_VALUE)
public class OfferController {



    @PostMapping(value = "/site/price", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getOfferPrice(@RequestBody ReservationPriceRequestDTO req){
        double price = 0;
        try {
            if (req.getType().equals("cottage")) {
                long days = ChronoUnit.DAYS.between(req.getFromDate().toLocalDate(), req.getUntilDate().toLocalDate());//req.getUntilDate() - req.getFromDate();
                price = days * req.getPrice();
            } else if (req.getType().equals("adventure")) {
                long minutes = ChronoUnit.MINUTES.between(req.getFromDate(), req.getUntilDate());//req.getUntilDate() - req.getFromDate();
                System.out.println(minutes);
                price = minutes / 60.0 * req.getPrice();
            } else if (req.getType().equals("ship")) {
                long minutes = ChronoUnit.MINUTES.between(req.getFromDate(), req.getUntilDate());//req.getUntilDate() - req.getFromDate();
                System.out.println(minutes);
                price = minutes / 60.0 * req.getPrice();
            }

            return ResponseEntity.ok(String.format("%.2f", price));
        }catch (Exception e){
            return ResponseEntity.ok("");
        }
    }
}
