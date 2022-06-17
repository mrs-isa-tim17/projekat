package com.project.mrsisa.controller;

import com.project.mrsisa.domain.Ship;
import com.project.mrsisa.dto.PeriodDTO;
import com.project.mrsisa.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.project.mrsisa.domain.LoyaltyScale;
import com.project.mrsisa.domain.ShipOwner;
import com.project.mrsisa.dto.owner.ShipOwnerProfileResponseDTO;
import com.project.mrsisa.service.LoyaltyScaleService;
import com.project.mrsisa.service.ShipOwnerService;
import com.project.mrsisa.util.TokenUtils;

@RestController
@RequestMapping(value = "/ship_owner", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class ShipOwnerController {
    @Autowired
	public ShipOwnerService shipOwnerService;
	@Autowired
	private LoyaltyScaleService loyaltyScaleService;
    @Autowired
    private TokenUtils tokenUtils;

		@GetMapping("/profile/{id}")
	    @PreAuthorize("hasRole('SHIP_OWNER')")
	    public ResponseEntity<ShipOwnerProfileResponseDTO> getShipOwner(@PathVariable Long id){
	        
	        ShipOwner so = null;
	       try {
	        so = shipOwnerService.findOne(id);
	        if (so == null) {
	        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	       
	       }catch (Exception e) {
			e.printStackTrace();
	       }
	        
	        LoyaltyScale ls = loyaltyScaleService.loyaltyScalesGreaterMinimumTrashhold(so.getLoyaltyPoints());
	        int discount;
	        if (ls == null) {
	        	discount = 0;
	        }else {
	        	discount = ls.getDiscount();
	        }
	 
	        return new ResponseEntity<ShipOwnerProfileResponseDTO>(new ShipOwnerProfileResponseDTO(so, discount), HttpStatus.OK);
	    }
		
		@PostMapping(value = "update/profile/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
	    @PreAuthorize("hasRole('SHIP_OWNER')")
	    public ResponseEntity<ShipOwnerProfileResponseDTO> updateShipOwner(@PathVariable Long id, @RequestBody ShipOwnerProfileResponseDTO shipOwnerDTO){
	        
	        ShipOwner so = null;
			ShipOwner updatedOwner = null;
	       try {
	        so = shipOwnerService.findOne(id);
	        if (so == null) {
	        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        so.setName(shipOwnerDTO.getName());
	        
	        so.setSurname(shipOwnerDTO.getSurname());
	        
	        so.setPhoneNumber(shipOwnerDTO.getPhoneNumber());
	        
	        if (shipOwnerDTO.getPassword() != "") {
	        	so.setPassword(shipOwnerDTO.getPassword());
	        }

	        so.getAddress().setLatitude(shipOwnerDTO.getLatitude());
	        so.getAddress().setLongitude(shipOwnerDTO.getLongitude());

	        updatedOwner = shipOwnerService.save(so);
	        
	       }catch (Exception e) {
			e.printStackTrace();
	       }

	       return new ResponseEntity<ShipOwnerProfileResponseDTO>(new ShipOwnerProfileResponseDTO(updatedOwner), HttpStatus.OK);
	    }

		@PostMapping("{id}/free")
		@PreAuthorize("hasRole('CLIENT')")
		public ResponseEntity<Boolean> getIfOwnerFree(@PathVariable long id, @RequestBody PeriodDTO periodDTO){
			if (shipOwnerService.checkIfFreeInPeriod(id, periodDTO))
				return ResponseEntity.ok(true);
			else
				return ResponseEntity.ok(false);
		}
}
