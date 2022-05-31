package com.project.mrsisa.controller;

import com.project.mrsisa.domain.OfferType;
import com.project.mrsisa.domain.Reservation;
import com.project.mrsisa.dto.owner.HistoryFutureReservationOwnerDTO;
import com.project.mrsisa.dto.owner.HistoryPastReservationOwnerDTO;
import com.project.mrsisa.dto.simple_user.PaginationDTO;
import com.project.mrsisa.service.CottageService;
import com.project.mrsisa.service.ImageService;
import com.project.mrsisa.service.ReservationService;

import com.project.mrsisa.service.ShipService;

import com.project.mrsisa.dto.client.OfferHistoryReservationDTO;
import com.project.mrsisa.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

@RestController
@RequestMapping(value = "/offer", produces = MediaType.APPLICATION_JSON_VALUE)
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
    @Autowired
    private AdventureService adventureService;

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
    public ResponseEntity<List<HistoryPastReservationOwnerDTO>> getAllPastReservationForOffer(@PathVariable Long id) {


        List<HistoryPastReservationOwnerDTO> reservationsDTO = new ArrayList<HistoryPastReservationOwnerDTO>();
        List<Reservation> pastReservations = reservationService.getPastHistoryReservation(id);
        for (Reservation r : pastReservations) {
            if (r.getOfferType() == OfferType.COTTAGE) {
                r.setOffer(cottageService.findOne(id));

            } else if (r.getOfferType() == OfferType.ADVENTURE) {
                //poziv adventureService.findOne
            } else { //ship
                r.setOffer(shipService.findOne(id));
            }
            r.getOffer().setImages(imageService.findAllByOfferId(id));
            reservationsDTO.add(new HistoryPastReservationOwnerDTO(r));


        }
        return ResponseEntity.ok(reservationsDTO);
    }



    @PostMapping("/cottage/history/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllCottagePastReservations(@PathVariable Long id, @RequestBody PaginationDTO paginationDTO){
        List<Reservation> pastCottageReservations = reservationService.getCottageHistoryReservation(id);
        OfferHistoryReservationDTO size = new OfferHistoryReservationDTO();
        size.setListSize(pastCottageReservations.size());
        pastCottageReservations = pastCottageReservations.subList(paginationDTO.getFromElement(), paginationDTO.getUntilElement(pastCottageReservations.size()));
        List<OfferHistoryReservationDTO> dtoList = makeFullPastCottageReservationDtoList(pastCottageReservations);
        dtoList.add(0, size);
        return ResponseEntity.ok(dtoList);
    }


    @PostMapping("/cottage/history/name/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllCottagePastReservationsSortByName(@PathVariable Long id, @RequestBody PaginationDTO paginationDTO){
        List<Reservation> pastCottageReservations = reservationService.getCottageHistoryReservation(id);
        List<OfferHistoryReservationDTO> dtoList = makeFullPastCottageReservationDtoList(pastCottageReservations);
        dtoList = sortReservationHistoryByName(dtoList);
        OfferHistoryReservationDTO size = new OfferHistoryReservationDTO();
        size.setListSize(pastCottageReservations.size());
        dtoList = dtoList.subList(paginationDTO.getFromElement(), paginationDTO.getUntilElement(pastCottageReservations.size()));
        dtoList.add(0, size);
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

    @PostMapping("/adventure/history/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllAdventurePastReservations(@PathVariable Long id, @RequestBody PaginationDTO paginationDTO){
        List<Reservation> pastAdventureReservations = reservationService.getAdventureHistoryReservation(id);
        OfferHistoryReservationDTO size = new OfferHistoryReservationDTO();
        size.setListSize(pastAdventureReservations.size());
        pastAdventureReservations = pastAdventureReservations.subList(paginationDTO.getFromElement(), paginationDTO.getUntilElement(pastAdventureReservations.size()));
        List<OfferHistoryReservationDTO> dtoList = makeFullPastAdventureReservationDtoList(pastAdventureReservations);
        dtoList.add(0, size);
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping("/adventure/history/name/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllAdventurePastReservationsSortByName(@PathVariable Long id, @RequestBody PaginationDTO paginationDTO){
        List<Reservation> pastAdventureReservations = reservationService.getAdventureHistoryReservation(id);
        List<OfferHistoryReservationDTO> dtoList = makeFullPastAdventureReservationDtoList(pastAdventureReservations);
        dtoList = sortReservationHistoryByName(dtoList);
        OfferHistoryReservationDTO size = new OfferHistoryReservationDTO();
        size.setListSize(pastAdventureReservations.size());
        dtoList = dtoList.subList(paginationDTO.getFromElement(), paginationDTO.getUntilElement(pastAdventureReservations.size()));
        dtoList.add(0, size);
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping("/adventure/history/date/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllAdventurePastReservationsSortByDate(@PathVariable Long id, @RequestBody PaginationDTO paginationDTO){
        List<Reservation> pastAdventureReservations = reservationService.getAdventureHistoryReservation(id);
        List<OfferHistoryReservationDTO> dtoList = makeFullPastAdventureReservationDtoList(pastAdventureReservations);
        dtoList = sortReservationHistoryByDate(dtoList);
        OfferHistoryReservationDTO size = new OfferHistoryReservationDTO();
        size.setListSize(pastAdventureReservations.size());
        dtoList = dtoList.subList(paginationDTO.getFromElement(), paginationDTO.getUntilElement(pastAdventureReservations.size()));
        dtoList.add(0, size);
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping("/adventure/history/duration/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllAdventurePastReservationsSortByDuration(@PathVariable Long id, @RequestBody PaginationDTO paginationDTO){
        List<Reservation> pastAdventureReservations = reservationService.getAdventureHistoryReservation(id);
        List<OfferHistoryReservationDTO> dtoList = makeFullPastAdventureReservationDtoList(pastAdventureReservations);
        dtoList = sortReservationHistoryByDuration(dtoList);
        OfferHistoryReservationDTO size = new OfferHistoryReservationDTO();
        size.setListSize(pastAdventureReservations.size());
        dtoList = dtoList.subList(paginationDTO.getFromElement(), paginationDTO.getUntilElement(pastAdventureReservations.size()));
        dtoList.add(0, size);
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping("/adventure/history/price/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllAdventurePastReservationsSortByPrice(@PathVariable Long id, @RequestBody PaginationDTO paginationDTO){
        List<Reservation> pastAdventureReservations = reservationService.getAdventureHistoryReservation(id);
        List<OfferHistoryReservationDTO> dtoList = makeFullPastAdventureReservationDtoList(pastAdventureReservations);
        dtoList = sortReservationHistoryByPrice(dtoList);
        OfferHistoryReservationDTO size = new OfferHistoryReservationDTO();
        size.setListSize(pastAdventureReservations.size());
        dtoList = dtoList.subList(paginationDTO.getFromElement(), paginationDTO.getUntilElement(pastAdventureReservations.size()));
        dtoList.add(0, size);
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping("/cottage/history/date/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllCottagePastReservationsSortByDate(@PathVariable Long id, @RequestBody PaginationDTO paginationDTO){
        List<Reservation> pastCottageReservations = reservationService.getCottageHistoryReservation(id);
        List<OfferHistoryReservationDTO> dtoList = makeFullPastCottageReservationDtoList(pastCottageReservations);
        dtoList = sortReservationHistoryByDate(dtoList);
        OfferHistoryReservationDTO size = new OfferHistoryReservationDTO();
        size.setListSize(pastCottageReservations.size());
        dtoList = dtoList.subList(paginationDTO.getFromElement(), paginationDTO.getUntilElement(pastCottageReservations.size()));
        dtoList.add(0, size);
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping("/cottage/history/duration/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllCottagePastReservationsSortByDuration(@PathVariable Long id, @RequestBody PaginationDTO paginationDTO){
        List<Reservation> pastCottageReservations = reservationService.getCottageHistoryReservation(id);
        List<OfferHistoryReservationDTO> dtoList = makeFullPastCottageReservationDtoList(pastCottageReservations);
        dtoList = sortReservationHistoryByDuration(dtoList);
        OfferHistoryReservationDTO size = new OfferHistoryReservationDTO();
        size.setListSize(pastCottageReservations.size());
        dtoList = dtoList.subList(paginationDTO.getFromElement(), paginationDTO.getUntilElement(pastCottageReservations.size()));
        dtoList.add(0, size);
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping("/cottage/history/price/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllCottagePastReservationsSortByPrice(@PathVariable Long id, @RequestBody PaginationDTO paginationDTO){
        List<Reservation> pastCottageReservations = reservationService.getCottageHistoryReservation(id);
        List<OfferHistoryReservationDTO> dtoList = makeFullPastCottageReservationDtoList(pastCottageReservations);
        dtoList = sortReservationHistoryByPrice(dtoList);
        OfferHistoryReservationDTO size = new OfferHistoryReservationDTO();
        size.setListSize(pastCottageReservations.size());
        dtoList = dtoList.subList(paginationDTO.getFromElement(), paginationDTO.getUntilElement(pastCottageReservations.size()));
        dtoList.add(0, size);
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping("/ship/history/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllShipPastReservations(@PathVariable Long id, @RequestBody PaginationDTO paginationDTO){
        List<Reservation> pastShipReservations = reservationService.getShipHistoryReservation(id);
        OfferHistoryReservationDTO size = new OfferHistoryReservationDTO();
        size.setListSize(pastShipReservations.size());
        pastShipReservations = pastShipReservations.subList(paginationDTO.getFromElement(), paginationDTO.getUntilElement(pastShipReservations.size()));
        List<OfferHistoryReservationDTO> dtoList = makeFullPastShipReservationDtoList(pastShipReservations);
        dtoList.add(0, size);
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping("/ship/history/name/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllShipPastReservationsSortByName(@PathVariable Long id, @RequestBody PaginationDTO paginationDTO){
        List<Reservation> pastShipReservations = reservationService.getShipHistoryReservation(id);
        List<OfferHistoryReservationDTO> dtoList = makeFullPastShipReservationDtoList(pastShipReservations);
        dtoList = sortReservationHistoryByName(dtoList);
        OfferHistoryReservationDTO size = new OfferHistoryReservationDTO();
        size.setListSize(pastShipReservations.size());
        dtoList = dtoList.subList(paginationDTO.getFromElement(), paginationDTO.getUntilElement(pastShipReservations.size()));
        dtoList.add(0, size);
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping("/ship/history/date/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllShipPastReservationsSortByDate(@PathVariable Long id, @RequestBody PaginationDTO paginationDTO){
        List<Reservation> pastShipReservations = reservationService.getShipHistoryReservation(id);
        List<OfferHistoryReservationDTO> dtoList = makeFullPastShipReservationDtoList(pastShipReservations);
        dtoList = sortReservationHistoryByDate(dtoList);
        OfferHistoryReservationDTO size = new OfferHistoryReservationDTO();
        size.setListSize(pastShipReservations.size());
        dtoList = dtoList.subList(paginationDTO.getFromElement(), paginationDTO.getUntilElement(pastShipReservations.size()));
        dtoList.add(0, size);
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping("/ship/history/duration/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllShipPastReservationsSortByDuration(@PathVariable Long id, @RequestBody PaginationDTO paginationDTO){
        List<Reservation> pastShipReservations = reservationService.getShipHistoryReservation(id);
        List<OfferHistoryReservationDTO> dtoList = makeFullPastShipReservationDtoList(pastShipReservations);
        dtoList = sortReservationHistoryByDuration(dtoList);
        OfferHistoryReservationDTO size = new OfferHistoryReservationDTO();
        size.setListSize(pastShipReservations.size());
        dtoList = dtoList.subList(paginationDTO.getFromElement(), paginationDTO.getUntilElement(pastShipReservations.size()));
        dtoList.add(0, size);
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping("/ship/history/price/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllShipPastReservationsSortByPrice(@PathVariable Long id, @RequestBody PaginationDTO paginationDTO){
        List<Reservation> pastShipReservations = reservationService.getShipHistoryReservation(id);
        List<OfferHistoryReservationDTO> dtoList = makeFullPastShipReservationDtoList(pastShipReservations);
        dtoList = sortReservationHistoryByPrice(dtoList);
        OfferHistoryReservationDTO size = new OfferHistoryReservationDTO();
        size.setListSize(pastShipReservations.size());
        dtoList = dtoList.subList(paginationDTO.getFromElement(), paginationDTO.getUntilElement(pastShipReservations.size()));
        dtoList.add(0, size);
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
