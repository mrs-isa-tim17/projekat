package com.project.mrsisa.controller;

import com.project.mrsisa.domain.Reservation;
import com.project.mrsisa.dto.client.OfferHistoryReservationDTO;
import com.project.mrsisa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping(value = "/reservation", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private CottageService cottageService;
    @Autowired
    private ShipService shipService;
    @Autowired
    private AdventureService adventureService;
    @Autowired
    private ImageService imageService;


    @GetMapping("/cottage/history/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllCottagePastReservations(@PathVariable Long id){
        List<Reservation> pastCottageReservations = reservationService.getCottageHistoryReservation(id);
        List<OfferHistoryReservationDTO> dtoList = makeFullPastCottageReservationDtoList(pastCottageReservations);
        return ResponseEntity.ok(dtoList);
    }

    private List<OfferHistoryReservationDTO> makeFullPastCottageReservationDtoList(List<Reservation> pastCottageReservations) {
        List<OfferHistoryReservationDTO> dtoList = new ArrayList<OfferHistoryReservationDTO>();
        for (Reservation r : pastCottageReservations){
            r.setOffer(cottageService.findOne(r.getOffer().getId()));
            r.getOffer().setImages(imageService.findAllByOfferId(r.getOffer().getId()));
            dtoList.add(new OfferHistoryReservationDTO(r));
        }
        return dtoList;
    }

    private List<OfferHistoryReservationDTO> makeFullPastShipReservationDtoList(List<Reservation> pastCottageReservations) {
        List<OfferHistoryReservationDTO> dtoList = new ArrayList<OfferHistoryReservationDTO>();
        for (Reservation r : pastCottageReservations){
            r.setOffer(shipService.findOne(r.getOffer().getId()));
            r.getOffer().setImages(imageService.findAllByOfferId(r.getOffer().getId()));
            dtoList.add(new OfferHistoryReservationDTO(r));
        }
        return dtoList;
    }

    private List<OfferHistoryReservationDTO> makeFullPastAdventureReservationDtoList(List<Reservation> pastCottageReservations) {
        List<OfferHistoryReservationDTO> dtoList = new ArrayList<OfferHistoryReservationDTO>();
        for (Reservation r : pastCottageReservations){
            r.setOffer(adventureService.findOneById(r.getOffer().getId()));
            r.getOffer().setImages(imageService.findAllByOfferId(r.getOffer().getId()));
            dtoList.add(new OfferHistoryReservationDTO(r));
        }
        return dtoList;
    }

    @GetMapping("/adventure/history/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllAdventurePastReservations(@PathVariable Long id){
        List<Reservation> pastAdventureReservations = reservationService.getAdventureHistoryReservation(id);
        List<OfferHistoryReservationDTO> dtoList = makeFullPastAdventureReservationDtoList(pastAdventureReservations);
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/adventure/history/name/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllAdventurePastReservationsSortByName(@PathVariable Long id){
        List<Reservation> pastAdventureReservations = reservationService.getAdventureHistoryReservation(id);
        List<OfferHistoryReservationDTO> dtoList = makeFullPastAdventureReservationDtoList(pastAdventureReservations);
        dtoList = sortReservationHistoryByName(dtoList);
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/adventure/history/date/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllAdventurePastReservationsSortByDate(@PathVariable Long id){
        List<Reservation> pastAdventureReservations = reservationService.getAdventureHistoryReservation(id);
        List<OfferHistoryReservationDTO> dtoList = makeFullPastAdventureReservationDtoList(pastAdventureReservations);
        dtoList = sortReservationHistoryByDate(dtoList);
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/adventure/history/duration/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllAdventurePastReservationsSortByDuration(@PathVariable Long id){
        List<Reservation> pastAdventureReservations = reservationService.getAdventureHistoryReservation(id);
        List<OfferHistoryReservationDTO> dtoList = makeFullPastAdventureReservationDtoList(pastAdventureReservations);
        dtoList = sortReservationHistoryByDuration(dtoList);
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/adventure/history/price/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllAdventurePastReservationsSortByPrice(@PathVariable Long id){
        List<Reservation> pastAdventureReservations = reservationService.getAdventureHistoryReservation(id);
        List<OfferHistoryReservationDTO> dtoList = makeFullPastAdventureReservationDtoList(pastAdventureReservations);
        dtoList = sortReservationHistoryByPrice(dtoList);
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/cottage/history/name/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllCottagePastReservationsSortByName(@PathVariable Long id){
        List<Reservation> pastCottageReservations = reservationService.getCottageHistoryReservation(id);
        List<OfferHistoryReservationDTO> dtoList = makeFullPastCottageReservationDtoList(pastCottageReservations);
        dtoList = sortReservationHistoryByName(dtoList);
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/cottage/history/date/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllCottagePastReservationsSortByDate(@PathVariable Long id){
        List<Reservation> pastCottageReservations = reservationService.getCottageHistoryReservation(id);
        List<OfferHistoryReservationDTO> dtoList = makeFullPastCottageReservationDtoList(pastCottageReservations);
        dtoList = sortReservationHistoryByDate(dtoList);
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/cottage/history/duration/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllCottagePastReservationsSortByDuration(@PathVariable Long id){
        List<Reservation> pastCottageReservations = reservationService.getCottageHistoryReservation(id);
        List<OfferHistoryReservationDTO> dtoList = makeFullPastCottageReservationDtoList(pastCottageReservations);
        dtoList = sortReservationHistoryByDuration(dtoList);
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/cottage/history/price/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllCottagePastReservationsSortByPrice(@PathVariable Long id){
        List<Reservation> pastCottageReservations = reservationService.getCottageHistoryReservation(id);
        List<OfferHistoryReservationDTO> dtoList = makeFullPastCottageReservationDtoList(pastCottageReservations);
        dtoList = sortReservationHistoryByPrice(dtoList);
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/ship/history/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllShipPastReservations(@PathVariable Long id){
        List<Reservation> pastShipReservations = reservationService.getShipHistoryReservation(id);
        List<OfferHistoryReservationDTO> dtoList = makeFullPastShipReservationDtoList(pastShipReservations);
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/ship/history/name/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllShipPastReservationsSortByName(@PathVariable Long id){
        List<Reservation> pastShipReservations = reservationService.getShipHistoryReservation(id);
        List<OfferHistoryReservationDTO> dtoList = makeFullPastShipReservationDtoList(pastShipReservations);
        dtoList = sortReservationHistoryByName(dtoList);
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/ship/history/date/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllShipPastReservationsSortByDate(@PathVariable Long id){
        List<Reservation> pastShipReservations = reservationService.getShipHistoryReservation(id);
        List<OfferHistoryReservationDTO> dtoList = makeFullPastShipReservationDtoList(pastShipReservations);
        dtoList = sortReservationHistoryByDate(dtoList);
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/ship/history/duration/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllShipPastReservationsSortByDuration(@PathVariable Long id){
        List<Reservation> pastShipReservations = reservationService.getShipHistoryReservation(id);
        List<OfferHistoryReservationDTO> dtoList = makeFullPastShipReservationDtoList(pastShipReservations);
        dtoList = sortReservationHistoryByDuration(dtoList);
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/ship/history/price/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllShipPastReservationsSortByPrice(@PathVariable Long id){
        List<Reservation> pastShipReservations = reservationService.getShipHistoryReservation(id);
        List<OfferHistoryReservationDTO> dtoList = makeFullPastShipReservationDtoList(pastShipReservations);
        dtoList = sortReservationHistoryByPrice(dtoList);
        return ResponseEntity.ok(dtoList);
    }


    private List<OfferHistoryReservationDTO> sortReservationHistoryByName(List<OfferHistoryReservationDTO> dtoList){
        Collections.sort(dtoList, new Comparator<OfferHistoryReservationDTO>() {
            @Override
            public int compare(OfferHistoryReservationDTO c1, OfferHistoryReservationDTO c2) {
                int NameCompare = c1.getName().compareTo(
                        c2.getName());

                return NameCompare;
            }
        });
        return dtoList;
    }

    private List<OfferHistoryReservationDTO> sortReservationHistoryByDate(List<OfferHistoryReservationDTO> dtoList){
        Collections.sort(dtoList, new Comparator<OfferHistoryReservationDTO>() {
                    @Override
                    public int compare(OfferHistoryReservationDTO c1, OfferHistoryReservationDTO c2) {
                        int dateCompare = c1.getEndDateLocalDate().compareTo(
                                c2.getEndDateLocalDate());

                        return dateCompare;
                    }
                });
        return dtoList;
    }

    private List<OfferHistoryReservationDTO> sortReservationHistoryByDuration(List<OfferHistoryReservationDTO> dtoList){
        Collections.sort(dtoList,new Comparator<OfferHistoryReservationDTO>() {
            @Override
            public int compare(OfferHistoryReservationDTO c1, OfferHistoryReservationDTO c2) {
                return Comparator.comparing(OfferHistoryReservationDTO::getDuration).compare(c1, c2);
            }
        });
        return dtoList;
    }

    private List<OfferHistoryReservationDTO> sortReservationHistoryByPrice(List<OfferHistoryReservationDTO> dtoList){
        Collections.sort(dtoList,new Comparator<OfferHistoryReservationDTO>() {
            @Override
            public int compare(OfferHistoryReservationDTO c1, OfferHistoryReservationDTO c2) {
                return Comparator.comparing(OfferHistoryReservationDTO::getPrice).compare(c1, c2);
            }
        });
        return dtoList;
    }
}
