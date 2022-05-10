package com.project.mrsisa.controller;

import com.project.mrsisa.domain.Reservation;
import com.project.mrsisa.dto.HistoryFutureReservationCottageOwnerDTO;
import com.project.mrsisa.dto.HistoryPastReservationCottageOwnerDTO;
import com.project.mrsisa.service.CottageService;
import com.project.mrsisa.service.ImageService;
import com.project.mrsisa.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/offer",  produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private CottageService cottageService;

    @Autowired
    private ImageService imageService;
    @RequestMapping(value = "/reservations/future/{id}")
    @PreAuthorize("hasRole('COTTAGE_OWNER')")
    public ResponseEntity<List<HistoryFutureReservationCottageOwnerDTO>> getAllFutureReservationForOffer(@PathVariable Long id){

        List<HistoryFutureReservationCottageOwnerDTO> reservationsDTO = new ArrayList<HistoryFutureReservationCottageOwnerDTO>();
        List<Reservation> futureReservations = reservationService.getFutureHistoryReservation(id);
        for(Reservation r : futureReservations){
            r.setOffer(cottageService.findOne(id));
            r.getOffer().setImages(imageService.findAllByOfferId(id));
            reservationsDTO.add(new HistoryFutureReservationCottageOwnerDTO(r));
        }
        return ResponseEntity.ok(reservationsDTO);
    }

    @RequestMapping(value = "/reservations/past/{id}")
    @PreAuthorize("hasRole('COTTAGE_OWNER')")
    public ResponseEntity<List<HistoryPastReservationCottageOwnerDTO>> getAllPastReservationForOffer(@PathVariable Long id){

        List<HistoryPastReservationCottageOwnerDTO> reservationsDTO = new ArrayList<HistoryPastReservationCottageOwnerDTO>();
        List<Reservation> pastReservations = reservationService.getPastHistoryReservation(id);
        for(Reservation r : pastReservations){
            r.setOffer(cottageService.findOne(id));
            r.getOffer().setImages(imageService.findAllByOfferId(id));
            reservationsDTO.add(new HistoryPastReservationCottageOwnerDTO(r));
        }
        return ResponseEntity.ok(reservationsDTO);
    }
}
