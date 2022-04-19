package com.project.mrsisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mrsisa.domain.Ship;
import com.project.mrsisa.repository.ShipRepository;
@Service
public class ShipService {
	@Autowired
	private ShipRepository shipRepository;
	
	public Ship save(Ship ship) {
		return shipRepository.save(ship);
	}
	
	public List<Ship> findAll() {
		return shipRepository.findAll();
	}
	
	public Ship findOne(Long id) {
		return shipRepository.findById(id).orElseGet(null);
	}
	
	public void remove(Long id) {
		shipRepository.deleteById(id);
	}
}
