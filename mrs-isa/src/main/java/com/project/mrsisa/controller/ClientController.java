package com.project.mrsisa.controller;

import com.project.mrsisa.dto.UserTokenState;
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
import com.project.mrsisa.service.ClientService;
import com.project.mrsisa.service.LoyaltyScaleService;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/verify/{code}")
    public ResponseEntity<UserTokenState> verifyAccount(@PathVariable("code") String code){
        Client c = clientService.verify(code);
        if (c == null){
            return ResponseEntity.ok(null);
        }
        //System.out.println(c.getEmail());
        String jwt = tokenUtils.generateToken(c.getUsername());
        int expiresIn = tokenUtils.getExpiredIn();
        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn, c.getRoleId(), c.getId()));//new ResponseEntity<Client>(c, HttpStatus.OK);
        //return new ModelAndView("login.vue");//"redirect:".concat("http://localhost:8081/book/site/login");//
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
