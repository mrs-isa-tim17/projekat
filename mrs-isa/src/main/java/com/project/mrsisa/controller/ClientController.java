package com.project.mrsisa.controller;

import com.project.mrsisa.domain.Reservation;
import com.project.mrsisa.dto.UserTokenState;
import com.project.mrsisa.dto.client.OfferHistoryReservationDTO;
import com.project.mrsisa.service.*;
import com.project.mrsisa.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.project.mrsisa.domain.Client;
import com.project.mrsisa.domain.LoyaltyScale;
import com.project.mrsisa.dto.ClientProfileResponseDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping(value = "/client", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	@Autowired
	private LoyaltyScaleService loyaltyScaleService;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private CottageService cottageService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ShipService shipService;

    @GetMapping("/verify/{code}")
    public ResponseEntity<UserTokenState> verifyAccount(@PathVariable("code") String code){
        Client c = clientService.verify(code);
        if (c == null){
            return ResponseEntity.ok(null);
        }
        String jwt = tokenUtils.generateToken(c.getUsername());
        int expiresIn = tokenUtils.getExpiredIn();
        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn, c.getRoleId(), c.getId()));//new ResponseEntity<Client>(c, HttpStatus.OK);
    }

    @GetMapping("/cottage/history/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllCottagePastReservations(@PathVariable Long id){
        List<Reservation> pastCottageReservations = reservationService.getCottageHistoryReservation(id);
        List<OfferHistoryReservationDTO> dtoList = new ArrayList<OfferHistoryReservationDTO>();
        for (Reservation r : pastCottageReservations){
            System.out.println(r.getOffer().getId());
            r.setOffer(cottageService.findOne(r.getOffer().getId()));
            r.getOffer().setImages(imageService.findAllByOfferId(r.getOffer().getId()));
            dtoList.add(new OfferHistoryReservationDTO(r));
        }
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/cottage/history/name/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ArrayList<OfferHistoryReservationDTO>> getAllCottagePastReservationsSortByName(@PathVariable Long id){
        List<Reservation> pastCottageReservations = reservationService.getCottageHistoryReservation(id);
        ArrayList<OfferHistoryReservationDTO> dtoList = new ArrayList<OfferHistoryReservationDTO>();
        for (Reservation r : pastCottageReservations){
            r.setOffer(cottageService.findOne(r.getOffer().getId()));
            r.getOffer().setImages(imageService.findAllByOfferId(r.getOffer().getId()));
            dtoList.add(new OfferHistoryReservationDTO(r));
        }
        Collections.sort(dtoList, new Comparator<OfferHistoryReservationDTO>() {
            @Override
            public int compare(OfferHistoryReservationDTO c1, OfferHistoryReservationDTO c2) {
                int NameCompare = c1.getName().compareTo(
                        c2.getName());

                return NameCompare;
            }
        });
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/cottage/history/date/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ArrayList<OfferHistoryReservationDTO>> getAllCottagePastReservationsSortByDate(@PathVariable Long id){
        List<Reservation> pastCottageReservations = reservationService.getCottageHistoryReservation(id);
        ArrayList<OfferHistoryReservationDTO> dtoList = new ArrayList<OfferHistoryReservationDTO>();
        for (Reservation r : pastCottageReservations){
            r.setOffer(cottageService.findOne(r.getOffer().getId()));
            r.getOffer().setImages(imageService.findAllByOfferId(r.getOffer().getId()));
            dtoList.add(new OfferHistoryReservationDTO(r));
        }
        Collections.sort(dtoList, //(x, y) -> x.getStartDate().compareTo(y.getEndDate()));
                new Comparator<OfferHistoryReservationDTO>() {
            @Override
            public int compare(OfferHistoryReservationDTO c1, OfferHistoryReservationDTO c2) {
                int dateCompare = c1.getEndDateLocalDate().compareTo(
                        c2.getEndDateLocalDate());

                return dateCompare;
            }
        });//*/
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/cottage/history/duration/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ArrayList<OfferHistoryReservationDTO>> getAllCottagePastReservationsSortByDuration(@PathVariable Long id){
        List<Reservation> pastCottageReservations = reservationService.getCottageHistoryReservation(id);
        ArrayList<OfferHistoryReservationDTO> dtoList = new ArrayList<OfferHistoryReservationDTO>();
        for (Reservation r : pastCottageReservations){
            r.setOffer(cottageService.findOne(r.getOffer().getId()));
            r.getOffer().setImages(imageService.findAllByOfferId(r.getOffer().getId()));
            dtoList.add(new OfferHistoryReservationDTO(r));
        }
        Collections.sort(dtoList,new Comparator<OfferHistoryReservationDTO>() {
            @Override
            public int compare(OfferHistoryReservationDTO c1, OfferHistoryReservationDTO c2) {
                return Comparator.comparing(OfferHistoryReservationDTO::getDuration).compare(c1, c2);
            }
        });
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/cottage/history/price/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ArrayList<OfferHistoryReservationDTO>> getAllCottagePastReservationsSortByPrice(@PathVariable Long id){
        List<Reservation> pastCottageReservations = reservationService.getCottageHistoryReservation(id);
        ArrayList<OfferHistoryReservationDTO> dtoList = new ArrayList<OfferHistoryReservationDTO>();
        for (Reservation r : pastCottageReservations){
            r.setOffer(cottageService.findOne(r.getOffer().getId()));
            r.getOffer().setImages(imageService.findAllByOfferId(r.getOffer().getId()));
            dtoList.add(new OfferHistoryReservationDTO(r));
        }
        Collections.sort(dtoList,new Comparator<OfferHistoryReservationDTO>() {
            @Override
            public int compare(OfferHistoryReservationDTO c1, OfferHistoryReservationDTO c2) {
                return Comparator.comparing(OfferHistoryReservationDTO::getPrice).compare(c1, c2);
            }
        });
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/ship/history/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<OfferHistoryReservationDTO>> getAllShipPastReservations(@PathVariable Long id){
        List<Reservation> pastShipReservations = reservationService.getShipHistoryReservation(id);
        List<OfferHistoryReservationDTO> dtoList = new ArrayList<OfferHistoryReservationDTO>();
        for (Reservation r : pastShipReservations){
            r.setOffer(shipService.findOne(r.getOffer().getId()));
            r.getOffer().setImages(imageService.findAllByOfferId(r.getOffer().getId()));
            dtoList.add(new OfferHistoryReservationDTO(r));
        }
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/ship/history/name/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ArrayList<OfferHistoryReservationDTO>> getAllShipPastReservationsSortByName(@PathVariable Long id){
        List<Reservation> pastCottageReservations = reservationService.getShipHistoryReservation(id);
        ArrayList<OfferHistoryReservationDTO> dtoList = new ArrayList<OfferHistoryReservationDTO>();
        for (Reservation r : pastCottageReservations){
            r.setOffer(shipService.findOne(r.getOffer().getId()));
            r.getOffer().setImages(imageService.findAllByOfferId(r.getOffer().getId()));
            dtoList.add(new OfferHistoryReservationDTO(r));
        }
        Collections.sort(dtoList, new Comparator<OfferHistoryReservationDTO>() {
            @Override
            public int compare(OfferHistoryReservationDTO c1, OfferHistoryReservationDTO c2) {
                int NameCompare = c1.getName().compareTo(
                        c2.getName());

                return NameCompare;
            }
        });
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/ship/history/date/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ArrayList<OfferHistoryReservationDTO>> getAllShipPastReservationsSortByDate(@PathVariable Long id){
        List<Reservation> pastCottageReservations = reservationService.getShipHistoryReservation(id);
        ArrayList<OfferHistoryReservationDTO> dtoList = new ArrayList<OfferHistoryReservationDTO>();
        for (Reservation r : pastCottageReservations){
            r.setOffer(shipService.findOne(r.getOffer().getId()));
            r.getOffer().setImages(imageService.findAllByOfferId(r.getOffer().getId()));
            dtoList.add(new OfferHistoryReservationDTO(r));
        }
        Collections.sort(dtoList, //(x, y) -> x.getStartDate().compareTo(y.getEndDate()));
                new Comparator<OfferHistoryReservationDTO>() {
                    @Override
                    public int compare(OfferHistoryReservationDTO c1, OfferHistoryReservationDTO c2) {
                        int dateCompare = c1.getEndDateLocalDate().compareTo(
                                c2.getEndDateLocalDate());

                        return dateCompare;
                    }
                });//*/
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/ship/history/duration/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ArrayList<OfferHistoryReservationDTO>> getAllShipPastReservationsSortByDuration(@PathVariable Long id){
        List<Reservation> pastCottageReservations = reservationService.getShipHistoryReservation(id);
        ArrayList<OfferHistoryReservationDTO> dtoList = new ArrayList<OfferHistoryReservationDTO>();
        for (Reservation r : pastCottageReservations){
            r.setOffer(shipService.findOne(r.getOffer().getId()));
            r.getOffer().setImages(imageService.findAllByOfferId(r.getOffer().getId()));
            dtoList.add(new OfferHistoryReservationDTO(r));
        }
        Collections.sort(dtoList,new Comparator<OfferHistoryReservationDTO>() {
            @Override
            public int compare(OfferHistoryReservationDTO c1, OfferHistoryReservationDTO c2) {
                return Comparator.comparing(OfferHistoryReservationDTO::getDuration).compare(c1, c2);
            }
        });
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/ship/history/price/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ArrayList<OfferHistoryReservationDTO>> getAllShipPastReservationsSortByPrice(@PathVariable Long id){
        List<Reservation> pastCottageReservations = reservationService.getShipHistoryReservation(id);
        ArrayList<OfferHistoryReservationDTO> dtoList = new ArrayList<OfferHistoryReservationDTO>();
        for (Reservation r : pastCottageReservations){
            r.setOffer(shipService.findOne(r.getOffer().getId()));
            r.getOffer().setImages(imageService.findAllByOfferId(r.getOffer().getId()));
            dtoList.add(new OfferHistoryReservationDTO(r));
        }
        Collections.sort(dtoList,new Comparator<OfferHistoryReservationDTO>() {
            @Override
            public int compare(OfferHistoryReservationDTO c1, OfferHistoryReservationDTO c2) {
                return Comparator.comparing(OfferHistoryReservationDTO::getPrice).compare(c1, c2);
            }
        });
        return ResponseEntity.ok(dtoList);
    }



    @GetMapping("/profile/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ClientProfileResponseDTO> getClient(@PathVariable Long id){
        
        Client client = null;
       try {
        client = clientService.findOne(id);
        if (client == null) {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
       
       }catch (Exception e) {
		e.printStackTrace();
       }
        
        LoyaltyScale ls = loyaltyScaleService.loyaltyScalesGreaterMinimumTrashhold(client.getLoyaltyPoints());
        int discount;
        if (ls == null) {
        	discount = 0;
        }else {
        	discount = ls.getDiscount();
        }
 
        return new ResponseEntity<ClientProfileResponseDTO>(new ClientProfileResponseDTO(client, discount), HttpStatus.OK);
    }
	
	@PostMapping(value = "/profile/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ClientProfileResponseDTO> updateClient(@PathVariable Long id, @RequestBody ClientProfileResponseDTO clientDTO){
        
        Client client = null;
       try {
        client = clientService.findOne(id);
        if (client == null) {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        client.setName(clientDTO.getName());
        
        client.setSurname(clientDTO.getSurname());
        
        client.setPhoneNumber(clientDTO.getPhoneNumber());
        
        if (clientDTO.getPassword() != "") {
        	client.setPassword(clientDTO.getPassword());
        }

        client.getAddress().setLatitude(clientDTO.getLatitude());
        client.getAddress().setLongitude(clientDTO.getLongitude());

        clientService.save(client);
        
       }catch (Exception e) {
		e.printStackTrace();
       }

       return new ResponseEntity<ClientProfileResponseDTO>(new ClientProfileResponseDTO(client, 5), HttpStatus.OK);
    }
}
