package com.project.mrsisa.controller;

import com.project.mrsisa.domain.*;
import com.project.mrsisa.dto.ReserveEntityDTO;
import com.project.mrsisa.dto.ReserveEntityResponseDTO;
import com.project.mrsisa.dto.client.SuccessOfCancelReservationDTO;
import com.project.mrsisa.dto.client.UpcomingReservationDTO;
import com.project.mrsisa.dto.owner.*;

import com.project.mrsisa.domain.Adventure;
import com.project.mrsisa.domain.OfferType;
import com.project.mrsisa.domain.Reservation;
import com.project.mrsisa.dto.owner.HistoryFutureReservationOwnerDTO;
import com.project.mrsisa.dto.owner.HistoryPastReservationOwnerDTO;

import com.project.mrsisa.dto.simple_user.PaginationDTO;
import com.project.mrsisa.exception.AlreadyCanceled;
import com.project.mrsisa.exception.NotAvailable;
import com.project.mrsisa.exception.NotDefinedValue;
import com.project.mrsisa.service.CottageService;
import com.project.mrsisa.service.ImageService;
import com.project.mrsisa.service.ReservationService;

import com.project.mrsisa.service.ShipService;
import com.project.mrsisa.dto.StartEndDateDTO;
import com.project.mrsisa.dto.client.OfferHistoryReservationDTO;
import com.project.mrsisa.service.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.*;

import java.time.format.DateTimeFormatter;
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
    private CottageOwnerService cottageOwnerService;
    @Autowired
    private ShipService shipService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private AdventureService adventureService;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    @RequestMapping(value = "/reservations/future/{id}")
    @PreAuthorize("hasRole('COTTAGE_OWNER') or hasRole('SHIP_OWNER')")
    public ResponseEntity<List<HistoryFutureReservationOwnerDTO>> getAllFutureReservationForOffer(@PathVariable Long id) {

        List<HistoryFutureReservationOwnerDTO> reservationsDTO = new ArrayList<HistoryFutureReservationOwnerDTO>();
        List<Reservation> futureReservations = reservationService.getFutureHistoryReservation(id);
        for (Reservation r : futureReservations) {
            r.setOffer(cottageService.findOne(id));
            /*if (r.getOfferType() == OfferType.COTTAGE) {
                r.setOffer(cottageService.findOne(id));
            } else if (r.getOfferType() == OfferType.ADVENTURE) {
                //poziv adventureService.findOne
            } else { //ship
               // r.setOffer(shipService.findOne(id));
            }*/
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

    @RequestMapping("/reportYearly/{id}")
    @PreAuthorize("hasRole('COTTAGE_OWNER')")
    public ResponseEntity<List<ReservationsYearlyGraphDTO>> getYearlyReport(@PathVariable Long id) {
        CottageOwner owner = cottageOwnerService.findOne(id);
        List<Cottage> offers = cottageService.getCottagesByOwner(owner);
        List<ReservationsYearlyGraphDTO> resGraphDTO = new ArrayList<>();
        for (Cottage c : offers) {
            LocalDate dateNow = LocalDate.now();
            LocalDate dateStart = dateNow.minusYears(1);
            int yearStart = dateStart.getYear();
            int monthStart = dateStart.getMonthValue();
            List<Integer> numbers = new ArrayList<>();
            List<String> monthYear = new ArrayList<>();
            for (int i = monthStart; i < 13; i++) {
                List<Reservation> resForMonthAndYear = reservationService.getReservationsForMonthAndYear(c.getId(), i, yearStart);
                numbers.add(resForMonthAndYear.size());
                String monthYearString = getMonthYear(i,yearStart);
                monthYear.add(monthYearString);

            }
            yearStart++;
            for (int i = 1; i < dateNow.getMonthValue() + 1; i++) {
                List<Reservation> resForMonthAndYear = reservationService.getReservationsForMonthAndYear(c.getId(), i, yearStart);
                numbers.add(resForMonthAndYear.size());
                String monthYearString = getMonthYear(i,yearStart);;
                monthYear.add(monthYearString);

            }
            resGraphDTO.add(new ReservationsYearlyGraphDTO(monthYear, numbers, c.getName()));
        }
        return ResponseEntity.ok(resGraphDTO);
    }

    private String getMonthYear(int month,int year){
        String monthString="";
        switch (month){
            case 1: {
                monthString = "JAN";
                break;
            }
            case 2:{
                monthString = "FEB";
                break;
            }
            case 3:{
                monthString = "MAR";
                break;
            }
            case 4:{
                monthString = "APR";
                break;
            }
            case 5:{
                monthString = "MAJ";
                break;
            }
            case 6:{
                monthString = "JUN";
                break;
            }
            case 7:{
                monthString = "JUL";
                break;
            }
            case 8:{
                monthString = "AVG";
                break;
            }
            case 9:{
                monthString = "SEP";
                break;
            }
            case 10:{
                monthString = "OKT";
                break;
            }
            case 11:{
                monthString = "NOV";
                break;
            }
            case 12: {
                monthString = "DEC";
                break;
            }
        }
        String monthYear = monthString + " " + Integer.toString(year);
        return monthYear;
    }

    @RequestMapping("/reportMonthly/{id}")
    @PreAuthorize("hasRole('COTTAGE_OWNER')")
    public ResponseEntity<List<ReservationMonthlyGraphDTO>> getMonthlyReport(@PathVariable Long id){
        CottageOwner owner = cottageOwnerService.findOne(id);
        List<Cottage> offers = cottageService.getCottagesByOwner(owner);
        List<ReservationMonthlyGraphDTO> dto = new ArrayList<>();
        for(Cottage c : offers){
            List<Reservation> resForMonth = reservationService.getReservationForMonth(c.getId());
            dto.add(new ReservationMonthlyGraphDTO(c.getName(),resForMonth.size()));
        }
        return ResponseEntity.ok(dto);
    }

    @RequestMapping("/reportWeekly/{id}")
    @PreAuthorize("hasRole('COTTAGE_OWNER')")
    public ResponseEntity<List<ReservationMonthlyGraphDTO>> getWeeklyReport(@PathVariable Long id){
        CottageOwner owner = cottageOwnerService.findOne(id);
        List<Cottage> offers = cottageService.getCottagesByOwner(owner);
        List<ReservationMonthlyGraphDTO> dto = new ArrayList<>();
        for(Cottage c : offers){
            List<Reservation> resForWeek = reservationService.getReservationForWeek(c.getId());
            dto.add(new ReservationMonthlyGraphDTO(c.getName(),resForWeek.size()));
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/reportPeriod/{id}")
    @PreAuthorize("hasRole('COTTAGE_OWNER')")
    public ResponseEntity<List<IncomePeriodGraphDTO>> getPeriodReportIncome(@PathVariable Long id, @RequestParam Map<String,String> params){
        CottageOwner owner = cottageOwnerService.findOne(id);
        List<Cottage> offers = cottageService.getCottagesByOwner(owner);
        List<IncomePeriodGraphDTO> dto = new ArrayList<>();
        System.out.println("datummmm"+params.get("start_date"));

        LocalDate start = LocalDate.parse(params.get("start_date"));
        LocalDate end = LocalDate.parse(params.get("end_date"));
        for(Cottage c : offers){
            List<Reservation> resForPeriod = reservationService.getReservationForPeriod(c.getId(),params.get("start_date"),params.get("end_date"));
            double prices = getPrices(resForPeriod);
            dto.add(new IncomePeriodGraphDTO(c.getName(),prices));
            System.out.println("datumiiii"+resForPeriod.size());
        }
        return ResponseEntity.ok(dto);
    }

    private double getPrices(List<Reservation> reservations){
        double prices = 0;
        for(Reservation r : reservations){
            prices += r.getPrice();
        }
        return prices;
    }


    
	@GetMapping(value="/reservation/periods/{id}")
	@PreAuthorize("hasRole('FISHINSTRUCTOR') or hasRole('ROLE_COTTAGE_OWNER') or hasRole('ROLE_SHIP_OWNER')")
	public ResponseEntity<List<StartEndDateDTO>> getReservationPeriods(@PathVariable Long id){
		List<StartEndDateDTO> reservationPeriods = new ArrayList<StartEndDateDTO>();
		
		Adventure adventure = adventureService.findOneById(id);
		List<Reservation> reservations = reservationService.getAllReservationsForOffer(id);
		for(Reservation r : reservations) {
			StartEndDateDTO period = new StartEndDateDTO(r.getStartDate().atStartOfDay().format(formatter), r.getEndDate().atStartOfDay().format(formatter), adventure.getName());
			reservationPeriods.add(period);
		}
		
		return new ResponseEntity<>(reservationPeriods, HttpStatus.OK);
	}

    @PostMapping(value = "reserve")
    @PreAuthorize("hasRole('CLIENT') or hasRole('COTTAGE_OWNER') or hasRole('FISHINSTRUCTOR')")
    public ResponseEntity<ReserveEntityResponseDTO> reserveEntity(@RequestBody ReserveEntityDTO reserveEntityDTO){
        try{
            Reservation r = reservationService.makeReservation(reserveEntityDTO);
           // reservationService.sendMailAboutReservation(r.getClient(), r);
            return ResponseEntity.ok(new ReserveEntityResponseDTO());
        }catch (AlreadyCanceled ac){
            return ResponseEntity.ok(new ReserveEntityResponseDTO(ac.getMessage()));
        }catch (NotAvailable na){
            return ResponseEntity.ok(new ReserveEntityResponseDTO(na.getMessage()));
        }catch (NotDefinedValue ndv) {
            return ResponseEntity.ok(new ReserveEntityResponseDTO(ndv.getMessage()));
        }catch (MailSendException mse){
            return ResponseEntity.ok(new ReserveEntityResponseDTO("Iz nekoga nismo bili u stanju da pošaljemo Vam mejl, kod zakazene rezervacije možete da vidite rezervaciju"));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok(new ReserveEntityResponseDTO("Iz nekog razloga došlo je do greške, molimo Vas pokušavajte kasnije"));
        }
    }


    @GetMapping(value = "upcoming/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<UpcomingReservationDTO>> getUpcomingReservationForClient(@PathVariable long id){
        List<Reservation> reservations = reservationService.getUpcomingReservationsForClient(id);
        List<UpcomingReservationDTO> res = new ArrayList<>();
        for (Reservation r : reservations){
            res.add(new UpcomingReservationDTO(r));
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping(value = "cancel/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<SuccessOfCancelReservationDTO> cancelReservation(@PathVariable long id){
        //cancel
        try {
            SuccessOfCancelReservationDTO success = reservationService.cancelReservation(id);
            return ResponseEntity.ok(success);
        }catch (Exception e) {
            SuccessOfCancelReservationDTO success = new SuccessOfCancelReservationDTO();
            success.setSuccessful(false);
            return ResponseEntity.ok(success);
        }
    }
}



