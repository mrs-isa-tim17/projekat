package com.project.mrsisa.controller;

import com.project.mrsisa.domain.*;
import com.project.mrsisa.dto.UserTokenState;
import com.project.mrsisa.dto.client.CurrentClientDTO;
import com.project.mrsisa.dto.client.HomePageInfoDTO;
import com.project.mrsisa.dto.client.OfferHistoryReservationDTO;
import com.project.mrsisa.dto.client.SubscriebedEntitiesDTO;
import com.project.mrsisa.service.*;
import com.project.mrsisa.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.project.mrsisa.dto.ClientProfileResponseDTO;

import java.util.*;

@RestController
@RequestMapping(value = "/client", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class ClientController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private CottageOwnerService cottageOwnerService;
	@Autowired
    private CottageService cottageService;
    @Autowired
    private ClientService clientService;
	@Autowired
	private LoyaltyScaleService loyaltyScaleService;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private DeleteRequestService deleteRequestService;

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

    @GetMapping("/penalties/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<HomePageInfoDTO> getInfoForHome(@PathVariable Long id){
        HomePageInfoDTO hp = new HomePageInfoDTO();
        Client client = clientService.findOne(id);
        hp.setPenalties(client.getPenaltyNumber());
        hp.setDeleteRequestMade(deleteRequestService.getIfUserMadeDeleteRequest(client.getId()));
        return ResponseEntity.ok(hp);
    }

    @GetMapping("/profile/{id}")
    @PreAuthorize("hasRole('CLIENT') or hasRole('COTTAGE_OWNER') or hasRole('SHIP_OWNER') or hasRole('FISHINSTRUCTOR')")
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
        
        LoyaltyScale ls = loyaltyScaleService.findCurrentLoyaltyScaleByUserType(client.getRoleId(), client.getUserType());//findCurrentLoyaltyScaleForUser(1);//loyaltyScalesGreaterMinimumTrashhold(client.getLoyaltyPoints());
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

    @GetMapping(value = "/current/{id}")
    @PreAuthorize("hasRole('COTTAGE_OWNER') or hasRole('FISHINSTRUCTOR')")
    public ResponseEntity<List<CurrentClientDTO>> getCurrentClients(@PathVariable Long id){
        CottageOwner owner = cottageOwnerService.findOne(id);
        List<String> emails = new ArrayList<>();
        List<Cottage> cottages = cottageService.getCottagesByOwner(owner);
        List<CurrentClientDTO> current = new ArrayList<>();
        for(Cottage c : cottages){
            List<Reservation> reservations = reservationService.getCurrentReservationsForOffer(c.getId());
            for(Reservation r : reservations){
                System.out.println("rez" + r.getId());
                Client client = clientService.findOne(r.getClient().getId());
                CurrentClientDTO currentdto = new CurrentClientDTO(client,c,r);
                current.add(currentdto);
               /* if(!emails.contains(currentdto.getClientEmail())){
                    current.add(currentdto);
                    emails.add(currentdto.getClientEmail());
                }*/
            }
        }
        for(CurrentClientDTO currenttt : current){
            System.out.println("trenutnoooooo" + currenttt.getClientEmail());
        }

        return new ResponseEntity<>(current,HttpStatus.OK);
    }

    @GetMapping("/subscribed/{clientId}/{offerId}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<Boolean> getIfSubscribed(@PathVariable Long clientId, @PathVariable Long offerId){
        if (clientService.findIfSubscribed(clientId, offerId))
            return ResponseEntity.ok(true);
        return ResponseEntity.ok(false);
    }

    @GetMapping("/subscribed/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<SubscriebedEntitiesDTO>> getEntitiesClientSubscribed(@PathVariable Long id){
        List<Offer> offers = clientService.getEntitiesClientSubscribedFor(id);
        List<SubscriebedEntitiesDTO> res = new ArrayList<>();
        for (Offer o : offers){
            res.add(new SubscriebedEntitiesDTO(o));
        }
        return ResponseEntity.ok(res);
    }
}
