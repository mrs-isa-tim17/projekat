package com.project.mrsisa.controller;

import java.util.ArrayList;
import java.util.List;

import com.project.mrsisa.domain.Ship;
import com.project.mrsisa.dto.simple_user.AdventureForListViewDTO;
import com.project.mrsisa.dto.simple_user.ShipForListViewDTO;
import com.project.mrsisa.service.ExperienceReviewService;
import com.project.mrsisa.service.ImageService;
import com.project.mrsisa.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.mrsisa.domain.Adventure;
import com.project.mrsisa.dto.AdventureDTO;
import com.project.mrsisa.service.AdventureService;

@RestController
@RequestMapping(value="/adventure", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdventureController {
	
	@Autowired
	private AdventureService adventureService;

	@Autowired
	private ImageService imageService;

	@Autowired
	private PriceService priceService;

	@Autowired
	private ExperienceReviewService experienceReviewService;

	@GetMapping(value = "/site/all")
	public ResponseEntity<List<AdventureForListViewDTO>> getAdventures(){
		List<Adventure> adventures = adventureService.findAll();
		List<AdventureForListViewDTO> shipsDTO = new ArrayList<>();
		for (Adventure adventure : adventures) {
			adventure.setImages(imageService.findAllByOfferId(adventure.getId()));
			AdventureForListViewDTO dto = new AdventureForListViewDTO(adventure);
			dto.setPrice(priceService.getCurrentPriceOfOffer(adventure.getId()));
			dto.setMark(experienceReviewService.getReatingByOfferId(adventure.getId()));
			shipsDTO.add(dto);
		}
		return ResponseEntity.ok(shipsDTO);
	}

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
