package com.project.mrsisa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.project.mrsisa.service.CottageOwnerService;
import com.project.mrsisa.service.CottageService;
@RestController
@RequestMapping(value = "/cottage")
public class CottageController {
	@Autowired
	private CottageService cottageService;
	@Autowired
	private CottageOwnerService cottageOwnerService;
	
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
	
	
	@GetMapping(value="/all")
	@PreAuthorize("hasRole('COTTAGE_OWNER')")
	public ResponseEntity<List<FindCottageDTO>> getAllCottages() {

		List<Cottage> cottages = cottageService.findAll();
		List<FindCottageDTO> cottageDTO = new ArrayList<>();
		for (Cottage c : cottages) {
			cottageDTO.add(new FindCottageDTO(c));
		}

		return new ResponseEntity<>(cottageDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/detail/{id}")
    @PreAuthorize("hasRole('COTTAGE_OWNER')")
	public ResponseEntity<FindCottageDTO> getCottage(@PathVariable Long id) {
		System.out.println("IN CONTROLLER");

		Cottage cottage = null;
		try {
			cottage = cottageService.findOne(id);
			if(cottage == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		FindCottageDTO cottageDTO = new FindCottageDTO(cottage);
		
        return new ResponseEntity<FindCottageDTO>(cottageDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('COTTAGE_OWNER')")
	public ResponseEntity<List<FindCottageDTO>> getCottagesByOwner(@PathVariable Long id) {
		CottageOwner owner = cottageOwnerService.findOne(id);
		List<Cottage> cottages = cottageService.getCottagesByOwner(owner);

		List<FindCottageDTO> cottagesDTO = new ArrayList<>();
		for (Cottage c : cottages) {
			cottagesDTO.add(new FindCottageDTO(c));
		}
		return new ResponseEntity<>(cottagesDTO, HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{id}")
	@PreAuthorize("hasRole('COTTAGE_OWNER')")
	public ResponseEntity<Boolean> deleteCottage(@PathVariable Long id) {

		Cottage cottage = cottageService.findOne(id);

		if (cottage != null) {
			cottageService.remove(id);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(false,HttpStatus.NOT_FOUND);
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
