package com.project.mrsisa.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
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

	private OfferProcessing offerProcessing = new OfferProcessing();

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
		for (Ship c : ships) {
			c.setImages(imageService.findAllByOfferId(c.getId()));
			OfferForHomePageViewDTO dto = new OfferForHomePageViewDTO(c);
			cottagesDTO.add(dto);
		}
		return ResponseEntity.ok(cottagesDTO);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<ShipDTO> saveShip(@RequestBody ShipDTO shipDTO) {

		Ship ship = new Ship();
		ship.setId(shipDTO.getId());
		ship.setAddress(shipDTO.getAddress());
		ship.setOwner(shipDTO.getOwner());
		ship.setName(shipDTO.getName());
		ship.setDescription(shipDTO.getDescription());
		ship.setDeleted(shipDTO.isDeleted());

		ship = shipService.save(ship);
		return new ResponseEntity<>(new ShipDTO(ship), HttpStatus.CREATED);
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
	
    @PreAuthorize("hasRole('SHIP_OWNER') or hasRole('ADMIN')")
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Boolean> deleteShip(@PathVariable Long id) {

		Ship ship = shipService.findOne(id);
		System.out.println("brisiiii");
		if ((ship != null) && ((reservationService.haveFutureReservations(id))==false)) {
				ship.setDeleted(true);
				shipService.save(ship);
				return new ResponseEntity<>(true, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(false, HttpStatus.OK);
		}
	}
	
	
	@PutMapping(value="/update",consumes = "application/json")
	public ResponseEntity<ShipDTO> updateShip(@RequestBody ShipDTO shipDTO) {

		Ship ship = shipService.findOne(shipDTO.getId());

		if (ship == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		ship.setId(shipDTO.getId());
		ship.setAddress(shipDTO.getAddress());
		ship.setOwner(shipDTO.getOwner());
		ship.setName(shipDTO.getName());
		ship.setDescription(shipDTO.getDescription());
		ship.setDeleted(shipDTO.isDeleted());
	
		//jos promena, slike, pravila..
		ship =  shipService.save(ship);
		return new ResponseEntity<>(new ShipDTO(ship), HttpStatus.OK);
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
		FindShipDTO shipDTO = new FindShipDTO(ship,images,rules,cancelConditions);

		return new ResponseEntity<FindShipDTO>(shipDTO, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value="/site/filter")
	public ResponseEntity<List<CottageForListViewDTO>> getFilteredCottages(@RequestBody ShipFilterParamsDTO shipFilterParamsDTO){
		List<Ship> ships = shipService.findAll();

		ships = offerProcessing.searchShipsBy(ships, shipFilterParamsDTO.getSearchBy());

		//lokacija
		ships = offerProcessing.filterByShipLocation(ships, shipFilterParamsDTO.getLongitude(), shipFilterParamsDTO.getLatitude());

		//kapacitet
		ships = offerProcessing.filterByCapacity(ships, shipFilterParamsDTO.getCapacity(), shipFilterParamsDTO.getCapacityRelOp());

		//broj kreveta
		ships = offerProcessing.filterBySpeed(ships, shipFilterParamsDTO.getSpeed(), shipFilterParamsDTO.getSpeedRelOp());

		//interval
		if (shipFilterParamsDTO.getDateFrom() != null && shipFilterParamsDTO.getDateUntil() != null) {
			for (Ship ship : ships) {
				ship.setPeriodAvailabilities(periodAvailabilitySerivce.getListOfAvailability(ship.getId(), shipFilterParamsDTO.getDateFrom().toLocalDate(), shipFilterParamsDTO.getDateUntil().toLocalDate()));
				ship.setPeriodUnavailabilities(periodUnavailabilityService.getListOfUnavailability(ship.getId(), shipFilterParamsDTO.getDateFrom().toLocalDate(), shipFilterParamsDTO.getDateUntil().toLocalDate()));
				ship.setReservations(reservationService.getListOfReservationByOfferInInterval(ship.getId(), shipFilterParamsDTO.getDateFrom().toLocalDate(), shipFilterParamsDTO.getDateUntil().toLocalDate()));
			}
			ships = offerProcessing.filterShipByInterval(ships, shipFilterParamsDTO.getDateFrom(), shipFilterParamsDTO.getDateUntil());
		}

		List<ShipForListViewDTO> shipsDTO = getShipsForListViewDTO(ships);

		//rating
		shipsDTO = offerProcessing.filterShipsByRating(shipsDTO, shipFilterParamsDTO.getRating(), shipFilterParamsDTO.getRatingRelOp());

		//cena
		shipsDTO = offerProcessing.filterShipsByPrice(shipsDTO, shipFilterParamsDTO.getPrice(), shipFilterParamsDTO.getPriceRelOp());

		return new ResponseEntity(shipsDTO, HttpStatus.OK);
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

	@PostMapping(value = "/site/sort/name", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ShipForListViewDTO>> getSortedCottageListByName(@RequestBody List<ShipForListViewDTO> cottagesDTO){
		Collections.sort(cottagesDTO, new Comparator<ShipForListViewDTO>() {
			@Override
			public int compare(ShipForListViewDTO c1, ShipForListViewDTO c2) {
				return c1.getName().compareTo(c2.getName());
			}
		});
		return ResponseEntity.ok(cottagesDTO);
	}

	@PostMapping(value = "/site/sort/location", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ShipForListViewDTO>> getSortedCottageListByLocation(@RequestBody List<ShipForListViewDTO> cottagesDTO){
		Collections.sort(cottagesDTO, new Comparator<ShipForListViewDTO>() {
			@Override
			public int compare(ShipForListViewDTO c1, ShipForListViewDTO c2) {
				return (int) (c1.getLatitude() - c2.getLatitude());
			}
		});
		return ResponseEntity.ok(cottagesDTO);
	}
	@PostMapping(value = "/site/sort/rating", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ShipForListViewDTO>> getSortedCottageListByRating(@RequestBody List<ShipForListViewDTO> cottagesDTO){
		Collections.sort(cottagesDTO, new Comparator<ShipForListViewDTO>() {
			@Override
			public int compare(ShipForListViewDTO c1, ShipForListViewDTO c2) {
				return (int) (c1.getMark() - c2.getMark());
			}
		});
		return ResponseEntity.ok(cottagesDTO);
	}
	@PostMapping(value = "/site/sort/price", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ShipForListViewDTO>> getSortedCottageListByPrice(@RequestBody List<ShipForListViewDTO> cottagesDTO){
		Collections.sort(cottagesDTO, new Comparator<ShipForListViewDTO>() {
			@Override
			public int compare(ShipForListViewDTO c1, ShipForListViewDTO c2) {
				return (int) (c1.getPrice() - c2.getPrice());
			}
		});
		return ResponseEntity.ok(cottagesDTO);
	}

	@PostMapping(value = "/site/sort/speed", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ShipForListViewDTO>> getSortedCottageListBySpeed(@RequestBody List<ShipForListViewDTO> cottagesDTO){
		Collections.sort(cottagesDTO, new Comparator<ShipForListViewDTO>() {
			@Override
			public int compare(ShipForListViewDTO c1, ShipForListViewDTO c2) {
				return (int) (c1.getMaxSpeed() - c2.getMaxSpeed());
			}
		});
		return ResponseEntity.ok(cottagesDTO);
	}

	@PostMapping(value = "/site/sort/capacity", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ShipForListViewDTO>> getSortedCottageListByCapacity(@RequestBody List<ShipForListViewDTO> cottagesDTO){
		Collections.sort(cottagesDTO, new Comparator<ShipForListViewDTO>() {
			@Override
			public int compare(ShipForListViewDTO c1, ShipForListViewDTO c2) {
				return (int) (c1.getCapacity() - c2.getCapacity());
			}
		});
		return ResponseEntity.ok(cottagesDTO);
	}

	@PostMapping(value = "/site/search", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ShipForListViewDTO>> getShipsSearchedBy(@RequestBody SearchParam searchBy){
		List<Ship> ships = shipService.findAll();
		ships = offerProcessing.searchShipsBy(ships, searchBy.getSearchBy());
		List<ShipForListViewDTO> shipsDTO = getShipsForListViewDTO(ships);
		return new ResponseEntity<List<ShipForListViewDTO>>(shipsDTO, HttpStatus.OK);
	}


	@GetMapping(value = "/site/{id}")
	public ResponseEntity<ShipProfileInfoDTO> getAdventureDisplayForProfile(@PathVariable long id) {
		Ship c = shipService.findOne(id);
		ShipProfileInfoDTO shipProfileInfoDTO = new ShipProfileInfoDTO(c);
		shipProfileInfoDTO.setImagesFromImageObjects(imageService.findAllByOfferId(id));
		shipProfileInfoDTO.setPrice(pricelistService.getCurrentPriceOfOffer(id));
		shipProfileInfoDTO.setBehavioralRulesFromBehaviourRuleObject(behaviorRuleService.findAllByOfferId(id));
		shipProfileInfoDTO.setAdditionalServicesFromAdditionalServiceObject(additionalServicesService.findAllByOfferId(id));
		shipProfileInfoDTO.setRating(experienceReviewService.getReatingByOfferId(c.getId(), OfferType.COTTAGE));
		shipProfileInfoDTO.setAdditionalServicesFromFishingEquipmentObject(fishingEquipmentService.findAllByAdventureId(id));
		return new ResponseEntity<ShipProfileInfoDTO>(shipProfileInfoDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/site/review/{id}")
	public ResponseEntity<List<ExperienceReviewDTO>> getExperienceReviesFromShip(@PathVariable long id) {
		List<ExperienceReview> er = experienceReviewService.findAllByOfferId(id);
		List<ExperienceReviewDTO> dto = new ArrayList<>();
		for (ExperienceReview e : er) {
			e.setClient(clientService.findOne(e.getClient().getId()));
			dto.add(new ExperienceReviewDTO(e));
		}
		return ResponseEntity.ok(dto);
	}


	@GetMapping(value="/site/calendar/reservation/{id}")
	public ResponseEntity<List<StartEndDateDTO>> getReservationPeriods(@PathVariable Long id){
		List<StartEndDateDTO> reservationPeriods = new ArrayList<StartEndDateDTO>();

		Ship ship = shipService.findOne(id);
		List<Reservation> reservations = reservationService.getAllReservationsForOffer(id);
		for(Reservation r : reservations) {//////////////////STA je ovo
			StartEndDateDTO period = new StartEndDateDTO(r.getStartDate().atStartOfDay().format(formatter), r.getEndDate().atStartOfDay().format(formatter), ship.getName());
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
