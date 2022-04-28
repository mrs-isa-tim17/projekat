package com.project.mrsisa.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mrsisa.domain.AdditionalServices;
import com.project.mrsisa.domain.Adventure;
import com.project.mrsisa.domain.BehaviorRule;
import com.project.mrsisa.domain.CancelCondition;
import com.project.mrsisa.domain.Client;
import com.project.mrsisa.domain.ExperienceReview;
import com.project.mrsisa.domain.FishingEquipment;
import com.project.mrsisa.domain.Image;
import com.project.mrsisa.domain.Pricelist;
import com.project.mrsisa.dto.AdventureDTO;
import com.project.mrsisa.service.AdditionalServicesService;
import com.project.mrsisa.service.AdventureService;
import com.project.mrsisa.service.BehaviorRuleService;
import com.project.mrsisa.service.CancelConditionService;
import com.project.mrsisa.service.ClientService;
import com.project.mrsisa.service.ExperienceReviewService;
import com.project.mrsisa.service.FishingEquipmentService;
import com.project.mrsisa.service.ImageService;
import com.project.mrsisa.service.PricelistService;


@RestController
@RequestMapping(value="/adventure", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdventureController {
	
	@Autowired
	private AdventureService adventureService;
	
	@Autowired
	private BehaviorRuleService behaviorRuleService;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private FishingEquipmentService fishingEquipmentService;
	
	@Autowired
	private CancelConditionService cancelConditionService;
	
	@Autowired
	private ExperienceReviewService experienceReviewService;
	
	@Autowired
	private AdditionalServicesService additionalServicesService;
	
	@Autowired
	private PricelistService pricelistService;
	
	
	@GetMapping(value = "/detail/{id}")
	public ResponseEntity<AdventureDTO> getAdventure(@PathVariable Long id) {
		System.out.println("IN CONTROLLER");

		Adventure adventure = null;
		try {
			adventure = adventureService.findOneById(id);
			if(adventure == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Long adventureId = adventure.getId(); 
		List<BehaviorRule> behaviorRules = behaviorRuleService.findAllByAdventureId(adventureId);
		List<Image> images = imageService.findAllByAdventureId(adventureId);
		List<FishingEquipment> fishEquipment = fishingEquipmentService.findAllByAdventureId(adventureId);
		List<CancelCondition> cancelConditions = cancelConditionService.findAllByAdventureId(adventureId);
		List<ExperienceReview> experience =  experienceReviewService.findAllByOfferId(adventureId);
		List<AdditionalServices> additionalServices = additionalServicesService.findAllByAdventureId(adventureId);
		List<Pricelist> pricelists = pricelistService.findAllByAdventureId(adventureId);
		
		double price = pricelists.get(0).getPrice();
		System.out.println("size za role: " + behaviorRules.size());
		System.out.println("image siye : "  + images.size());
		System.out.println("fishing equipment siye : "  + fishEquipment.size());
		System.out.println("cancel condition siye : "  + cancelConditions.size());
		System.out.println("experience review siye : "  + experience.size());
		System.out.println("addition siye : "  + additionalServices.size());
		
		AdventureDTO adventureDTO = new AdventureDTO(adventure, behaviorRules, images, fishEquipment, cancelConditions, experience, additionalServices, price);
			
		System.out.println(adventureDTO);
		
        return new ResponseEntity<AdventureDTO>(adventureDTO, HttpStatus.OK);

	}
	
	
	
/*
	@PostMapping(consumes="applicatiton/json")
	public ResponseEntity<AdventureDTO> saveAdventure(@RequestBody AdventureDTO adventureDTO){
		
		Adventure adventure = new Adventure();
		adventure.setAddress(adventureDTO.getAddress());
		adventure.setName(adventureDTO.getName());
		adventure.setDescription(adventure.getDescription());
		adventure.setDeleted(adventureDTO.isDeleted());
		
		adventure.setExperienceReviews(adventureDTO.getExperienceReviews());
		adventure.setAdditionalServices(adventureDTO.getAdditionalServices());
		adventure.setBehaviorRules(adventureDTO.getBehaviorRules());
		adventure.setImages(adventureDTO.getImages());
		adventure.setCancelCondition(adventureDTO.getCancelCondition());
		adventure.setPricelists(adventureDTO.getPricelists());	
		adventure.setCalendar(adventureDTO.getCalendar());
		
		
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
	
*/
}
