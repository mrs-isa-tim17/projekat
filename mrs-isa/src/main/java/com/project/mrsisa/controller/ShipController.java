package com.project.mrsisa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mrsisa.domain.Ship;
import com.project.mrsisa.dto.ShipDTO;
import com.project.mrsisa.service.ShipService;
@RestController
@RequestMapping(value = "/api/ships")
public class ShipController {

	@Autowired
	private ShipService shipService;
	
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
