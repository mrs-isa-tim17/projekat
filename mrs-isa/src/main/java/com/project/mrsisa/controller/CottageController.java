package com.project.mrsisa.controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.project.mrsisa.domain.*;
import com.project.mrsisa.dto.StartEndDateDTO;
import com.project.mrsisa.dto.cottage.FindCottagesDTO;
import com.project.mrsisa.dto.simple_user.CottageFilterParamsDTO;
import com.project.mrsisa.dto.simple_user.CottageForListViewDTO;
import com.project.mrsisa.dto.simple_user.OfferForHomePageViewDTO;
import com.project.mrsisa.dto.AdminOfferDTO;
import com.project.mrsisa.dto.simple_user.*;
import com.project.mrsisa.service.OfferService;
import com.project.mrsisa.service.*;
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

	@Autowired
	private PeriodAvailabilitySerivce periodAvailabilitySerivce;

	@Autowired
	private PeriodUnavailabilityService periodUnavailabilityService;

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private ComplaintService complaintService;
	
	@Autowired
	private UserService userService;

	private OfferService offerService = new OfferService();

	@Autowired
	private ClientService clientService;

	@Autowired
	private SaleAppointmentService saleAppointmentService;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	@PostMapping(value = "/site/all")
	public ResponseEntity<List<CottageForListViewDTO>> getCottages(@RequestBody PaginationDTO paginationDTO) {
		List<Cottage> cottages = cottageService.findAll();
		if (paginationDTO.getFromElement() < 0)
			paginationDTO.setFromElement(0);
		int untilElement = paginationDTO.getUntilElement(cottages.size());// paginationDTO.getFromElement() + paginationDTO.getNumberToDisplay();
		List<CottageForListViewDTO> cottagesDTO = getCottagesForListViewDTO(cottages.subList(paginationDTO.getFromElement(), untilElement));
		CottageForListViewDTO firstDto = new CottageForListViewDTO();
		firstDto.setListSize(cottages.size());
		cottagesDTO.add(0, firstDto);
		return ResponseEntity.ok(cottagesDTO);
	}

	@GetMapping(value = "/site/all")
	public ResponseEntity<List<CottageForListViewDTO>> getCottages() {
		List<Cottage> cottages = cottageService.findActiveCottages();
		List<CottageForListViewDTO> cottagesDTO = getCottagesForListViewDTO(cottages);
		return ResponseEntity.ok(cottagesDTO);
	}

	private List<CottageForListViewDTO> getCottagesForListViewDTO(List<Cottage> cottages) {
		List<CottageForListViewDTO> cottagesDTO = new ArrayList<>();
		for (Cottage c : cottages) {
			c.setImages(imageService.findAllByOfferId(c.getId()));
			CottageForListViewDTO dto = new CottageForListViewDTO(c);
			dto.setPrice(pricelistService.getCurrentPriceOfOffer(c.getId()));
			dto.setMark(experienceReviewService.getReatingByOfferId(c.getId(), OfferType.COTTAGE));
			cottagesDTO.add(dto);
		}
		return cottagesDTO;
	}

	@GetMapping(value = "/site/short")
	public ResponseEntity<List<OfferForHomePageViewDTO>> getCottagesForHomePage() {
		List<Cottage> cottages = cottageService.findActiveCottages();
		List<OfferForHomePageViewDTO> cottagesDTO = new ArrayList<>();
		for (Cottage c : cottages) {
			c.setImages(imageService.findAllByOfferId(c.getId()));
			OfferForHomePageViewDTO dto = new OfferForHomePageViewDTO(c);
			cottagesDTO.add(dto);
		}
		return ResponseEntity.ok(cottagesDTO);
	}

	@PostMapping(value = "/save", consumes = "application/json")
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
		for (String rule : cottageDTO.getBehavioralRules()) {
			rules.add(behaviorRuleService.findOneByText(rule));
		}
		cottage.setBehaviorRules(rules);
		List<AdditionalServices> additionalService = new ArrayList<AdditionalServices>();
		for (String service : cottageDTO.getAdditionalServices()) {
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


	@GetMapping(value = "/all")
	@PreAuthorize("hasRole('COTTAGE_OWNER')")
	public ResponseEntity<List<FindCottagesDTO>> getAllCottages() {

		List<Cottage> cottages = cottageService.findActiveCottages();
		List<FindCottagesDTO> cottageDTO = new ArrayList<>();
		for (Cottage c : cottages) {
			 List<Image> images = imageService.findAllByOfferId(c.getId());
			 double price = pricelistService.findOffersCurrentPriceById(c.getId()).getPrice();
			 cottageDTO.add(new FindCottagesDTO(c,images, price));
		}

		return new ResponseEntity<>(cottageDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/detail/{id}")
	@PreAuthorize("hasRole('COTTAGE_OWNER')")
	public ResponseEntity<FindCottageDTO> getCottage(@PathVariable Long id) {
		Cottage cottage = null;
		try {
			cottage = cottageService.findOne(id);
			if (cottage == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 List<BehaviorRule> rules = behaviorRuleService.findAllByOfferId(cottage.getId());
		 List<Image> images = imageService.findAllByOfferId(cottage.getId());
		 List<CancelCondition> cancelConditions = cancelConditionService.findAllByOfferId(cottage.getId());
		 Pricelist pricelist = pricelistService.findOffersCurrentPriceById(cottage.getId());
		 //List<Complaint> complaint = complaintService.findAllByOfferId(cottage.getId());
		// List<ExperienceReview> experienceReviews = experienceReviewService.findAllByOfferId(cottage.getId());
		 List<AdditionalServices> additionalServices = additionalServicesService.findAllByOfferId(cottage.getId());
		 FindCottageDTO cottageDTO = new FindCottageDTO(cottage,rules,images,cancelConditions,additionalServices,pricelist);

		
        return new ResponseEntity<FindCottageDTO>(cottageDTO, HttpStatus.OK);

	}

	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('COTTAGE_OWNER')")
	public ResponseEntity<List<FindCottagesDTO>> getCottagesByOwner(@PathVariable Long id) {
		CottageOwner owner = cottageOwnerService.findOne(id);
		List<Cottage> cottages = cottageService.getCottagesByOwner(owner);

		List<FindCottagesDTO> cottagesDTO = new ArrayList<>();
		
		
		for (Cottage c : cottages) {
			 List<Image> images = imageService.findAllByOfferId(c.getId());
			 double price = pricelistService.getCurrentPriceOfOffer(c.getId());
			 cottagesDTO.add(new FindCottagesDTO(c,images,price));

		}
		return new ResponseEntity<>(cottagesDTO, HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{id}")
	@PreAuthorize("hasRole('COTTAGE_OWNER') or hasRole('ADMIN')")
	public ResponseEntity<Boolean> deleteCottage(@PathVariable Long id) {
		System.out.println("tu sam, brisanje");
		Cottage cottage = cottageService.findOne(id);
		
		if ((cottage != null) && ((reservationService.haveFutureReservations(id))==false)) {
			cottage.setDeleted(true);
			cottageService.save(cottage);
			return new ResponseEntity<>(true, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(false, HttpStatus.OK);
		}
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/update")
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

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/site/filter")
	public ResponseEntity<List<CottageForListViewDTO>> getFilteredCottages(@RequestBody CottageFilterParamsDTO cottageFilterParamsDTO) {

		List<CottageForListViewDTO> cottagesDTO = filterCottages(cottageFilterParamsDTO);

		cottagesDTO = handleSort(cottagesDTO, cottageFilterParamsDTO);

		cottagesDTO = handlePagination(cottagesDTO, cottageFilterParamsDTO);

		return new ResponseEntity(cottagesDTO, HttpStatus.OK);
	}

	private List<CottageForListViewDTO> handleSort(List<CottageForListViewDTO> cottagesDTO, CottageFilterParamsDTO cottageFilterParamsDTO) {
		if (cottageFilterParamsDTO.getSortBy().equals("name")){
			return sortByName(cottagesDTO);
		}else if (cottageFilterParamsDTO.getSortBy().equals("location")){
			return sortByLocation(cottagesDTO);
		}else if (cottageFilterParamsDTO.getSortBy().equals("rating")){
			return sortByRating(cottagesDTO);
		}else if (cottageFilterParamsDTO.getSortBy().equals("price")){
			return sortByPrice(cottagesDTO);
		}else if (cottageFilterParamsDTO.getSortBy().equals("beds")){
			return sortByBeds(cottagesDTO);
		}else if (cottageFilterParamsDTO.getSortBy().equals("rooms")){
			return sortByRooms(cottagesDTO);
		}
		return cottagesDTO;
	}

	private List<CottageForListViewDTO> sortByRooms(List<CottageForListViewDTO> cottagesDTO) {
		Collections.sort(cottagesDTO, new Comparator<CottageForListViewDTO>() {
			@Override
			public int compare(CottageForListViewDTO c1, CottageForListViewDTO c2) {
				return (int) (c1.getRoomQuantity() - c2.getRoomQuantity());
			}
		});
		return cottagesDTO;
	}

	private List<CottageForListViewDTO> sortByBeds(List<CottageForListViewDTO> cottagesDTO) {
		Collections.sort(cottagesDTO, new Comparator<CottageForListViewDTO>() {
			@Override
			public int compare(CottageForListViewDTO c1, CottageForListViewDTO c2) {
				return (int) (c1.getBedQuantity() - c2.getBedQuantity());
			}
		});
		return cottagesDTO;
	}

	private List<CottageForListViewDTO> sortByPrice(List<CottageForListViewDTO> cottagesDTO) {
		Collections.sort(cottagesDTO, new Comparator<CottageForListViewDTO>() {
			@Override
			public int compare(CottageForListViewDTO c1, CottageForListViewDTO c2) {
				return (int) (c1.getPrice() - c2.getPrice());
			}
		});
		return cottagesDTO;
	}

	private List<CottageForListViewDTO> sortByRating(List<CottageForListViewDTO> cottagesDTO) {
		Collections.sort(cottagesDTO, new Comparator<CottageForListViewDTO>() {
			@Override
			public int compare(CottageForListViewDTO c1, CottageForListViewDTO c2) {
				return (int) (c1.getMark() - c2.getMark());
			}
		});
		return cottagesDTO;
	}

	private List<CottageForListViewDTO> sortByLocation(List<CottageForListViewDTO> cottagesDTO) {
		Collections.sort(cottagesDTO, new Comparator<CottageForListViewDTO>() {
			@Override
			public int compare(CottageForListViewDTO c1, CottageForListViewDTO c2) {
				return (int) (c1.getLatitude() - c2.getLatitude());
			}
		});
		return cottagesDTO;
	}

	private List<CottageForListViewDTO> sortByName(List<CottageForListViewDTO> cottagesDTO) {
		Collections.sort(cottagesDTO, new Comparator<CottageForListViewDTO>() {
			@Override
			public int compare(CottageForListViewDTO c1, CottageForListViewDTO c2) {
				return c1.getName().compareTo(c2.getName());
			}
		});
		return cottagesDTO;
	}

	private List<CottageForListViewDTO> handlePagination(List<CottageForListViewDTO> cottagesDTO, CottageFilterParamsDTO cottageFilterParamsDTO) {
		if (cottageFilterParamsDTO.getFromElement() < 0)
			cottageFilterParamsDTO.setFromElement(0);
		int untilElement = cottageFilterParamsDTO.getUntilElement(cottagesDTO.size());// paginationDTO.getFromElement() + paginationDTO.getNumberToDisplay();


		CottageForListViewDTO firstDto = new CottageForListViewDTO();
		firstDto.setListSize(cottagesDTO.size());

		cottagesDTO = cottagesDTO.subList(cottageFilterParamsDTO.getFromElement(), untilElement);
		cottagesDTO.add(0, firstDto);

		System.out.println(cottagesDTO.size());
		return cottagesDTO;
	}

	private List<CottageForListViewDTO> filterCottages(CottageFilterParamsDTO cottageFilterParamsDTO) {

		List<Cottage> cottages = cottageService.findAll();

		cottages = offerService.searchCottagesBy(cottages, cottageFilterParamsDTO.getSearchBy());

		//lokacija
		cottages = offerService.filterByLocation(cottages, cottageFilterParamsDTO.getLongitude(), cottageFilterParamsDTO.getLatitude());

		//broj soba
		cottages = offerService.filterByRoomQuntity(cottages, cottageFilterParamsDTO.getNumberOfRooms(), cottageFilterParamsDTO.getRoomsRelOp());

		//broj kreveta
		cottages = offerService.filterByBedQuntity(cottages, cottageFilterParamsDTO.getNumberOfBed(), cottageFilterParamsDTO.getBedsRelOp());


		//interval
		if (cottageFilterParamsDTO.getDateFrom() != null && cottageFilterParamsDTO.getDateUntil() != null) {
			for (Cottage c : cottages) {
				c.setPeriodAvailabilities(periodAvailabilitySerivce.getListOfAvailability(c.getId(), cottageFilterParamsDTO.getDateFrom(), cottageFilterParamsDTO.getDateUntil()));
				c.setPeriodUnavailabilities(periodUnavailabilityService.getListOfUnavailability(c.getId(), cottageFilterParamsDTO.getDateFrom(), cottageFilterParamsDTO.getDateUntil()));
				c.setReservations(reservationService.getListOfReservationByOfferInInterval(c.getId(), cottageFilterParamsDTO.getDateFrom(), cottageFilterParamsDTO.getDateUntil()));
				c.setSaleAppointments(saleAppointmentService.getListOfReservationByOfferInInterval(c.getId(), cottageFilterParamsDTO.getDateFrom(), cottageFilterParamsDTO.getDateUntil()));
			}
			cottages = offerService.filterByInterval(cottages, cottageFilterParamsDTO.getDateFrom(), cottageFilterParamsDTO.getDateUntil());

		}

		System.out.println("FILLTER");

		List<CottageForListViewDTO> cottagesDTO = getCottagesForListViewDTO(cottages);

		//List<CottageForListViewDTO> cottagesDTO = getCottagesForListViewDTO(cottages);

		//rating
		cottagesDTO = offerService.filterByRating(cottagesDTO, cottageFilterParamsDTO.getRating(), cottageFilterParamsDTO.getRatingRelOp());

		//cena
		cottagesDTO = offerService.filterByPrice(cottagesDTO, cottageFilterParamsDTO.getPrice(), cottageFilterParamsDTO.getPriceRelOp());

		return cottagesDTO;

	}


	@GetMapping(value = "/site/{id}")
	public ResponseEntity<CottageProfileInfoDTO> getCottageDisplayForProfile(@PathVariable long id) {
		Cottage c = cottageService.findOne(id);
		CottageProfileInfoDTO cottageDTO = new CottageProfileInfoDTO(c);
		cottageDTO.setImagesFromImageObjects(imageService.findAllByOfferId(id));
		cottageDTO.setPrice(pricelistService.getCurrentPriceOfOffer(id));
		cottageDTO.setBehavioralRulesFromBehaviourRuleObject(behaviorRuleService.findAllByOfferId(id));
		cottageDTO.setAdditionalServicesFromAdditionalServiceObject(additionalServicesService.findAllByOfferId(id));
		cottageDTO.setRating(experienceReviewService.getReatingByOfferId(c.getId(), OfferType.COTTAGE));
		return new ResponseEntity<CottageProfileInfoDTO>(cottageDTO, HttpStatus.OK);
	}


	@PostMapping(value = "/site/review/{id}")
	public ResponseEntity<List<ExperienceReviewDTO>> getExperienceReviesFromCottage(@PathVariable long id, @RequestBody PaginationDTO paginationDTO) {
		List<ExperienceReview> er = experienceReviewService.findAllByOfferId(id);
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
	
	@GetMapping(value="/admin/all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<AdminOfferDTO>> getActiveCottageForAdmin() {
		List<Cottage> cottages = cottageService.findActiveCottages();
		List<AdminOfferDTO>  adminOffers = new ArrayList<AdminOfferDTO>();
		for(Cottage cottage : cottages) {
			User owner = userService.findById(cottage.getOwner().getId());
			List<Reservation> reservations = reservationService.getAllReservationsForOffer(cottage.getId());
			double rate = experienceReviewService.getReatingByOfferId(cottage.getId(), OfferType.COTTAGE);
			AdminOfferDTO offer = new AdminOfferDTO(cottage.getId(), cottage.getName(), cottage.getDescription(),
					owner.getName(), owner.getSurname(), owner.getEmail(),
					cottage.getAddress().getLongitude(), cottage.getAddress().getLatitude(),  
					cottage.getBedQuantity(), cottage.getRoomQuantity(), reservations.size(), rate, cottage.isDeleted());
			adminOffers.add(offer);
		}
		return new ResponseEntity<>(adminOffers, HttpStatus.OK);
		
	}
	
	@GetMapping(value="/admin/all/deleted")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<AdminOfferDTO>> getDeletedCottageForAdmin() {
		List<Cottage> cottages = cottageService.findDeletedCottages();
		List<AdminOfferDTO>  adminOffers = new ArrayList<AdminOfferDTO>();
		for(Cottage cottage : cottages) {
			User owner = userService.findById(cottage.getOwner().getId());
			List<Reservation> reservations = reservationService.getAllReservationsForOffer(cottage.getId());
			double rate = experienceReviewService.getReatingByOfferId(cottage.getId(), OfferType.COTTAGE);
			AdminOfferDTO offer = new AdminOfferDTO(cottage.getId(), cottage.getName(), cottage.getDescription(),
					owner.getName(), owner.getSurname(), owner.getEmail(),
					cottage.getAddress().getLongitude(), cottage.getAddress().getLatitude(),  
					cottage.getBedQuantity(), cottage.getRoomQuantity(), reservations.size(), rate, cottage.isDeleted());
			adminOffers.add(offer);
		}
		return new ResponseEntity<>(adminOffers, HttpStatus.OK);
		
	}

	@GetMapping(value="/reservation/periods/{id}")
	@PreAuthorize("hasRole('COTTAGE_OWNER')")
	public ResponseEntity<List<StartEndDateDTO>> getReservationPeriods(@PathVariable Long id){
		List<StartEndDateDTO> reservationPeriods = new ArrayList<StartEndDateDTO>();

		Cottage cottage = cottageService.findOne(id);
		List<Reservation> reservations = reservationService.getAllReservationsForOffer(id);
		for(Reservation r : reservations) {
			StartEndDateDTO period = new StartEndDateDTO(r.getStartDate().atStartOfDay().format(formatter), r.getEndDate().atStartOfDay().format(formatter), cottage.getName());
			reservationPeriods.add(period);
		}
		return new ResponseEntity<>(reservationPeriods, HttpStatus.OK);
	}

}
