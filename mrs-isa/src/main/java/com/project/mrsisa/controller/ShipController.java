package com.project.mrsisa.controller;

import java.util.ArrayList;
import java.util.List;

import com.project.mrsisa.domain.*;
import com.project.mrsisa.domain.Cottage;
import com.project.mrsisa.domain.OfferType;
import com.project.mrsisa.dto.cottage.FindCottageDTO;
import com.project.mrsisa.dto.simple_user.OfferForHomePageViewDTO;
import com.project.mrsisa.dto.simple_user.ShipForListViewDTO;
import com.project.mrsisa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	private BehaviorRuleService behaviorRuleService;
	@Autowired
	private CancelConditionService cancelConditionService;

	@GetMapping(value = "/site/all")
	public ResponseEntity<List<ShipForListViewDTO>> getCottages(){
		List<Ship> ships = shipService.findAll();
		List<ShipForListViewDTO> shipsDTO = new ArrayList<>();
		System.out.println("Ships number: " + ships.size());
;		for (Ship ship : ships) {
			ship.setImages(imageService.findAllByOfferId(ship.getId()));
			ShipForListViewDTO dto = new ShipForListViewDTO(ship);
			dto.setPrice(pricelistService.getCurrentPriceOfOffer(ship.getId()));
			dto.setMark(experienceReviewService.getReatingByOfferId(ship.getId(), OfferType.SHIP));
			shipsDTO.add(dto);
		}
		return ResponseEntity.ok(shipsDTO);
	}

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('SHIP_OWNER')")
    public ResponseEntity<List<FindShipDTO>> getShipsByOwner(@PathVariable Long id) {
        ShipOwner owner = shipOwnerService.findOne(id);
        List<Ship> ships = shipService.getShipsByOwner(owner);

        List<FindShipDTO> shipsDTO = new ArrayList<>();


        for (Ship s : ships) {
            List<BehaviorRule> rules = behaviorRuleService.findAllByOfferId(s.getId());
            List<Image> images = imageService.findAllByOfferId(s.getId());
            List<CancelCondition> cancelConditions = cancelConditionService.findAllByOfferId(s.getId());
            shipsDTO.add(new FindShipDTO(s,images,rules,cancelConditions));
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
	
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Void> deleteShip(@PathVariable Long id) {

		Ship ship = shipService.findOne(id);

		if (ship != null) {
			shipService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

}
