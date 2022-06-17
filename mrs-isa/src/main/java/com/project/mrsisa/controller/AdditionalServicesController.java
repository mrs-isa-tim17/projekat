package com.project.mrsisa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mrsisa.domain.AdditionalServices;
import com.project.mrsisa.service.AdditionalServicesService;

@RestController
@RequestMapping(value="/additional", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdditionalServicesController {
	@Autowired
	private AdditionalServicesService additionalServicesService;
	
	
	@GetMapping(value = "/all")
    @PreAuthorize("hasRole('FISHINSTRUCTOR') or hasRole('COTTAGE_OWNER') or hasRole('SHIP_OWNER')")
	public ResponseEntity<List<String>> getAdditionalServices(){
		List<AdditionalServices> additional = additionalServicesService.findAll();
		List<String> additionalDTO = new ArrayList<String>();
		
		for (AdditionalServices ad : additional) {
			additionalDTO.add(ad.getName());
		}
		return new ResponseEntity<>(additionalDTO, HttpStatus.OK);
	}


}
