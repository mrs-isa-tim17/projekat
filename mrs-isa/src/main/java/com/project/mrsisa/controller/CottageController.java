package com.project.mrsisa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.mrsisa.domain.Cottage;
import com.project.mrsisa.domain.CottageOwner;
import com.project.mrsisa.dto.CottageDTO;
import com.project.mrsisa.dto.cottage.CreateUpdateCottageDTO;
import com.project.mrsisa.dto.cottage.FindCottageDTO;
import com.project.mrsisa.service.CottageService;
@RestController
@RequestMapping(value = "/api/cottages")
public class CottageController {
	@Autowired
	private CottageService cottageService;
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<CreateUpdateCottageDTO> saveCottage(@RequestBody CreateUpdateCottageDTO cottageDTO) {

		Cottage cottage = new Cottage();
		cottage.setId(cottageDTO.getId());
		//cottage.setAddress(cottageDTO.getAddress());
		//cottage.setOwner(cottageDTO.getOwner());
		cottage.setBedQuantity(cottageDTO.getBedQuantity());
		cottage.setName(cottageDTO.getName());
		cottage.setDescription(cottageDTO.getDescription());
		cottage.setDeleted(cottageDTO.isDeleted());

		cottage = cottageService.save(cottage);
		return new ResponseEntity<>(new CreateUpdateCottageDTO(cottage), HttpStatus.CREATED);
	}
	
	@CrossOrigin(origins="http://localhost:8080")
	@GetMapping(value="/all")
	public ResponseEntity<List<FindCottageDTO>> getAllCottages() {

		List<Cottage> cottages = cottageService.findAll();
		List<FindCottageDTO> cottageDTO = new ArrayList<>();
		for (Cottage c : cottages) {
			cottageDTO.add(new FindCottageDTO(c));
		}

		return new ResponseEntity<>(cottageDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/findByOwner")
	public ResponseEntity<List<CottageDTO>> getCottagesByOwner(@RequestParam CottageOwner owner) {

		List<Cottage> cottages = cottageService.getCottagesByOwner(owner);

		List<CottageDTO> cottagesDTO = new ArrayList<>();
		for (Cottage c : cottages) {
			cottagesDTO.add(new CottageDTO(c));
		}
		return new ResponseEntity<>(cottagesDTO, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteCottage(@PathVariable Long id) {

		Cottage cottage = cottageService.findOne(id);

		if (cottage != null) {
			cottageService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(consumes = "application/json",value="/update")
	public ResponseEntity<CreateUpdateCottageDTO> updateCottage(@RequestBody CreateUpdateCottageDTO cottageDTO) {

		// a student must exist
		Cottage cottage = cottageService.findOne(cottageDTO.getId());

		if (cottage == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		cottage.setId(cottageDTO.getId());
		//cottage.setAddress(cottageDTO.getAddress());
		//cottage.setOwner(cottageDTO.getOwner());
		cottage.setBedQuantity(cottageDTO.getBedQuantity());
		cottage.setName(cottageDTO.getName());
		cottage.setDescription(cottageDTO.getDescription());
		cottage.setDeleted(cottageDTO.isDeleted());
	

		cottage = cottageService.save(cottage);
		return new ResponseEntity<>(new CreateUpdateCottageDTO(cottage), HttpStatus.OK);
	}
	
	
}
