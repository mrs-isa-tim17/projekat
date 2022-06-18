package com.project.mrsisa.controller;

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

import com.project.mrsisa.domain.Admin;
import com.project.mrsisa.domain.LoyaltyScale;
import com.project.mrsisa.dto.owner.CottageOwnerProfileResponseDTO;
import com.project.mrsisa.service.AdminService;
import com.project.mrsisa.service.LoyaltyScaleService;
import com.project.mrsisa.util.TokenUtils;


@RestController
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private LoyaltyScaleService loyaltyScaleService;
    @Autowired
    private TokenUtils tokenUtils;

	@GetMapping("/profile/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CottageOwnerProfileResponseDTO> getCottageOwner(@PathVariable Long id){
        
        Admin admin = null;
       try {
    	   admin = adminService.findOne(id);
        if (admin == null) {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
       
       }catch (Exception e) {
		e.printStackTrace();
       }
        
        LoyaltyScale ls = loyaltyScaleService.loyaltyScalesGreaterMinimumTrashhold(admin.getLoyaltyPoints());
        int discount;
        if (ls == null) {
        	discount = 0;
        }else {
        	discount = ls.getDiscount();
        }
 
        return new ResponseEntity<CottageOwnerProfileResponseDTO>(new CottageOwnerProfileResponseDTO(admin, discount), HttpStatus.OK);
    }
	
	@PostMapping(value = "update/profile/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CottageOwnerProfileResponseDTO> updateCottageOwner(@PathVariable Long id, @RequestBody CottageOwnerProfileResponseDTO clientDTO){
        
		Admin admin = null;
       try {
    	   admin = adminService.findOne(id);
        if (admin == null) {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        admin.setName(clientDTO.getName());
        
        admin.setSurname(clientDTO.getSurname());
        
        admin.setPhoneNumber(clientDTO.getPhoneNumber());
        
        if (clientDTO.getPassword() != "") {
        	admin.setPassword(clientDTO.getPassword());
        }

        admin.getAddress().setLatitude(clientDTO.getLatitude());
        admin.getAddress().setLongitude(clientDTO.getLongitude());

        adminService.save(admin);
        
       }catch (Exception e) {
		e.printStackTrace();
       }

       return new ResponseEntity<CottageOwnerProfileResponseDTO>(new CottageOwnerProfileResponseDTO(admin, 5), HttpStatus.OK);
    }
}



