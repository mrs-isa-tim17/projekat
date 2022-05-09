package com.project.mrsisa.controller;

import java.util.ArrayList;
import java.util.List;

import com.project.mrsisa.domain.*;
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

	@GetMapping(value = "/site/all")
	public ResponseEntity<List<ShipForListViewDTO>> getCottages(){
		List<Ship> ships = shipService.findAll();
		List<ShipForListViewDTO> shipsDTO = new ArrayList<>();
		for (Ship ship : ships) {
			ship.setImages(imageService.findAllByOfferId(ship.getId()));
			ShipForListViewDTO dto = new ShipForListViewDTO(ship);
			dto.setPrice(pricelistService.getCurrentPriceOfOffer(ship.getId()));
			dto.setMark(experienceReviewService.getReatingByOfferId(ship.getId()));
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
           //List<BehaviorRule> rules = behaviorRuleService.findAllByOfferId(c.getId());
            List<Image> images = imageService.findAllByOfferId(s.getId());
            //List<CancelCondition> cancelConditions = cancelConditionService.findAllByOfferId(c.getId());*/
            shipsDTO.add(new FindShipDTO(s,images));
        }
        return new ResponseEntity<>(shipsDTO, HttpStatus.OK);
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

}
