package com.project.mrsisa.service;

import java.util.List;


import com.project.mrsisa.domain.Offer;
import com.project.mrsisa.domain.ShipOwner;
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

	public List<Ship> getShipsByOwner(ShipOwner owner){
		return shipRepository.findByOwner(owner);
	}

    public List<Offer> findAllByClientId(Long clientId) {
		return shipRepository.findAllByClientId(clientId);
    }
}
