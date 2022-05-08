package com.project.mrsisa.controller;

import java.util.ArrayList;
import java.util.List;

import com.project.mrsisa.domain.OfferType;
import com.project.mrsisa.dto.simple_user.CottageForListViewDTO;
import com.project.mrsisa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

@RestController
@RequestMapping(value = "/cottage", produces = MediaType.APPLICATION_JSON_VALUE)
public class CottageController {
	@Autowired
	private CottageOwnerService cottageOwnerService;

	@Autowired
	private CottageService cottageService;

	@Autowired
	private ImageService imageService;

	@Autowired
	private PricelistService pricelistService;

	@Autowired
	private ExperienceReviewService experienceReviewService;

	@GetMapping(value = "/site/all")
	public ResponseEntity<List<CottageForListViewDTO>> getCottages(){
		List<Cottage> cottages = cottageService.findAll();
		List<CottageForListViewDTO> cottagesDTO = new ArrayList<>();
		System.out.println("Number of cottages: " + cottages.size());
		for (Cottage c : cottages) {
			c.setImages(imageService.findAllByOfferId(c.getId()));
			CottageForListViewDTO dto = new CottageForListViewDTO(c);
			dto.setPrice(pricelistService.getCurrentPriceOfOffer(c.getId()));
			dto.setMark(experienceReviewService.getReatingByOfferId(c.getId(), OfferType.COTTAGE));
			cottagesDTO.add(dto);
		}
		System.out.println("Number of cottagesDTO: " + cottagesDTO.size());
		return ResponseEntity.ok(cottagesDTO);
	}

	@PostMapping(consumes = "application/json")
	@PreAuthorize("hasRole('COTTAGE_OWNER')")
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
