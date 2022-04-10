package com.project.mrsisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.mrsisa.domain.Ship;
import com.project.mrsisa.repository.ShipRepository;

public class ShipService {
	@Autowired
	private ShipRepository shipRepository;
	
	public Ship save(Ship ship) {
		return shipRepository.save(ship);
	}
	
	public List<Ship> findAll() {
		return shipRepository.findAll();
	}
}
