package com.project.mrsisa.controller;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.project.mrsisa.domain.*;
import com.project.mrsisa.domain.OfferType;
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
	private PeriodUnavailabilityService periodUnavailabilityService;

	@Autowired
	private PeriodAvailabilitySerivce periodAvailabilitySerivce;

	@Autowired
	private ReservationService reservationService;

	private OfferProcessing offerProcessing = new OfferProcessing();

	@GetMapping(value = "/site/all")
	public ResponseEntity<List<ShipForListViewDTO>> getCottages(){
		List<Ship> ships = shipService.findAll();
		List<ShipForListViewDTO> shipsDTO = getShipsForListViewDTO(ships);
		return ResponseEntity.ok(shipsDTO);
	}

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('SHIP_OWNER')")
    public ResponseEntity<List<FindShipDTO>> getShipsByOwner(@PathVariable Long id) {
        ShipOwner owner = shipOwnerService.findOne(id);
        List<Ship> ships = shipService.getShipsByOwner(owner);

        List<FindShipDTO> shipsDTO = new ArrayList<>();


        for (Ship s : ships) {
           //List<BehaviorRule> rules = behaviorRuleService.findAllByOfferId(c.getId());
            List<Image> images = imageService.findAllByOfferId(s.getId());
            //List<CancelCondition> cancelConditions = cancelConditionService.findAllByOfferId(c.getId());*/
            shipsDTO.add(new FindShipDTO(s,images));
        }
        return new ResponseEntity<>(shipsDTO, HttpStatus.OK);
    }
	@GetMapping(value = "/site/short")
	public ResponseEntity<List<OfferForHomePageViewDTO>> getShipsForHomePage(){
		List<Ship> ships = shipService.findAll();
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

		List<Ship> ships = shipService.findAll();
		List<ShipDTO> shipDTO = new ArrayList<>();
		for (Ship s : ships) {
			shipDTO.add(new ShipDTO(s));
		}

		return new ResponseEntity<>(shipDTO, HttpStatus.OK);
	}
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteShip(@PathVariable Long id) {

		Ship ship = shipService.findOne(id);

		if (ship != null) {
			shipService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PutMapping(consumes = "application/json")
	public ResponseEntity<ShipDTO> updateShip(@RequestBody ShipDTO shipDTO) {

		// a student must exist
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
	

		ship =  shipService.save(ship);
		return new ResponseEntity<>(new ShipDTO(ship), HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value="/site/filter")
	public ResponseEntity<List<CottageForListViewDTO>> getFilteredCottages(@RequestBody ShipFilterParamsDTO shipFilterParamsDTO){
		List<Ship> ships = shipService.findAll();

		System.out.println("CONTROLLLERRRR");
		System.out.println(ships.size());

		//lokacija
		ships = offerProcessing.filterByShipLocation(ships, shipFilterParamsDTO.getLongitude(), shipFilterParamsDTO.getLatitude());
		System.out.println(ships.size());

		//kapacitet
		ships = offerProcessing.filterByCapacity(ships, shipFilterParamsDTO.getCapacity(), shipFilterParamsDTO.getCapacityRelOp());
		System.out.println(ships.size());

		//broj kreveta
		ships = offerProcessing.filterBySpeed(ships, shipFilterParamsDTO.getSpeed(), shipFilterParamsDTO.getSpeedRelOp());
		System.out.println(ships.size());


		//interval
		if (shipFilterParamsDTO.getDateFrom() != null && shipFilterParamsDTO.getDateUntil() != null) {
			for (Ship ship : ships) {
				ship.setPeriodAvailabilities(periodAvailabilitySerivce.getListOfAvailability(ship.getId(), shipFilterParamsDTO.getDateFrom().toLocalDate(), shipFilterParamsDTO.getDateUntil().toLocalDate()));
				ship.setPeriodUnavailabilities(periodUnavailabilityService.getListOfUnavailability(ship.getId(), shipFilterParamsDTO.getDateFrom().toLocalDate(), shipFilterParamsDTO.getDateUntil().toLocalDate()));
				ship.setReservations(reservationService.getListOfReservationByOfferInInterval(ship.getId(), shipFilterParamsDTO.getDateFrom().toLocalDate(), shipFilterParamsDTO.getDateUntil().toLocalDate()));
			}
			ships = offerProcessing.filterShipByInterval(ships, shipFilterParamsDTO.getDateFrom(), shipFilterParamsDTO.getDateUntil());
		}
		System.out.println(ships.size());

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

}
