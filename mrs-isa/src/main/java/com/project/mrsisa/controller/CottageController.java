package com.project.mrsisa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.mrsisa.domain.Cottage;
import com.project.mrsisa.dto.CottageDTO;
import com.project.mrsisa.service.CottageService;


public class CottageController {
	@Autowired
	private CottageService cottageService;
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<CottageDTO> saveCottage(@RequestBody CottageDTO cottageDTO) {

		Cottage cottage = new Cottage();
		cottage.setId(cottageDTO.getId());
		cottage.setAddress(cottageDTO.getAddress());
		cottage.setOwner(cottageDTO.getOwner());
		cottage.setBedQuantity(cottageDTO.getBedQuantity());
		cottage.setName(cottageDTO.getName());
		cottage.setDescription(cottageDTO.getDescription());
		cottage.setDeleted(cottageDTO.isDeleted());

		cottage = cottageService.save(cottage);
		return new ResponseEntity<>(new CottageDTO(cottage), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<CottageDTO>> getAllCottages() {

		List<Cottage> cottages = cottageService.findAll();
		List<CottageDTO> cottageDTO = new ArrayList<>();
		for (Cottage c : cottages) {
			cottageDTO.add(new CottageDTO(c));
		}

		return new ResponseEntity<>(cottageDTO, HttpStatus.OK);
	}

}
