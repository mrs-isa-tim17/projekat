package com.project.mrsisa.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.project.mrsisa.domain.*;
import com.project.mrsisa.dto.cottage.FindCottagesDTO;
import com.project.mrsisa.dto.simple_user.CottageFilterParamsDTO;
import com.project.mrsisa.dto.simple_user.CottageForListViewDTO;
import com.project.mrsisa.dto.simple_user.OfferForHomePageViewDTO;
import com.project.mrsisa.dto.simple_user.SearchParam;
import com.project.mrsisa.dto.client.QuickReservationForClientDTO;
import com.project.mrsisa.dto.simple_user.*;
import com.project.mrsisa.processing.OfferProcessing;
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



	private OfferProcessing offerProcessing = new OfferProcessing();

	@Autowired
	private ClientService clientService;

	@GetMapping(value = "/site/all")
	public ResponseEntity<List<CottageForListViewDTO>> getCottages() {
		List<Cottage> cottages = cottageService.findAll();
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
		List<Cottage> cottages = cottageService.findAll();
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

		List<Cottage> cottages = cottageService.findAll();
		List<FindCottagesDTO> cottageDTO = new ArrayList<>();
		for (Cottage c : cottages) {
			 List<Image> images = imageService.findAllByOfferId(c.getId());
			 List<AdditionalServices> additionalServices = additionalServicesService.findAllByOfferId(c.getId());
			 cottageDTO.add(new FindCottagesDTO(c,images, additionalServices));
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
			 List<AdditionalServices> additionalServices = additionalServicesService.findAllByOfferId(c.getId());
			 cottagesDTO.add(new FindCottagesDTO(c,images,additionalServices));

		}
		return new ResponseEntity<>(cottagesDTO, HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{id}")
	@PreAuthorize("hasRole('COTTAGE_OWNER')")
	public ResponseEntity<Boolean> deleteCottage(@PathVariable Long id) {
		System.out.println("tu sam, brisanje");
		Cottage cottage = cottageService.findOne(id);
		System.out.println(cottage.getName());
		if (cottage != null) {
			cottageService.remove(id);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
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
		List<Cottage> cottages = cottageService.findAll();

		cottages = offerProcessing.searchCottagesBy(cottages, cottageFilterParamsDTO.getSearchBy());

		//lokacija
		cottages = offerProcessing.filterByLocation(cottages, cottageFilterParamsDTO.getLongitude(), cottageFilterParamsDTO.getLatitude());

		//broj soba
		cottages = offerProcessing.filterByRoomQuntity(cottages, cottageFilterParamsDTO.getNumberOfRooms(), cottageFilterParamsDTO.getRoomsRelOp());

		//broj kreveta
		cottages = offerProcessing.filterByBedQuntity(cottages, cottageFilterParamsDTO.getNumberOfBed(), cottageFilterParamsDTO.getBedsRelOp());


		//interval
		if (cottageFilterParamsDTO.getDateFrom() != null && cottageFilterParamsDTO.getDateUntil() != null) {
			for (Cottage c : cottages) {
				c.setPeriodAvailabilities(periodAvailabilitySerivce.getListOfAvailability(c.getId(), cottageFilterParamsDTO.getDateFrom().toLocalDate(), cottageFilterParamsDTO.getDateUntil().toLocalDate()));
				c.setPeriodUnavailabilities(periodUnavailabilityService.getListOfUnavailability(c.getId(), cottageFilterParamsDTO.getDateFrom().toLocalDate(), cottageFilterParamsDTO.getDateUntil().toLocalDate()));
				c.setReservations(reservationService.getListOfReservationByOfferInInterval(c.getId(), cottageFilterParamsDTO.getDateFrom().toLocalDate(), cottageFilterParamsDTO.getDateUntil().toLocalDate()));
			}
			cottages = offerProcessing.filterByInterval(cottages, cottageFilterParamsDTO.getDateFrom(), cottageFilterParamsDTO.getDateUntil());

		}

		List<CottageForListViewDTO> cottagesDTO = getCottagesForListViewDTO(cottages);

		//rating
		cottagesDTO = offerProcessing.filterByRating(cottagesDTO, cottageFilterParamsDTO.getRating(), cottageFilterParamsDTO.getRatingRelOp());

		//cena
		cottagesDTO = offerProcessing.filterByPrice(cottagesDTO, cottageFilterParamsDTO.getPrice(), cottageFilterParamsDTO.getPriceRelOp());


		return new ResponseEntity(cottagesDTO, HttpStatus.OK);
	}

	@PostMapping(value = "/site/sort/name", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CottageForListViewDTO>> getSortedCottageListByName(@RequestBody List<CottageForListViewDTO> cottagesDTO) {
		Collections.sort(cottagesDTO, new Comparator<CottageForListViewDTO>() {
			@Override
			public int compare(CottageForListViewDTO c1, CottageForListViewDTO c2) {
				return c1.getName().compareTo(c2.getName());
			}
		});
		return ResponseEntity.ok(cottagesDTO);
	}

	@PostMapping(value = "/site/sort/location", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CottageForListViewDTO>> getSortedCottageListByLocation(@RequestBody List<CottageForListViewDTO> cottagesDTO) {
		Collections.sort(cottagesDTO, new Comparator<CottageForListViewDTO>() {
			@Override
			public int compare(CottageForListViewDTO c1, CottageForListViewDTO c2) {
				return (int) (c1.getLatitude() - c2.getLatitude());
			}
		});
		return ResponseEntity.ok(cottagesDTO);
	}

	@PostMapping(value = "/site/sort/rating", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CottageForListViewDTO>> getSortedCottageListByRating(@RequestBody List<CottageForListViewDTO> cottagesDTO) {
		Collections.sort(cottagesDTO, new Comparator<CottageForListViewDTO>() {
			@Override
			public int compare(CottageForListViewDTO c1, CottageForListViewDTO c2) {
				return (int) (c1.getMark() - c2.getMark());
			}
		});
		return ResponseEntity.ok(cottagesDTO);
	}

	@PostMapping(value = "/site/sort/price", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CottageForListViewDTO>> getSortedCottageListByPrice(@RequestBody List<CottageForListViewDTO> cottagesDTO) {
		Collections.sort(cottagesDTO, new Comparator<CottageForListViewDTO>() {
			@Override
			public int compare(CottageForListViewDTO c1, CottageForListViewDTO c2) {
				return (int) (c1.getPrice() - c2.getPrice());
			}
		});
		return ResponseEntity.ok(cottagesDTO);
	}

	@PostMapping(value = "/site/sort/rooms", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CottageForListViewDTO>> getSortedCottageListByRooms(@RequestBody List<CottageForListViewDTO> cottagesDTO) {
		Collections.sort(cottagesDTO, new Comparator<CottageForListViewDTO>() {
			@Override
			public int compare(CottageForListViewDTO c1, CottageForListViewDTO c2) {
				return (int) (c1.getRoomQuantity() - c2.getRoomQuantity());
			}
		});
		return ResponseEntity.ok(cottagesDTO);
	}

	@PostMapping(value = "/site/sort/beds", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CottageForListViewDTO>> getSortedCottageListByBeds(@RequestBody List<CottageForListViewDTO> cottagesDTO) {
		Collections.sort(cottagesDTO, new Comparator<CottageForListViewDTO>() {
			@Override
			public int compare(CottageForListViewDTO c1, CottageForListViewDTO c2) {
				return (int) (c1.getBedQuantity() - c2.getBedQuantity());
			}
		});
		return ResponseEntity.ok(cottagesDTO);
	}

	@PostMapping(value = "/site/search", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CottageForListViewDTO>> getCottagesSearchedBy(@RequestBody SearchParam searchBy) {
		List<Cottage> cottages = cottageService.findAll();
		cottages = offerProcessing.searchCottagesBy(cottages, searchBy.getSearchBy());
		List<CottageForListViewDTO> cottagesDTO = getCottagesForListViewDTO(cottages);
		return new ResponseEntity<List<CottageForListViewDTO>>(cottagesDTO, HttpStatus.OK);
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

	@GetMapping(value = "/site/review/{id}")
	public ResponseEntity<List<ExperienceReviewDTO>> getExperienceReviesFromCottage(@PathVariable long id) {
		List<ExperienceReview> er = experienceReviewService.findAllByOfferId(id);
		List<ExperienceReviewDTO> dto = new ArrayList<>();
		for (ExperienceReview e : er) {
			e.setClient(clientService.findOne(e.getClient().getId()));
			dto.add(new ExperienceReviewDTO(e));
		}
		return ResponseEntity.ok(dto);
	}

}
