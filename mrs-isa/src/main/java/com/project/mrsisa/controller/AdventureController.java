package com.project.mrsisa.controller;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import com.project.mrsisa.domain.*;
import com.project.mrsisa.dto.simple_user.*;
import com.project.mrsisa.dto.simple_user.AdventureForListViewDTO;

import com.project.mrsisa.service.OfferService;
import com.project.mrsisa.service.*;
import com.project.mrsisa.dto.simple_user.OfferForHomePageViewDTO;

import com.project.mrsisa.service.ExperienceReviewService;
import com.project.mrsisa.service.ImageService;


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

import com.project.mrsisa.dto.AdminOfferDTO;
import com.project.mrsisa.dto.AdventureDTO;

import com.project.mrsisa.dto.ReservationForOwnerDTO;
import com.project.mrsisa.service.AdditionalServicesService;
import com.project.mrsisa.service.AdventureService;
import com.project.mrsisa.service.BehaviorRuleService;
import com.project.mrsisa.service.CancelConditionService;
import com.project.mrsisa.service.FishingEquipmentService;
import com.project.mrsisa.service.PricelistService;
import com.project.mrsisa.service.ReservationService;
import com.project.mrsisa.service.UserService;


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
	private FishingInstructorService fishingInstructorService;
	@Autowired
	private CancelConditionService cancelConditionService;
	
	@Autowired
	private ExperienceReviewService experienceReviewService;
	
	@Autowired
	private AdditionalServicesService additionalServicesService;
	
	@Autowired
	private PricelistService pricelistService;

	private OfferService offerService = new OfferService();
	@Autowired
	private PeriodAvailabilitySerivce periodAvailabilitySerivce;
	@Autowired
	private PeriodUnavailabilityService periodUnavailabilityService;
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private UserService userService;
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	@Autowired
	private ClientService clientService;

	@Autowired
	private SaleAppointmentService saleAppointmentService;
	
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
		List<Adventure> adventures = adventureService.findActiveAdventures();
		List<AdventureForListViewDTO> adventureDTO = getAdventuresForListViewDTO(adventures);
		return ResponseEntity.ok(adventureDTO);
	}

	@GetMapping(value = "/site/short")
	public ResponseEntity<List<OfferForHomePageViewDTO>> getAdventuresForHomePage(){
		List<Adventure> adventures = adventureService.findAll();
		List<OfferForHomePageViewDTO> cottagesDTO = new ArrayList<>();
		if (adventures.size() > 6){
			adventures = adventures.subList(0, 5);
		}
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
		adventureService.removeOneFromCacheById(adventure.getId());
		return new ResponseEntity<>(newadvDTo, HttpStatus.OK);
	}
	
	
	@DeleteMapping(value = "/detail/delete/{id}")
	@PreAuthorize("hasRole('FISHINSTRUCTOR') or hasRole('ADMIN')")
	public ResponseEntity<Boolean> deleteAdventure(@PathVariable Long id) {
		return ResponseEntity.ok(adventureService.deleteAdventure(id));
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


	@GetMapping(value = "/detail/all/{id}")
	@PreAuthorize("hasRole('FISHINSTRUCTOR')")
	public ResponseEntity<List<AdventureDTO>> getAdventuresByOwner(@PathVariable Long id) {
		
		List<AdventureDTO> adventuredDTO = getAdventuresForOwner(id);
		return new ResponseEntity<>(adventuredDTO, HttpStatus.OK);
	}
	
	private List<AdventureDTO> getAdventuresForOwner(Long id){
		FishingInstructor instructor = fishingInstructorService.findOne(id);
		List<Adventure> adventures = adventureService.getAdventuresByOwner(instructor);

		List<AdventureDTO> adventureDTO = new ArrayList<>();
		System.out.println("avanture" + adventures.size());
		for (Adventure adventure : adventures) {
			adventureDTO.add(formAdventureDTO(adventure));
			System.out.println("avanturaaaaaa" + adventure.getId() + adventure.getName());
		}
		return adventureDTO;
	}
	
	@GetMapping(value="/detail/reservation/{adventureId}")
	@PreAuthorize("hasRole('FISHINSTRUCTOR')")
	public ResponseEntity<List<ReservationForOwnerDTO>> getReservationsForAdventure(@PathVariable Long adventureId){
		
		List<ReservationForOwnerDTO> reservationsForOwner = new ArrayList<ReservationForOwnerDTO>();
		List<Reservation> reservations = reservationService.getAllReservationsForOffer(adventureId);
		System.out.println("Stigne ovde");
		for(Reservation reservation: reservations) {
			if(reservation.getClient() == null) {
				continue;
			}
			Client client = (Client) userService.findById(reservation.getClient().getId());
			Adventure adventure = adventureService.findOneById(adventureId);
			ReservationForOwnerDTO reservationForOwner = new ReservationForOwnerDTO(reservation.getId(), client.getId(),client.getName(), client.getSurname(), 
					reservation.getStartDateTime(), reservation.getEndDateTime(), reservation.isQuick(), adventure.getName());
			
			reservationsForOwner.add(reservationForOwner);
		}
		
		return new ResponseEntity<>(reservationsForOwner , HttpStatus.OK);
	}
		
	
	@GetMapping(value="/detail/reservation/all/{ownerId}")
	@PreAuthorize("hasRole('FISHINSTRUCTOR')")
	public ResponseEntity<List<ReservationForOwnerDTO>> getAllOwnersReservations(@PathVariable Long ownerId){
		System.out.println("STIGNEEE");
		List<ReservationForOwnerDTO> reservationsForOwner = new ArrayList<ReservationForOwnerDTO>();
		
		List<AdventureDTO> allAdventures = getAdventuresForOwner(ownerId);
		System.out.println("STIGNEEE");
		for(AdventureDTO adventure : allAdventures) {
			List<Reservation> reservations = reservationService.getAllReservationsForOffer(adventure.getId());
			for(Reservation reservation: reservations) {
				if(reservation.getClient() == null) {
					continue;
				}
				Client client = (Client) userService.findById(reservation.getClient().getId());
				ReservationForOwnerDTO reservationForOwner = new ReservationForOwnerDTO(reservation.getId(), client.getId(),client.getName(), client.getSurname(), 
						reservation.getStartDateTime(), reservation.getEndDateTime(), reservation.isQuick(), adventure.getName());
				
				reservationsForOwner.add(reservationForOwner);
			}
		}

		return new ResponseEntity<>(reservationsForOwner , HttpStatus.OK);
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
		
		FishingInstructor fi = fishingInstructorService.findOne(adventureDTO.getInstructorId());
		
		adventure.setOwner(fi);
		
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
		
		
		Pricelist pricelist = new Pricelist(adventureDTO.getPrice(),adventureDTO.getStartDatePrice());
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
		List<ExperienceReview> experience =  experienceReviewService.findAllApprovedByOfferId(adventureId);
		List<AdditionalServices> additionalServices = additionalServicesService.findAllByOfferId(adventureId);
		Pricelist pricelist = pricelistService.findOffersCurrentPriceById(adventureId);
		double price = pricelist.getPrice();

		System.out.println("size za role: " + behaviorRules.size());
		System.out.println("image siye : "  + images.size());
		System.out.println("fishing equipment siye : "  + fishEquipment.size());
		System.out.println("cancel condition siye : "  + cancelConditions.size());
		System.out.println("experience review siye : "  + experience.size());
		System.out.println("addition siye : "  + additionalServices.size());
		System.out.println("OVDE");
		AdventureDTO adventureDTO = new AdventureDTO(adventure, behaviorRules, images, fishEquipment, cancelConditions, experience, additionalServices, price, adventure.getOwner().getId(), pricelist.getId());
			
		System.out.println(adventureDTO);
		return adventureDTO;
	}

	private Adventure updateAdventure(Adventure adventure, AdventureDTO adventureDTO) {
		
		adventure.setName(adventureDTO.getName());
		adventure.getAddress().setLatitude(adventureDTO.getLatitude());
		adventure.getAddress().setLongitude(adventureDTO.getLongitude());
		adventure.setCapacity(adventureDTO.getCapacity());
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
		

		Pricelist pricelist = new Pricelist(adventureDTO.getPrice(), LocalDate.now());
		List<Pricelist> pricelists = pricelistService.findAllByAdventureId(adventureDTO.getId());
		for(Pricelist p : pricelists) 
		{
			System.out.println("CENAAaa" + p.getPrice() + " " + p.getId() + " " + p.getStartDate() + "  " + p.getEndDate());
			if(p.getEndDate() == null) {
				p.setEndDate(LocalDate.now());
			}
		}
		pricelist.setOffer(adventure);
		pricelist.setStartDate(LocalDate.now());
		pricelists.add(pricelist);
		adventure.setPricelists(pricelists);   

	/*	Pricelist pricelist = pricelistService.findOneById(adventureDTO.getPriceListId());
		pricelist.setEndDate(LocalDate.now());
		Pricelist updated = pricelistService.save(pricelist);
		System.out.println("cenaaaa  id: "  + updated.getId());
		Pricelist newPricelist = new Pricelist();
		newPricelist.setStartDate(LocalDate.now());
		newPricelist.setEndDate(null);
		newPricelist.setPrice(adventureDTO.getPrice());
		newPricelist.setOffer(adventure);
		pricelistService.save(newPricelist); */
		
		
		adventure.setImages(adventureDTO.getImagesAdventure());  // kasnije
		
	return adventure;
	}


	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value="/site/filter")
	public ResponseEntity<List<AdventureForListViewDTO>> getFilteredAdventures(@RequestBody AdventureFilterParamsDTO adventureFilterParamsDTO){

		List<AdventureForListViewDTO> adventuresDTO = filterAdventures(adventureFilterParamsDTO);

		adventuresDTO = handleSort(adventuresDTO, adventureFilterParamsDTO);

		adventuresDTO = handlePagination(adventuresDTO, adventureFilterParamsDTO);

		return new ResponseEntity(adventuresDTO, HttpStatus.OK);
	}

	private List<AdventureForListViewDTO> handlePagination(List<AdventureForListViewDTO> adventuresDTO, AdventureFilterParamsDTO adventureFilterParamsDTO) {
		if (adventureFilterParamsDTO.getFromElement() < 0)
			adventureFilterParamsDTO.setFromElement(0);
		int untilElement = adventureFilterParamsDTO.getUntilElement(adventuresDTO.size());// paginationDTO.getFromElement() + paginationDTO.getNumberToDisplay();


		AdventureForListViewDTO firstDto = new AdventureForListViewDTO();
		firstDto.setListSize(adventuresDTO.size());

		adventuresDTO = adventuresDTO.subList(adventureFilterParamsDTO.getFromElement(), untilElement);
		adventuresDTO.add(0, firstDto);

		System.out.println(adventuresDTO.size());

		return adventuresDTO;
	}

	private List<AdventureForListViewDTO> handleSort(List<AdventureForListViewDTO> adventuresDTO, AdventureFilterParamsDTO adventureFilterParamsDTO) {
		if (adventureFilterParamsDTO.getSortBy().equals("name")){
			return sortByName(adventuresDTO);
		}else if (adventureFilterParamsDTO.getSortBy().equals("location")){
			return sortByLocation(adventuresDTO);
		}else if (adventureFilterParamsDTO.getSortBy().equals("rating")){
			return sortByRating(adventuresDTO);
		}else if (adventureFilterParamsDTO.getSortBy().equals("price")){
			return sortByPrice(adventuresDTO);
		}else if (adventureFilterParamsDTO.getSortBy().equals("capacity")){
			return sortByCapacity(adventuresDTO);
		}
		return adventuresDTO;
	}

	private List<AdventureForListViewDTO> sortByCapacity(List<AdventureForListViewDTO> adventuresDTO) {
		Collections.sort(adventuresDTO, new Comparator<AdventureForListViewDTO>() {
			@Override
			public int compare(AdventureForListViewDTO c1, AdventureForListViewDTO c2) {
				return (int) (c1.getCapacity() - c2.getCapacity());
			}
		});
		return adventuresDTO;
	}

	private List<AdventureForListViewDTO> sortByPrice(List<AdventureForListViewDTO> adventuresDTO) {
		Collections.sort(adventuresDTO, new Comparator<AdventureForListViewDTO>() {
			@Override
			public int compare(AdventureForListViewDTO c1, AdventureForListViewDTO c2) {
				return (int) (c1.getPrice() - c2.getPrice());
			}
		});
		return adventuresDTO;
	}

	private List<AdventureForListViewDTO> sortByRating(List<AdventureForListViewDTO> adventuresDTO) {
		Collections.sort(adventuresDTO, new Comparator<AdventureForListViewDTO>() {
			@Override
			public int compare(AdventureForListViewDTO c1, AdventureForListViewDTO c2) {
				return (int) (c1.getMark() - c2.getMark());
			}
		});
		Collections.reverse(adventuresDTO);
		return adventuresDTO;
	}

	private List<AdventureForListViewDTO> sortByLocation(List<AdventureForListViewDTO> adventuresDTO) {
		Collections.sort(adventuresDTO, new Comparator<AdventureForListViewDTO>() {
			@Override
			public int compare(AdventureForListViewDTO c1, AdventureForListViewDTO c2) {
				return (int) (c1.getLatitude() - c2.getLatitude());
			}
		});
		return adventuresDTO;
	}

	private List<AdventureForListViewDTO> sortByName(List<AdventureForListViewDTO> adventuresDTO) {
		Collections.sort(adventuresDTO, new Comparator<AdventureForListViewDTO>() {
			@Override
			public int compare(AdventureForListViewDTO c1, AdventureForListViewDTO c2) {
				return c1.getName().compareTo(c2.getName());
			}
		});
		return adventuresDTO;
	}

	private List<AdventureForListViewDTO> filterAdventures(AdventureFilterParamsDTO adventureFilterParamsDTO) {

		List<Adventure> adventures = adventureService.findAll();

		adventures = offerService.searchAdventuresBy(adventures, adventureFilterParamsDTO.getSearchBy());

		//lokacija
		adventures = offerService.filterByAdventureLocation(adventures, adventureFilterParamsDTO.getLongitude(), adventureFilterParamsDTO.getLatitude());

		//kapacitet
		adventures = offerService.filterAdventuresByCapacity(adventures, adventureFilterParamsDTO.getCapacity(), adventureFilterParamsDTO.getCapacityRelOp());

		//interval
		if (adventureFilterParamsDTO.getDateFrom() != null && adventureFilterParamsDTO.getDateUntil() != null) {
			for (Adventure adventure : adventures) {
				adventure.setPeriodAvailabilities(periodAvailabilitySerivce.getListOfAvailability(adventure.getId(), adventureFilterParamsDTO.getDateFrom(), adventureFilterParamsDTO.getDateUntil()));
				adventure.setPeriodUnavailabilities(periodUnavailabilityService.getListOfUnavailability(adventure.getId(), adventureFilterParamsDTO.getDateFrom(), adventureFilterParamsDTO.getDateUntil()));
				adventure.setReservations(reservationService.getListOfReservationByOfferInInterval(adventure.getId(), adventureFilterParamsDTO.getDateFrom(), adventureFilterParamsDTO.getDateUntil()));
				adventure.setSaleAppointments(saleAppointmentService.getListOfReservationByOfferInInterval(adventure.getId(), adventureFilterParamsDTO.getDateFrom(), adventureFilterParamsDTO.getDateUntil()));
			}
			adventures = offerService.filterAdventureByInterval(adventures, adventureFilterParamsDTO.getDateFrom(), adventureFilterParamsDTO.getDateUntil());
		}

		List<AdventureForListViewDTO> adventuresDTO = getAdventuresForListViewDTO(adventures);

		//rating
		adventuresDTO = offerService.filterAdventuresByRating(adventuresDTO, adventureFilterParamsDTO.getRating(), adventureFilterParamsDTO.getRatingRelOp());

		//cena
		adventuresDTO = offerService.filterAdventuresByPrice(adventuresDTO, adventureFilterParamsDTO.getPrice(), adventureFilterParamsDTO.getPriceRelOp());

		return adventuresDTO;

	}

	private List<AdventureForListViewDTO> getAdventuresForListViewDTO(List<Adventure> adventures){
		List<AdventureForListViewDTO> adventureDTO = new ArrayList<>();
		for (Adventure adventure : adventures) {
			adventure.setImages(imageService.findAllByOfferId(adventure.getId()));
			AdventureForListViewDTO dto = new AdventureForListViewDTO(adventure);
			dto.setPrice(pricelistService.getCurrentPriceOfOffer(adventure.getId()));
			dto.setMark(experienceReviewService.getReatingByOfferId(adventure.getId(), OfferType.ADVENTURE));
			adventureDTO.add(dto);
		}
		return adventureDTO;
	}

	@PostMapping(value = "/site/search", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AdventureForListViewDTO>> getCottagesSearchedBy(@RequestBody SearchParam searchBy){
		List<Adventure> adventure = adventureService.findAll();
		adventure = offerService.searchAdventuresBy(adventure, searchBy.getSearchBy());
		List<AdventureForListViewDTO> adventuresDTO = getAdventuresForListViewDTO(adventure);
		return new ResponseEntity<List<AdventureForListViewDTO>>(adventuresDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/site/{id}")
	public ResponseEntity<AdventureProfileInfoDTO> getAdventureDisplayForProfile(@PathVariable long id) {
		try {
			Adventure c = adventureService.findOneById(id);
			AdventureProfileInfoDTO adventuresDTO = new AdventureProfileInfoDTO(c);
			adventuresDTO.setImagesFromImageObjects(imageService.findAllByOfferId(id));
			adventuresDTO.setPrice(pricelistService.getCurrentPriceOfOffer(id));
			adventuresDTO.setBehavioralRulesFromBehaviourRuleObject(behaviorRuleService.findAllByOfferId(id));
			adventuresDTO.setAdditionalServicesFromAdditionalServiceObject(additionalServicesService.findAllByOfferId(id));
			adventuresDTO.setRating(experienceReviewService.getReatingByOfferId(c.getId(), OfferType.ADVENTURE));
			adventuresDTO.setAdditionalServicesFromFishingEquipmentObject(fishingEquipmentService.findAllByAdventureId(id));
			return new ResponseEntity<AdventureProfileInfoDTO>(adventuresDTO, HttpStatus.OK);
		}catch (Exception e){
			return ResponseEntity.ok(new AdventureProfileInfoDTO());
		}

	}


	@PostMapping(value = "/site/review/{id}")
	public ResponseEntity<List<ExperienceReviewDTO>> getExperienceReviesFromAdvanture(@PathVariable long id, @RequestBody PaginationDTO paginationDTO) {
		List<ExperienceReview> er = experienceReviewService.findAllApprovedByOfferId(id);
		ExperienceReviewDTO size = new ExperienceReviewDTO();
		size.setListSize(er.size());
		er = er.subList(paginationDTO.getFromElement(), paginationDTO.getUntilElement(er.size()));
		List<ExperienceReviewDTO> dto = new ArrayList<>();
		for (ExperienceReview e : er) {
			dto.add(new ExperienceReviewDTO(e));
		}
		dto.add(0, size);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping(value="/detail/admin/all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<AdminOfferDTO>> getAllAvtiveAdventureForAdmin() {
		List<Adventure> adventures = adventureService.findActiveAdventures();
		List<AdminOfferDTO>  adminOffers = new ArrayList<AdminOfferDTO>();
		for(Adventure adventure : adventures) {
			User owner = userService.findById(adventure.getOwner().getId());
			List<Reservation> reservations = reservationService.getAllReservationsForOffer(adventure.getId());
			double rate = experienceReviewService.getReatingByOfferId(adventure.getId(), OfferType.ADVENTURE);
			AdminOfferDTO offer = new AdminOfferDTO(adventure.getId(), adventure.getName(), adventure.getDescription(),
					owner.getName(), owner.getSurname(), owner.getEmail(),
		    		adventure.getAddress().getLongitude(), adventure.getAddress().getLatitude(), adventure.getCapacity(),
		    		reservations.size(), rate, adventure.isDeleted());
			
			adminOffers.add(offer);
		}
		return new ResponseEntity<>(adminOffers, HttpStatus.OK);
		
	}
	
	@GetMapping(value="/detail/admin/all/deleted")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<AdminOfferDTO>> getAllDeletedAdventureForAdmin() {
		List<Adventure> adventures = adventureService.findDeletedAdventures();
		List<AdminOfferDTO>  adminOffers = new ArrayList<AdminOfferDTO>();
		for(Adventure adventure : adventures) {
			User owner = userService.findById(adventure.getOwner().getId());
			List<Reservation> reservations = reservationService.getAllReservationsForOffer(adventure.getId());
			double rate = experienceReviewService.getReatingByOfferId(adventure.getId(), OfferType.ADVENTURE);
			AdminOfferDTO offer = new AdminOfferDTO(adventure.getId(), adventure.getName(), adventure.getDescription(),
					owner.getName(), owner.getSurname(), owner.getEmail(),
		    		adventure.getAddress().getLongitude(), adventure.getAddress().getLatitude(), adventure.getCapacity(),
		    		reservations.size(), rate, adventure.isDeleted());
			
			adminOffers.add(offer);
		}
		return new ResponseEntity<>(adminOffers, HttpStatus.OK);
		
	}
	

}