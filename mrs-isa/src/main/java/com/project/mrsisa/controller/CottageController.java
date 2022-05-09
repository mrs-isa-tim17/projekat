package com.project.mrsisa.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.project.mrsisa.domain.AdditionalServices;
import com.project.mrsisa.domain.Address;
import com.project.mrsisa.domain.BehaviorRule;
import com.project.mrsisa.domain.CancelCondition;
import com.project.mrsisa.domain.Cottage;
import com.project.mrsisa.domain.CottageOwner;
import com.project.mrsisa.domain.Image;
import com.project.mrsisa.domain.Pricelist;
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
	
	@Autowired
	private BehaviorRuleService behaviorRuleService;
	
	@Autowired
	private AdditionalServicesService additionalServicesService;
	
	@Autowired
	private CancelConditionService cancelConditionService;

	@GetMapping(value = "/site/all")
	public ResponseEntity<List<CottageForListViewDTO>> getCottages(){
		List<Cottage> cottages = cottageService.findAll();
		List<CottageForListViewDTO> cottagesDTO = new ArrayList<>();
		System.out.println("Number of cottages: " + cottages.size());
		for (Cottage c : cottages) {
			c.setImages(imageService.findAllByOfferId(c.getId()));
			CottageForListViewDTO dto = new CottageForListViewDTO(c);
			dto.setPrice(pricelistService.getCurrentPriceOfOffer(c.getId()));
			dto.setMark(experienceReviewService.getReatingByOfferId(c.getId()));
			cottagesDTO.add(dto);
		}
		System.out.println("Number of cottagesDTO: " + cottagesDTO.size());
		return ResponseEntity.ok(cottagesDTO);
	}

	@PostMapping(value="/save", consumes = "application/json")
	@PreAuthorize("hasRole('COTTAGE_OWNER')")
	public ResponseEntity<CreateUpdateCottageDTO> saveCottage(@RequestBody CreateUpdateCottageDTO cottageDTO) {
		System.out.println("cuvanje vikendice");
		Cottage cottage = new Cottage();
		//cottage.setId(cottageDTO.getId());
		//cottage.setAddress(cottageDTO.getAddress());
		//cottage.setOwner(cottageDTO.getOwner());
		Address a = new Address();
		a.setLatitude(cottageDTO.getLatitude());
		a.setLongitude(cottageDTO.getLongitude());
		cottage.setAddress(a);
		cottage.setBedQuantity(cottageDTO.getBedQuantity());
		cottage.setRoomQuantity(cottageDTO.getRoomQuantity());
		cottage.setName(cottageDTO.getName());
		cottage.setDescription(cottageDTO.getDescription());
		cottage.setDeleted(cottageDTO.isDeleted());
		List<BehaviorRule> rules = new ArrayList<BehaviorRule>();
		for(String rule : cottageDTO.getBehavioralRules()) {
			rules.add(behaviorRuleService.findOneByText(rule));
		}
		cottage.setBehaviorRules(rules);
		List<AdditionalServices> additionalService = new ArrayList<AdditionalServices>();
		for(String service : cottageDTO.getAdditionalServices()) {
			additionalService.add(additionalServicesService.findOneByName(service));
		}
		cottage.setAdditionalServices(additionalService);
		
		List<CancelCondition> cancelConditions = new ArrayList<CancelCondition>();

		CancelCondition c1 = new CancelCondition(5, Double.parseDouble(cottageDTO.getPercent1()));
		cancelConditions.add(c1);
		cancelConditionService.save(c1);
		
		CancelCondition c2 = new CancelCondition(10, Double.parseDouble(cottageDTO.getPercent2()));
		cancelConditions.add(c2);
		cancelConditionService.save(c2);
		
		CancelCondition c3 = new CancelCondition(15, Double.parseDouble(cottageDTO.getPercent3()));
		cancelConditions.add(c3);
		cancelConditionService.save(c3);
		
		CancelCondition c4 = new CancelCondition(20, Double.parseDouble(cottageDTO.getPercent4()));
		cancelConditions.add(c4);
		cancelConditionService.save(c4);
		
		Pricelist pricelist = new Pricelist(cottageDTO.getPrice());
		pricelist.setOffer(cottage);
		
		ArrayList<Pricelist> pricelists = new ArrayList<Pricelist>();
		pricelists.add(pricelist);
		cottage.setPricelists(pricelists);
		
		cottage.setCancelCondition(cancelConditions);
		cottage = cottageService.save(cottage);
		return new ResponseEntity<>(new CreateUpdateCottageDTO(cottage), HttpStatus.CREATED);
	}
	
	
	@GetMapping(value="/all")
	@PreAuthorize("hasRole('COTTAGE_OWNER')")
	public ResponseEntity<List<FindCottageDTO>> getAllCottages() {

		List<Cottage> cottages = cottageService.findAll();
		List<FindCottageDTO> cottageDTO = new ArrayList<>();
		for (Cottage c : cottages) {
			 List<BehaviorRule> rules = behaviorRuleService.findAllByOfferId(c.getId());
			 List<Image> images = imageService.findAllByOfferId(c.getId());
			 List<CancelCondition> cancelConditions = cancelConditionService.findAllByOfferId(c.getId());
			 cottageDTO.add(new FindCottageDTO(c,rules,images, cancelConditions));
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
		 List<BehaviorRule> rules = behaviorRuleService.findAllByOfferId(cottage.getId());
		 List<Image> images = imageService.findAllByOfferId(cottage.getId());
		 List<CancelCondition> cancelConditions = cancelConditionService.findAllByOfferId(cottage.getId());
		 FindCottageDTO cottageDTO = new FindCottageDTO(cottage,rules,images,cancelConditions);
		
        return new ResponseEntity<FindCottageDTO>(cottageDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('COTTAGE_OWNER')")
	public ResponseEntity<List<FindCottageDTO>> getCottagesByOwner(@PathVariable Long id) {
		CottageOwner owner = cottageOwnerService.findOne(id);
		List<Cottage> cottages = cottageService.getCottagesByOwner(owner);

		List<FindCottageDTO> cottagesDTO = new ArrayList<>();
		
		
		for (Cottage c : cottages) {
			 List<BehaviorRule> rules = behaviorRuleService.findAllByOfferId(c.getId());
			 List<Image> images = imageService.findAllByOfferId(c.getId());
			 List<CancelCondition> cancelConditions = cancelConditionService.findAllByOfferId(c.getId());
			 cottagesDTO.add(new FindCottageDTO(c,rules,images,cancelConditions));
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
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,value="/update")
	public ResponseEntity<CreateUpdateCottageDTO> updateCottage(@RequestBody CreateUpdateCottageDTO cottageDTO) {


		Cottage cottage = cottageService.findOne(cottageDTO.getId());

		if (cottage == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		//cottage.setAddress(cottageDTO.getAddress());
		//cottage.setOwner(cottageDTO.getOwner());
		cottage.setBedQuantity(cottageDTO.getBedQuantity());
		cottage.setName(cottageDTO.getName());
		cottage.setDescription(cottageDTO.getDescription());

	

		cottage = cottageService.save(cottage);
		return new ResponseEntity<>(new CreateUpdateCottageDTO(cottage), HttpStatus.OK);
	}
	
	
}
