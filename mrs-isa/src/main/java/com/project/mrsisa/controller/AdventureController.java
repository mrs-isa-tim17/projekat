package com.project.mrsisa.controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import com.project.mrsisa.domain.*;
import com.project.mrsisa.dto.simple_user.AdventureForListViewDTO;
import com.project.mrsisa.dto.simple_user.OfferForHomePageViewDTO;
import com.project.mrsisa.dto.simple_user.ShipForListViewDTO;

import com.project.mrsisa.dto.simple_user.AdventureForListViewDTO;

import com.project.mrsisa.service.ExperienceReviewService;
import com.project.mrsisa.service.ImageService;
import com.project.mrsisa.service.PeriodAvailabilitySerivce;
import com.project.mrsisa.service.PeriodUnavailabilityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mrsisa.dto.AdventureDTO;
import com.project.mrsisa.dto.StartEndDateDTO;
import com.project.mrsisa.service.AdditionalServicesService;
import com.project.mrsisa.service.AdventureService;
import com.project.mrsisa.service.BehaviorRuleService;
import com.project.mrsisa.service.CancelConditionService;
import com.project.mrsisa.service.FishingEquipmentService;
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
    @PreAuthorize("hasRole('FISHINSTRUCTOR')")
	public ResponseEntity<AdventureDTO> getAdventure(@PathVariable Long id) {
		System.out.println("IN CONTROLLER");

		Adventure adventure = null;
		try {
			adventure = adventureService.findOneById(id);
			if (adventure == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		AdventureDTO adventureDTO = formAdventureDTO(adventure);

		return new ResponseEntity<AdventureDTO>(adventureDTO, HttpStatus.OK);

	}

	@GetMapping(value = "/site/all")
	public ResponseEntity<List<AdventureForListViewDTO>> getAdventures(){
		List<Adventure> adventures = adventureService.findAll();
		List<AdventureForListViewDTO> shipsDTO = new ArrayList<>();
		for (Adventure adventure : adventures) {
			adventure.setImages(imageService.findAllByOfferId(adventure.getId()));
			AdventureForListViewDTO dto = new AdventureForListViewDTO(adventure);
			dto.setPrice(pricelistService.getCurrentPriceOfOffer(adventure.getId()));
			dto.setMark(experienceReviewService.getReatingByOfferId(adventure.getId(), OfferType.ADVENTURE));
			shipsDTO.add(dto);
		}
		return ResponseEntity.ok(shipsDTO);
	}

	@GetMapping(value = "/site/short")
	public ResponseEntity<List<OfferForHomePageViewDTO>> getAdventuresForHomePage(){
		List<Adventure> adventures = adventureService.findAll();
		List<OfferForHomePageViewDTO> cottagesDTO = new ArrayList<>();
		for (Adventure c : adventures) {
			c.setImages(imageService.findAllByOfferId(c.getId()));
			OfferForHomePageViewDTO dto = new OfferForHomePageViewDTO(c);
			cottagesDTO.add(dto);
		}
		return ResponseEntity.ok(cottagesDTO);
	}

	public AdventureController(AdventureService adventureService2) {
		this.adventureService = adventureService2;

	}
	

	@PostMapping(value="/detail/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('FISHINSTRUCTOR')")
	public ResponseEntity<AdventureDTO> saveAdventure(@RequestBody AdventureDTO adventureDTO){
		
		
		System.out.println("u kontroleru sam");

		Adventure adventure = formAdventure(adventureDTO);
		adventureService.save(adventure);
		return new ResponseEntity<>(adventureDTO , HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/detail/update", consumes=MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('FISHINSTRUCTOR')")
	public ResponseEntity<AdventureDTO> updateAdventure(@RequestBody AdventureDTO adventureDTO) {

		System.out.println("update controler");
		
		Adventure adventure = adventureService.findOneById(adventureDTO.getId());
	
		
		if (adventure == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		System.out.println("ID: " + adventure.getId());
		
		adventure = this.updateAdventure(adventure, adventureDTO);
	
		
		adventure = adventureService.save(adventure);
		
		System.out.println("update in database");
		AdventureDTO newadvDTo = formAdventureDTO(adventure);
		
		return new ResponseEntity<>(newadvDTo, HttpStatus.OK);
	}
	
	
	@DeleteMapping(value = "/detail/delete/{id}")
	@PreAuthorize("hasRole('FISHINSTRUCTOR')")
	public ResponseEntity<Boolean> deleteAdventure(@PathVariable Long id) {
		
		System.out.println("delete - in controller");

		Adventure adventure = adventureService.findOneById(id);

		if (adventure != null) {
			adventureService.remove(id);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/detail/all")
	@PreAuthorize("hasRole('FISHINSTRUCTOR')")
	public ResponseEntity<List<AdventureDTO>> getAllAdventures() {

		List<Adventure> adventures = adventureService.findAll();
		List<AdventureDTO> adventureDTO = new ArrayList<>();
		for (Adventure adventure : adventures) {
			adventureDTO.add(formAdventureDTO(adventure));
		}

		return new ResponseEntity<>(adventureDTO, HttpStatus.OK);
	}
	
	
	private Adventure formAdventure(AdventureDTO adventureDTO) {
		
		Adventure adventure = new Adventure();
			
		Address a = new Address();
		a.setLatitude(adventureDTO.getLatitude());
		a.setLongitude(adventureDTO.getLongitude());
		adventure.setAddress(a);
		adventure.setName(adventureDTO.getName());
		adventure.setCapacity(adventureDTO.getCapacity());
		adventure.setDeleted(false);
		adventure.setDescription(adventureDTO.getDescription());
		adventure.setInstructorBiography(adventureDTO.getInstructorBiography());
		
		List<BehaviorRule> behavoirRules = new ArrayList<BehaviorRule>();
		for(String rule : adventureDTO.getBehaviorRules())
		{
			BehaviorRule br = behaviorRuleService.findOneByText(rule);
			behavoirRules.add(br);
		}
		adventure.setBehaviorRules(behavoirRules);
		
		List<FishingEquipment> fishingEquipment = new ArrayList<FishingEquipment>();
		for(String equipmentName : adventureDTO.getFishingEquipment()) 
		{
			FishingEquipment fe = fishingEquipmentService.findOneByName(equipmentName);
			fishingEquipment.add(fe);
		}
		adventure.setFishingEquipments(fishingEquipment);
		
		List<AdditionalServices> additionalServices = new ArrayList<AdditionalServices>();
		for(String service : adventureDTO.getAdditionalServices()) 
		{
			AdditionalServices as = additionalServicesService.findOneByName(service);
			additionalServices.add(as);	
		}
		adventure.setAdditionalServices(additionalServices);
		
		
		List<CancelCondition> cancelConditions = new ArrayList<CancelCondition>();

			CancelCondition c1 = new CancelCondition(5, Double.parseDouble(adventureDTO.getP1()));
			cancelConditions.add(c1);
			cancelConditionService.save(c1);
			
			CancelCondition c2 = new CancelCondition(10, Double.parseDouble(adventureDTO.getP2()));
			cancelConditions.add(c2);
			cancelConditionService.save(c2);
			
			CancelCondition c3 = new CancelCondition(15, Double.parseDouble(adventureDTO.getP3()));
			cancelConditions.add(c3);
			cancelConditionService.save(c3);
			
			CancelCondition c4 = new CancelCondition(20, Double.parseDouble(adventureDTO.getP4()));
			cancelConditions.add(c4);
			cancelConditionService.save(c4);
		
		adventure.setCancelCondition(cancelConditions);
		
		
		Pricelist pricelist = new Pricelist(adventureDTO.getPrice());
		pricelist.setOffer(adventure);
		
		ArrayList<Pricelist> pricelists = new ArrayList<Pricelist>();
		pricelists.add(pricelist);
		adventure.setPricelists(pricelists);
		
		

		
		List<Image> images = new ArrayList<Image>();    // kasnije
				
		
		return adventure;
		
		
	}
	
	private AdventureDTO formAdventureDTO(Adventure adventure) {
		Long adventureId = adventure.getId(); 
		List<BehaviorRule> behaviorRules = behaviorRuleService.findAllByOfferId(adventureId);
		List<Image> images = imageService.findAllByAdventureId(adventureId);
		List<FishingEquipment> fishEquipment = fishingEquipmentService.findAllByAdventureId(adventureId);
		List<CancelCondition> cancelConditions = cancelConditionService.findAllByOfferId(adventureId);
		List<ExperienceReview> experience =  experienceReviewService.findAllByOfferId(adventureId);
		List<AdditionalServices> additionalServices = additionalServicesService.findAllByAdventureId(adventureId);
		Pricelist pricelist = pricelistService.findOffersCurrentPriceById(adventureId);
		double price = pricelist.getPrice();

		System.out.println("size za role: " + behaviorRules.size());
		System.out.println("image siye : "  + images.size());
		System.out.println("fishing equipment siye : "  + fishEquipment.size());
		System.out.println("cancel condition siye : "  + cancelConditions.size());
		System.out.println("experience review siye : "  + experience.size());
		System.out.println("addition siye : "  + additionalServices.size());
		System.out.println("OVDE");
		AdventureDTO adventureDTO = new AdventureDTO(adventure, behaviorRules, images, fishEquipment, cancelConditions, experience, additionalServices, price);
			
		System.out.println(adventureDTO);
		return adventureDTO;
	}

	private Adventure updateAdventure(Adventure adventure, AdventureDTO adventureDTO) {
		
		adventure.setName(adventureDTO.getName());
		adventure.getAddress().setLatitude(adventureDTO.getLatitude());
		adventure.getAddress().setLongitude(adventureDTO.getLongitude());
		adventure.setCapacity(adventureDTO.getCapacity());
		System.out.println("++++" +adventureDTO.getCapacity());
		System.out.println("++++++++"+adventure.getCapacity());
		adventure.setDescription(adventureDTO.getDescription());
		adventure.setInstructorBiography(adventureDTO.getInstructorBiography());

		List<AdditionalServices> aService = new ArrayList<AdditionalServices>();
		for(String as : adventureDTO.getAdditionalServices()) 
		{
			aService.add(additionalServicesService.findOneByName(as));
		}
		adventure.setAdditionalServices(aService);

		List<BehaviorRule> bRules = new ArrayList<BehaviorRule>();
		for(String as : adventureDTO.getBehaviorRules()) 
		{
			bRules.add(behaviorRuleService.findOneByText(as));	
		}
		adventure.setBehaviorRules(bRules);
		
		List<FishingEquipment> fEquipment = new ArrayList<FishingEquipment>();
		for(String fe : adventureDTO.getFishingEquipment()) 
		{
			fEquipment.add(fishingEquipmentService.findOneByName(fe));	
		}
		adventure.setFishingEquipments(fEquipment);
		
		List<CancelCondition> cCondition = new ArrayList<CancelCondition>();
		for(int i = 0; i < adventureDTO.getDays().size(); i++) 
		{
			int day = Integer.parseInt(adventureDTO.getDays().get(i));
			CancelCondition cc = cancelConditionService.findOneByOfferIdAndDays(adventureDTO.getId(), day);
			switch (day) {
			case 5:
				cc.setPrecent(Double.parseDouble(adventureDTO.getP1()));
				break;
			case 10:
				cc.setPrecent(Double.parseDouble(adventureDTO.getP2()));
				break;
			case 15:
				cc.setPrecent(Double.parseDouble(adventureDTO.getP3()));
				break;

			case 20:
				cc.setPrecent(Double.parseDouble(adventureDTO.getP4()));
				break;
			default:
				break;
			}
			cCondition.add(cc);
			cancelConditionService.save(cc);
		}
		adventure.setCancelCondition(cCondition);
		

		Pricelist pricelist = new Pricelist(adventureDTO.getPrice());
		List<Pricelist> pricelists = pricelistService.findAllByAdventureId(adventureDTO.getId());
		for(Pricelist p : pricelists) 
		{
			if(p.getEndDate() == null) {
				p.setEndDate(LocalDate.now());
			}
		}
		pricelists.add(pricelist);
		pricelist.setOffer(adventure);
		adventure.setPricelists(pricelists);
		System.out.println("priceee !!!");

		adventure.setImages(adventureDTO.getImagesAdventure());  // kasnije
		
	return adventure;
	}
}