package com.project.mrsisa.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.project.mrsisa.domain.*;
import com.project.mrsisa.dto.StartEndDateDTO;
import com.project.mrsisa.dto.ship.FindShipsByOwnerDTO;
import com.project.mrsisa.dto.simple_user.OfferForHomePageViewDTO;
import com.project.mrsisa.dto.simple_user.ShipForListViewDTO;
import com.project.mrsisa.dto.simple_user.*;
import com.project.mrsisa.service.OfferService;
import com.project.mrsisa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mrsisa.dto.ship.FindShipDTO;
import com.project.mrsisa.dto.AdminOfferDTO;
import com.project.mrsisa.dto.ShipDTO;
@RestController
@RequestMapping(value = "/ship")
public class ShipController {

	@Autowired
	private ShipService shipService;
    @Autowired
    private ShipOwnerService shipOwnerService;
	@Autowired
	private ImageService imageService;

	@Autowired
	private PricelistService pricelistService;

	@Autowired
	private ExperienceReviewService experienceReviewService;

	@Autowired
	private BehaviorRuleService behaviorRuleService;
	@Autowired
	private CancelConditionService cancelConditionService;

	@Autowired
	private PeriodUnavailabilityService periodUnavailabilityService;

	@Autowired
	private PeriodAvailabilitySerivce periodAvailabilitySerivce;

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private AdditionalServicesService additionalServicesService;

	@Autowired
	private FishingEquipmentService fishingEquipmentService;

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private SaleAppointmentService saleAppointmentService;

	private OfferService offerService = new OfferService();

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	@GetMapping(value = "/site/all")
	public ResponseEntity<List<ShipForListViewDTO>> getCottages(){
		List<Ship> ships = shipService.findActiveShips();
		List<ShipForListViewDTO> shipsDTO = getShipsForListViewDTO(ships);
		return ResponseEntity.ok(shipsDTO);
	}

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('SHIP_OWNER')")
    public ResponseEntity<List<FindShipsByOwnerDTO>> getShipsByOwner(@PathVariable Long id) {
        ShipOwner owner = shipOwnerService.findOne(id);
        List<Ship> ships = shipService.getShipsByOwner(owner);

        List<FindShipsByOwnerDTO> shipsDTO = new ArrayList<>();


        for (Ship s : ships) {

            List<Image> images = imageService.findAllByOfferId(s.getId());
         	double price = pricelistService.getCurrentPriceOfOffer(s.getId());
            shipsDTO.add(new FindShipsByOwnerDTO(s,images,price));
        }
        return new ResponseEntity<>(shipsDTO, HttpStatus.OK);
    }
	@GetMapping(value = "/site/short")
	public ResponseEntity<List<OfferForHomePageViewDTO>> getShipsForHomePage(){
		List<Ship> ships = shipService.findActiveShips();
		List<OfferForHomePageViewDTO> cottagesDTO = new ArrayList<>();
		if (ships.size() > 6){
			ships = ships.subList(0, 5);
		}
		for (Ship c : ships) {
			c.setImages(imageService.findAllByOfferId(c.getId()));
			OfferForHomePageViewDTO dto = new OfferForHomePageViewDTO(c);
			cottagesDTO.add(dto);
		}
		return ResponseEntity.ok(cottagesDTO);
	}

	@PostMapping(value="/save")
	@PreAuthorize("hasRole('SHIP_OWNER')")
	public ResponseEntity<ShipDTO> saveShip(@RequestBody ShipDTO shipDTO) {

		Ship ship = new Ship();
		Address a = new Address();
		a.setLatitude(shipDTO.getLatitude());
		a.setLongitude(shipDTO.getLongitude());
		ship.setAddress(a);
		ShipOwner owner = shipOwnerService.findOne(shipDTO.getOwnerId());
		ship.setEngineDesignation(shipDTO.getEngineDesignation());
		ship.setEnginePower(shipDTO.getEnginePower());
		ship.setType(shipDTO.getType());
		ship.setLength(shipDTO.getLength());
		ship.setMaxSpeed(shipDTO.getMaxSpeed());

		ship.setOwner(owner);
		ship.setName(shipDTO.getName());
		ship.setDescription(shipDTO.getDescription());
		ship.setDeleted(false);
		Pricelist newPricelist = new Pricelist();
		newPricelist.setStartDate(LocalDate.now());
		newPricelist.setOffer(ship);
		newPricelist.setEndDate(null);
		newPricelist.setPrice(shipDTO.getPrice());
		pricelistService.save(newPricelist);
		ship.setCapacity(shipDTO.getCapacity());
		ship.setNavigationEquipment(NavigationEquipment.RADAR);
		List<String> newAddServicesString = shipDTO.getAdditionalServices();
		List<AdditionalServices> aService = new ArrayList<AdditionalServices>();
		for(String as : newAddServicesString)
		{
			aService.add(additionalServicesService.findOneByName(as));
		}
		ship.setAdditionalServices(aService);

		List<String> newFishEqString = shipDTO.getFishingEquipment();
		List<FishingEquipment> fishEq = new ArrayList<FishingEquipment>();
		for(String as : newFishEqString)
		{
			fishEq.add(fishingEquipmentService.findOneByName(as));
		}
		ship.setFishingEquipments(fishEq);

		List<String> newBehRuleString = shipDTO.getBehavioralRules();
		List<BehaviorRule> behRules = new ArrayList<>();
		for(String br : newBehRuleString){
			behRules.add(behaviorRuleService.findOneByText(br));
		}
		ship.setBehaviorRules(behRules);
		List<CancelCondition> cancelConditions = new ArrayList<CancelCondition>();
		List<Double> percents =  shipDTO.getPercents();
		CancelCondition c1 = new CancelCondition(5, percents.get(0));
		cancelConditions.add(c1);
		cancelConditionService.save(c1);

		CancelCondition c2 = new CancelCondition(10, percents.get(1));
		cancelConditions.add(c2);
		cancelConditionService.save(c2);

		CancelCondition c3 = new CancelCondition(15, percents.get(2));
		cancelConditions.add(c3);
		cancelConditionService.save(c3);

		CancelCondition c4 = new CancelCondition(20, percents.get(3));
		cancelConditions.add(c4);
		cancelConditionService.save(c4);
		ship.setCancelCondition(cancelConditions);





		//jos promena, slike, pravila..

		ship = shipService.save(ship);
		return new ResponseEntity<>(new ShipDTO(ship, shipDTO.getPrice()), HttpStatus.CREATED);
	}
	
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<ShipDTO>> getAllShips() {

		List<Ship> ships = shipService.findActiveShips();
		List<ShipDTO> shipDTO = new ArrayList<>();
		for (Ship s : ships) {
			shipDTO.add(new ShipDTO(s));
		}

		return new ResponseEntity<>(shipDTO, HttpStatus.OK);
	}
	

	@PostMapping(value = "/delete/{id}")
	@PreAuthorize("hasRole('SHIP_OWNER') or hasRole('ADMIN')")
	public ResponseEntity<Boolean> deleteShip(@PathVariable Long id) {



		return ResponseEntity.ok(shipService.deleteShip(id));
		/*Ship ship = shipService.findOne(id);
		System.out.println("brisiiii");
		if ((ship != null) && ((reservationService.haveFutureReservations(id))==false)) {
				ship.setDeleted(true);
				shipService.save(ship);
				System.out.println("OBrisiiii");
				return new ResponseEntity<>(true, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(false, HttpStatus.OK);
		}*/
	}
	
	
	@PostMapping(value="/update/{id}",consumes = "application/json")
	@PreAuthorize("hasRole('SHIP_OWNER')")
	public ResponseEntity<ShipDTO> updateShip(@PathVariable Long id, @RequestBody ShipDTO shipDTO) {

		Ship ship = shipService.findOne(id);

		if (ship == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		ship.setId(shipDTO.getId());
		ship.getAddress().setLatitude(shipDTO.getLatitude());
		ship.getAddress().setLongitude(shipDTO.getLongitude());

		ship.setName(shipDTO.getName());
		ship.setDescription(shipDTO.getDescription());
		ship.setDeleted(shipDTO.isDeleted());
        if(pricelistService.findOffersCurrentPriceById(shipDTO.getId()).getPrice() != shipDTO.getPrice()){
            Pricelist pricelist = pricelistService.findOneById(shipDTO.getPriceListId());
            pricelist.setEndDate(LocalDate.now());
            Pricelist updated = pricelistService.save(pricelist);
            System.out.println("cenaaaa  id: "  + updated.getId());
            Pricelist newPricelist = new Pricelist();
            newPricelist.setStartDate(LocalDate.now());
            newPricelist.setEndDate(null);
            newPricelist.setPrice(shipDTO.getPrice());
            newPricelist.setOffer(ship);
            System.out.println("NOVOOOOOa  id: "  + newPricelist.getStartDate());
            System.out.println("NOVOOOOOa  id: "  + newPricelist.getEndDate());
            pricelistService.save(newPricelist);
        }
		ShipOwner owner = shipOwnerService.findOne(shipDTO.getOwnerId());
		ship.setOwner(owner);

		ship.setCapacity(shipDTO.getCapacity());

		List<java.lang.String> fishEq = shipDTO.getFishingEquipment();
		List<FishingEquipment> fishingEquipments = new ArrayList<>();
		for(String fe : fishEq){
			fishingEquipments.add(new FishingEquipment(fe));
		}
		ship.setFishingEquipments(fishingEquipments);
		List<java.lang.String> newAddServicesString = shipDTO.getAdditionalServices();
        System.out.println("addditional" + newAddServicesString.size());
		List<AdditionalServices> aService = new ArrayList<AdditionalServices>();
		for(String as : newAddServicesString)
		{
			aService.add(additionalServicesService.findOneByName(as));
			System.out.println("aadddd" + aService.get(0).getId());
		}
		ship.setAdditionalServices(aService);

		List<String> newBehRuleString = shipDTO.getBehavioralRules();
		List<BehaviorRule> behRules = new ArrayList<>();
		for(String br : newBehRuleString){
			behRules.add(behaviorRuleService.findOneByText(br));
		}
		ship.setBehaviorRules(behRules);
		List<CancelCondition> cancelConditions = new ArrayList<CancelCondition>();
		List<Double> percents =  shipDTO.getPercents();
		CancelCondition c1 = new CancelCondition(5, percents.get(0));
		cancelConditions.add(c1);
		cancelConditionService.save(c1);

		CancelCondition c2 = new CancelCondition(10, percents.get(1));
		cancelConditions.add(c2);
		cancelConditionService.save(c2);

		CancelCondition c3 = new CancelCondition(15, percents.get(2));
		cancelConditions.add(c3);
		cancelConditionService.save(c3);

		CancelCondition c4 = new CancelCondition(20, percents.get(3));
		cancelConditions.add(c4);
		cancelConditionService.save(c4);
		ship.setCancelCondition(cancelConditions);
		ship.setNavigationEquipment(NavigationEquipment.valueOf(getValueOfNavEq(shipDTO.getNavigationEquipment())));

		//jos promena, slike, pravila..
		Ship updatedShip=  shipService.save(ship);
		for(AdditionalServices a : updatedShip.getAdditionalServices()){
			System.out.println("aaddsss" + a.getName());
		}
		return new ResponseEntity<>(new ShipDTO(updatedShip,shipDTO.getPrice()), HttpStatus.OK);
	}

	private int getValueOfNavEq(String navEq){
		switch (navEq){
			case "GPS":
				return 0;
			case "radar":
				return 1;
			case "VHF radio":
				return 2;
			default:
				return 3;
				}
	}

	@GetMapping(value = "/detail/{id}")
	@PreAuthorize("hasRole('SHIP_OWNER')")
	public ResponseEntity<FindShipDTO> getShip(@PathVariable Long id) {
		Ship ship = null;
		try {
			ship = shipService.findOne(id);
			if(ship == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<BehaviorRule> rules = behaviorRuleService.findAllByOfferId(ship.getId());
		List<Image> images = imageService.findAllByOfferId(ship.getId());
		List<CancelCondition> cancelConditions = cancelConditionService.findAllByOfferId(ship.getId());
		List<FishingEquipment> fishingEquipments = fishingEquipmentService.findAllByAdventureId(ship.getId());
		Pricelist pricelist = pricelistService.findOffersCurrentPriceById(ship.getId());
		System.out.println(pricelist.getId());
		List<AdditionalServices> additionalServices = additionalServicesService.findAllByOfferId(ship.getId());
		FindShipDTO shipDTO = new FindShipDTO(ship,images,rules,cancelConditions,fishingEquipments,pricelist,additionalServices);

		return new ResponseEntity<FindShipDTO>(shipDTO, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value="/site/filter")
	public ResponseEntity<List<CottageForListViewDTO>> getFilteredCottages(@RequestBody ShipFilterParamsDTO shipFilterParamsDTO){
		List<ShipForListViewDTO> shipsDTO = filterShips(shipFilterParamsDTO);

		shipsDTO = handleSort(shipsDTO, shipFilterParamsDTO);

		shipsDTO = handlePagination(shipsDTO, shipFilterParamsDTO);


		return new ResponseEntity(shipsDTO, HttpStatus.OK);
	}

	private List<ShipForListViewDTO> handlePagination(List<ShipForListViewDTO> shipsDTP, ShipFilterParamsDTO shipFilterParamsDTO) {
		if (shipFilterParamsDTO.getFromElement() < 0)
			shipFilterParamsDTO.setFromElement(0);
		int untilElement = shipFilterParamsDTO.getUntilElement(shipsDTP.size());// paginationDTO.getFromElement() + paginationDTO.getNumberToDisplay();


		ShipForListViewDTO firstDto = new ShipForListViewDTO();
		firstDto.setListSize(shipsDTP.size());

		shipsDTP = shipsDTP.subList(shipFilterParamsDTO.getFromElement(), untilElement);
		shipsDTP.add(0, firstDto);

		return shipsDTP;
	}


	private List<ShipForListViewDTO> handleSort(List<ShipForListViewDTO> shipsDTO, ShipFilterParamsDTO shipFilterParamsDTO) {
		if (shipFilterParamsDTO.getSortBy().equals("name")){
			return sortByName(shipsDTO);
		}else if (shipFilterParamsDTO.getSortBy().equals("location")){
			return sortByLocation(shipsDTO);
		}else if (shipFilterParamsDTO.getSortBy().equals("rating")){
			return sortByRating(shipsDTO);
		}else if (shipFilterParamsDTO.getSortBy().equals("price")){
			return sortByPrice(shipsDTO);
		}else if (shipFilterParamsDTO.getSortBy().equals("speed")){
			return sortBySpeed(shipsDTO);
		}else if (shipFilterParamsDTO.getSortBy().equals("capacity")){
			return sortByCapacity(shipsDTO);
		}
		return shipsDTO;
	}

	private List<ShipForListViewDTO> sortByCapacity(List<ShipForListViewDTO> shipsDTO) {
		Collections.sort(shipsDTO, new Comparator<ShipForListViewDTO>() {
			@Override
			public int compare(ShipForListViewDTO c1, ShipForListViewDTO c2) {
				return (int) (c1.getCapacity() - c2.getCapacity());
			}
		});
		return shipsDTO;
	}

	private List<ShipForListViewDTO> sortBySpeed(List<ShipForListViewDTO> shipsDTO) {
		Collections.sort(shipsDTO, new Comparator<ShipForListViewDTO>() {
			@Override
			public int compare(ShipForListViewDTO c1, ShipForListViewDTO c2) {
				return (int) (c1.getMaxSpeed() - c2.getMaxSpeed());
			}
		});
		return shipsDTO;
	}

	private List<ShipForListViewDTO> sortByPrice(List<ShipForListViewDTO> shipsDTO) {
		Collections.sort(shipsDTO, new Comparator<ShipForListViewDTO>() {
			@Override
			public int compare(ShipForListViewDTO c1, ShipForListViewDTO c2) {
				return (int) (c1.getPrice() - c2.getPrice());
			}
		});
		return shipsDTO;
	}


	private List<ShipForListViewDTO> sortByRating(List<ShipForListViewDTO> shipsDTO) {
		Collections.sort(shipsDTO, new Comparator<ShipForListViewDTO>() {
			@Override
			public int compare(ShipForListViewDTO c1, ShipForListViewDTO c2) {
				return (int) (c1.getMark() - c2.getMark());
			}
		});
		return shipsDTO;
	}

	private List<ShipForListViewDTO> sortByLocation(List<ShipForListViewDTO> shipsDTO) {
		Collections.sort(shipsDTO, new Comparator<ShipForListViewDTO>() {
			@Override
			public int compare(ShipForListViewDTO c1, ShipForListViewDTO c2) {
				return (int) (c1.getLatitude() - c2.getLatitude());
			}
		});
		return shipsDTO;
	}

	private List<ShipForListViewDTO> sortByName(List<ShipForListViewDTO> shipsDTO) {
		Collections.sort(shipsDTO, new Comparator<ShipForListViewDTO>() {
			@Override
			public int compare(ShipForListViewDTO c1, ShipForListViewDTO c2) {
				return c1.getName().compareTo(c2.getName());
			}
		});
		return shipsDTO;
	}

	private List<ShipForListViewDTO> filterShips(ShipFilterParamsDTO shipFilterParamsDTO) {
		List<Ship> ships = shipService.findAll();

		ships = offerService.searchShipsBy(ships, shipFilterParamsDTO.getSearchBy());

		//lokacija
		ships = offerService.filterByShipLocation(ships, shipFilterParamsDTO.getLongitude(), shipFilterParamsDTO.getLatitude());

		//kapacitet
		ships = offerService.filterByCapacity(ships, shipFilterParamsDTO.getCapacity(), shipFilterParamsDTO.getCapacityRelOp());

		//broj kreveta
		ships = offerService.filterBySpeed(ships, shipFilterParamsDTO.getSpeed(), shipFilterParamsDTO.getSpeedRelOp());

		//interval
		if (shipFilterParamsDTO.getDateFrom() != null && shipFilterParamsDTO.getDateUntil() != null) {
			for (Ship ship : ships) {
				ship.setPeriodAvailabilities(periodAvailabilitySerivce.getListOfAvailability(ship.getId(), shipFilterParamsDTO.getDateFrom(), shipFilterParamsDTO.getDateUntil()));
				ship.setPeriodUnavailabilities(periodUnavailabilityService.getListOfUnavailability(ship.getId(), shipFilterParamsDTO.getDateFrom(), shipFilterParamsDTO.getDateUntil()));
				ship.setReservations(reservationService.getListOfReservationByOfferInInterval(ship.getId(), shipFilterParamsDTO.getDateFrom(), shipFilterParamsDTO.getDateUntil()));
				ship.setSaleAppointments(saleAppointmentService.getListOfReservationByOfferInInterval(ship.getId(), shipFilterParamsDTO.getDateFrom(), shipFilterParamsDTO.getDateUntil()));
			}
			ships = offerService.filterShipByInterval(ships, shipFilterParamsDTO.getDateFrom(), shipFilterParamsDTO.getDateUntil());
		}

		List<ShipForListViewDTO> shipsDTO = getShipsForListViewDTO(ships);

		//rating
		shipsDTO = offerService.filterShipsByRating(shipsDTO, shipFilterParamsDTO.getRating(), shipFilterParamsDTO.getRatingRelOp());

		//cena
		shipsDTO = offerService.filterShipsByPrice(shipsDTO, shipFilterParamsDTO.getPrice(), shipFilterParamsDTO.getPriceRelOp());

		return shipsDTO;
	}

	private List<ShipForListViewDTO> getShipsForListViewDTO(List<Ship> ships){
		List<ShipForListViewDTO> shipsDTO = new ArrayList<>();
		for (Ship ship : ships) {
			ship.setImages(imageService.findAllByOfferId(ship.getId()));
			ShipForListViewDTO dto = new ShipForListViewDTO(ship);
			dto.setPrice(pricelistService.getCurrentPriceOfOffer(ship.getId()));
			dto.setMark(experienceReviewService.getReatingByOfferId(ship.getId(), OfferType.SHIP));
			shipsDTO.add(dto);
		}
		return shipsDTO;
	}

	@PostMapping(value = "/site/search", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ShipForListViewDTO>> getShipsSearchedBy(@RequestBody SearchParam searchBy){
		List<Ship> ships = shipService.findAll();
		ships = offerService.searchShipsBy(ships, searchBy.getSearchBy());
		List<ShipForListViewDTO> shipsDTO = getShipsForListViewDTO(ships);
		return new ResponseEntity<List<ShipForListViewDTO>>(shipsDTO, HttpStatus.OK);
	}


	@GetMapping(value = "/site/{id}")
	public ResponseEntity<ShipProfileInfoDTO> getShipDisplayForProfile(@PathVariable long id) {
		try {
			Ship c = shipService.findOne(id);
			ShipProfileInfoDTO shipProfileInfoDTO = new ShipProfileInfoDTO(c);
			shipProfileInfoDTO.setImagesFromImageObjects(imageService.findAllByOfferId(id));
			shipProfileInfoDTO.setPrice(pricelistService.getCurrentPriceOfOffer(id));
			shipProfileInfoDTO.setBehavioralRulesFromBehaviourRuleObject(behaviorRuleService.findAllByOfferId(id));
			shipProfileInfoDTO.setAdditionalServicesFromAdditionalServiceObject(additionalServicesService.findAllByOfferId(id));
			shipProfileInfoDTO.setRating(experienceReviewService.getReatingByOfferId(c.getId(), OfferType.SHIP));
			shipProfileInfoDTO.setAdditionalServicesFromFishingEquipmentObject(fishingEquipmentService.findAllByAdventureId(id));
			return new ResponseEntity<ShipProfileInfoDTO>(shipProfileInfoDTO, HttpStatus.OK);
		}catch (Exception e){
			return ResponseEntity.ok(new ShipProfileInfoDTO());
		}
	}

	@PostMapping(value = "/site/review/{id}")
	public ResponseEntity<List<ExperienceReviewDTO>> getExperienceReviesFromShip(@PathVariable long id, @RequestBody PaginationDTO paginationDTO) {
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


	@GetMapping(value="/site/calendar/reservation/{id}")
	public ResponseEntity<List<StartEndDateDTO>> getReservationPeriods(@PathVariable Long id){
		List<StartEndDateDTO> reservationPeriods = new ArrayList<StartEndDateDTO>();

		Ship ship = shipService.findOne(id);
		List<Reservation> reservations = reservationService.getAllReservationsForOffer(id);
		for(Reservation r : reservations) {//////////////////STA je ovo
			StartEndDateDTO period = new StartEndDateDTO(r.getStartDateTime().format(formatter), r.getEndDateTime().format(formatter), ship.getName());
			reservationPeriods.add(period);
		}

		return new ResponseEntity<>(reservationPeriods, HttpStatus.OK);
	}
	
	@GetMapping(value="/admin/all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<AdminOfferDTO>> getActiveShipsForAdmin() {
		List<Ship> ships = shipService.findActiveShips();
		List<AdminOfferDTO>  adminOffers = new ArrayList<AdminOfferDTO>();
		for(Ship ship : ships) {
			User owner = userService.findById(ship.getOwner().getId());
			List<Reservation> reservations = reservationService.getAllReservationsForOffer(ship.getId());
			double rate = experienceReviewService.getReatingByOfferId(ship.getId(), OfferType.SHIP);
			AdminOfferDTO offer = new AdminOfferDTO(ship.getId(), ship.getName(), ship.getDescription(),
					owner.getName(), owner.getSurname(), owner.getEmail(),
					ship.getAddress().getLongitude(), ship.getAddress().getLatitude(),  ship.getCapacity(),ship.getType().toString(), reservations.size(), rate, ship.isDeleted());
			
			adminOffers.add(offer);
		}
		return new ResponseEntity<>(adminOffers, HttpStatus.OK);
		
	}
	
	
	@GetMapping(value="/admin/all/deleted")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<AdminOfferDTO>> getDeletedShipsForAdmin() {
		List<Ship> ships = shipService.findDeletedShips();
		List<AdminOfferDTO>  adminOffers = new ArrayList<AdminOfferDTO>();
		for(Ship ship : ships) {
			User owner = userService.findById(ship.getOwner().getId());
			List<Reservation> reservations = reservationService.getAllReservationsForOffer(ship.getId());
			double rate = experienceReviewService.getReatingByOfferId(ship.getId(), OfferType.SHIP);
			AdminOfferDTO offer = new AdminOfferDTO(ship.getId(), ship.getName(), ship.getDescription(),
					owner.getName(), owner.getSurname(), owner.getEmail(),
					ship.getAddress().getLongitude(), ship.getAddress().getLatitude(),  ship.getCapacity(),ship.getType().toString(), reservations.size(), rate, ship.isDeleted());
			
			adminOffers.add(offer);
		}
		return new ResponseEntity<>(adminOffers, HttpStatus.OK);
		
	}


}
