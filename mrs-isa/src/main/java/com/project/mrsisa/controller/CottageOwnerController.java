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

import com.project.mrsisa.domain.CottageOwner;
import com.project.mrsisa.domain.LoyaltyScale;
import com.project.mrsisa.dto.CottageOwnerProfileResponseDTO;
import com.project.mrsisa.service.CottageOwnerService;
import com.project.mrsisa.service.LoyaltyScaleService;

@RestController
@RequestMapping(value = "/cottage_owner", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class CottageOwnerController {
	
	@Autowired
	private CottageOwnerService coService;
	@Autowired
	private LoyaltyScaleService loyaltyScaleService;
    @Autowired
    private TokenUtils tokenUtils;

	@GetMapping("/profile/{id}")
    @PreAuthorize("hasRole('COTTAGE_OWNER')")
    public ResponseEntity<CottageOwnerProfileResponseDTO> getCottageOwner(@PathVariable Long id){
        
        CottageOwner co = null;
       try {
        co = coService.findOne(id);
        if (co == null) {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
       
       }catch (Exception e) {
		e.printStackTrace();
       }
        
        LoyaltyScale ls = loyaltyScaleService.loyaltyScalesGreaterMinimumTrashhold(co.getLoyaltyPoints());
        int discount;
        if (ls == null) {
        	discount = 0;
        }else {
        	discount = ls.getDiscount();
        }
 
        return new ResponseEntity<CottageOwnerProfileResponseDTO>(new CottageOwnerProfileResponseDTO(co, discount), HttpStatus.OK);
    }
	
	@PostMapping(value = "update/profile/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @PreAuthorize("hasRole('COTTAGE_OWNER')")
    public ResponseEntity<CottageOwnerProfileResponseDTO> updateCottageOwner(@PathVariable Long id, @RequestBody CottageOwnerProfileResponseDTO clientDTO){
        
        CottageOwner co = null;
       try {
        co = coService.findOne(id);
        if (co == null) {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        co.setName(clientDTO.getName());
        
        co.setSurname(clientDTO.getSurname());
        
        co.setPhoneNumber(clientDTO.getPhoneNumber());
        
        if (clientDTO.getPassword() != "") {
        	co.setPassword(clientDTO.getPassword());
        }

        co.getAddress().setLatitude(clientDTO.getLatitude());
        co.getAddress().setLongitude(clientDTO.getLongitude());

        coService.save(co);
        
       }catch (Exception e) {
		e.printStackTrace();
       }

       return new ResponseEntity<CottageOwnerProfileResponseDTO>(new CottageOwnerProfileResponseDTO(co, 5), HttpStatus.OK);
    }
}
