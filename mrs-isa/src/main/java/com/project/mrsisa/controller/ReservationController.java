package com.project.mrsisa.controller;

import com.project.mrsisa.domain.OfferType;
import com.project.mrsisa.domain.Reservation;
import com.project.mrsisa.dto.HistoryFutureReservationOwnerDTO;
import com.project.mrsisa.dto.HistoryPastReservationOwnerDTO;
import com.project.mrsisa.service.CottageService;
import com.project.mrsisa.service.ImageService;
import com.project.mrsisa.service.ReservationService;
import com.project.mrsisa.service.ShipService;
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
    private ShipService shipService;
    @Autowired
    private ImageService imageService;
    @RequestMapping(value = "/reservations/future/{id}")
    @PreAuthorize("hasRole('COTTAGE_OWNER') or hasRole('SHIP_OWNER')")
    public ResponseEntity<List<HistoryFutureReservationOwnerDTO>> getAllFutureReservationForOffer(@PathVariable Long id){

        List<HistoryFutureReservationOwnerDTO> reservationsDTO = new ArrayList<HistoryFutureReservationOwnerDTO>();
        List<Reservation> futureReservations = reservationService.getFutureHistoryReservation(id);
        for(Reservation r : futureReservations){
            if(r.getOfferType() == OfferType.COTTAGE){
                r.setOffer(cottageService.findOne(id));
            }
            else if(r.getOfferType() == OfferType.ADVENTURE){
                //poziv adventureService.findOne
            }
            else { //ship
                r.setOffer(shipService.findOne(id));
            }
            r.getOffer().setImages(imageService.findAllByOfferId(id));
            reservationsDTO.add(new HistoryFutureReservationOwnerDTO(r));
        }
        return ResponseEntity.ok(reservationsDTO);
    }

    @RequestMapping(value = "/reservations/past/{id}")
    @PreAuthorize("hasRole('COTTAGE_OWNER') or hasRole('SHIP_OWNER')")
    public ResponseEntity<List<HistoryPastReservationOwnerDTO>> getAllPastReservationForOffer(@PathVariable Long id){

        List<HistoryPastReservationOwnerDTO> reservationsDTO = new ArrayList<HistoryPastReservationOwnerDTO>();
        List<Reservation> pastReservations = reservationService.getPastHistoryReservation(id);
        for(Reservation r : pastReservations){
            if(r.getOfferType() == OfferType.COTTAGE){
                r.setOffer(cottageService.findOne(id));
            }
            else if(r.getOfferType() == OfferType.ADVENTURE){
                //poziv adventureService.findOne
            }
            else { //ship
                r.setOffer(shipService.findOne(id));
            }
            r.getOffer().setImages(imageService.findAllByOfferId(id));
            reservationsDTO.add(new HistoryPastReservationOwnerDTO(r));
        }
        return ResponseEntity.ok(reservationsDTO);
    }
}
