package com.project.mrsisa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mrsisa.domain.Address;
import com.project.mrsisa.domain.Adventure;
import com.project.mrsisa.domain.Place;
import com.project.mrsisa.dto.AdventureDTO;
import com.project.mrsisa.service.AdventureService;


@RestController
@RequestMapping(value="/adventure")
public class AdventureController {
	
	//@Autowired
	//private AdventureService adventureService;
	
	/*public AdventureController (AdventureService adventureService2) {
		this.adventureService = adventureService2;
	}*/
	
	
	@GetMapping(value = "/detail/{id}")
	public AdventureDTO getAdventure(@PathVariable Long id) {
		System.out.println("IN CONTROLLER");
		Place p = new Place();
		p.setId(new Long(1));
		p.setCountry("Srbija");
		p.setPlaceName("Bajina Basta");
		p.setPostNumber(32000);
		Address a = new Address();
		ArrayList<Address> ad = new ArrayList<Address>();
		ad.add(a);
		p.setAddresses(new ArrayList<Address> (ad));
		
		a.setId(new Long(2));
		a.setPlace(p);
		a.setSerialNumber("32");
		a.setStreetName("Puskinova");
		
		return new AdventureDTO("RAfting", a , "najbolja zabava ikaaad");
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
