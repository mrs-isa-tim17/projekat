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

import com.project.mrsisa.domain.FishingEquipment;
import com.project.mrsisa.service.FishingEquipmentService;

@RestController
@RequestMapping(value="/fishing", produces = MediaType.APPLICATION_JSON_VALUE)
public class FishingEquipmentController {
	@Autowired
	private FishingEquipmentService fishingEquipmentService;
	
	
	@GetMapping(value = "/all")
    @PreAuthorize("hasRole('FISHINSTRUCTOR') or hasRole('SHIP_OWNER')")
	public ResponseEntity<List<String>> getFishingEquipments(){
		List<FishingEquipment> fishing = fishingEquipmentService.findAll();
		List<String> fishingDTO = new ArrayList<String>();
		
		for (FishingEquipment fi : fishing) {
			fishingDTO.add(fi.getName());
		}
		return new ResponseEntity<>(fishingDTO, HttpStatus.OK);
	}


}
