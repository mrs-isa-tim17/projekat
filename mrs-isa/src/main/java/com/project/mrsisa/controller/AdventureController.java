package com.project.mrsisa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.mrsisa.domain.Adventure;
import com.project.mrsisa.dto.AdventureDTO;
import com.project.mrsisa.service.AdventureService;

public class AdventureController {
	
	@Autowired
	private AdventureService adventureService;
	
	public AdventureController(AdventureService adventureService2) {
		this.adventureService = adventureService2;
	}


	@PostMapping(consumes="applicatiton/json")
	public ResponseEntity<AdventureDTO> saveAdventure(@RequestBody AdventureDTO adventureDTO){
		
		Adventure adventure = new Adventure();
		adventure.setAddress(adventureDTO.getAddress());
		adventure.setName(adventureDTO.getName());
		adventure.setDescription(adventure.getDescription());
		adventure.setDeleted(adventureDTO.isDeleted());
		
	/*	adventure.setExperienceReviews(adventureDTO.getExperienceReviews());
		adventure.setAdditionalServices(adventureDTO.getAdditionalServices());
		adventure.setBehaviorRules(adventureDTO.getBehaviorRules());
		adventure.setImages(adventureDTO.getImages());
		adventure.setCancelCondition(adventureDTO.getCancelCondition());
		adventure.setPricelists(adventureDTO.getPricelists());	
		adventure.setCalendar(adventureDTO.getCalendar());
	*/	
		
		adventure = adventureService.save(adventure);
		return new ResponseEntity<>(new AdventureDTO(adventure), HttpStatus.CREATED);
	}
	
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<AdventureDTO>> getAllAdventures() {

		List<Adventure> adventures = adventureService.findAll();
		List<AdventureDTO> adventureDTO = new ArrayList<>();
		for (Adventure s : adventures) {
			adventureDTO.add(new AdventureDTO(s));
		}

		return new ResponseEntity<>(adventureDTO, HttpStatus.OK);
	}
	

}
