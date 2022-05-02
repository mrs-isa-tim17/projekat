package com.project.mrsisa.controller;

import com.project.mrsisa.domain.Reservation;
import com.project.mrsisa.dto.UserTokenState;
import com.project.mrsisa.dto.client.CottageHistoryReservationDTO;
import com.project.mrsisa.service.*;
import com.project.mrsisa.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.project.mrsisa.domain.Client;
import com.project.mrsisa.domain.LoyaltyScale;
import com.project.mrsisa.dto.ClientProfileResponseDTO;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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
    public ResponseEntity<List<CottageHistoryReservationDTO>> getAllCottagePastReservations(@PathVariable Long id){
        System.out.println("CONTROLLER");
        List<Reservation> pastCottageReservations = reservationService.getCottageHistoryReservation(id);
        List<CottageHistoryReservationDTO> dtoList = new ArrayList<CottageHistoryReservationDTO>();
        for (Reservation r : pastCottageReservations){
            r.setOffer(cottageService.findOne(r.getOffer().getId()));
            r.getOffer().setImages(imageService.findAllByCottageId(r.getOffer().getId()));
            System.out.println(r.getOffer().getName());
            dtoList.add(new CottageHistoryReservationDTO(r));
        }
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
