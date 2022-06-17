package com.project.mrsisa.service;

import java.util.List;


import com.project.mrsisa.domain.Cottage;
import com.project.mrsisa.domain.Offer;
import com.project.mrsisa.domain.ShipOwner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.project.mrsisa.domain.Ship;
import com.project.mrsisa.repository.ShipRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShipService {
	private final Logger LOG = LoggerFactory.getLogger(CottageService.class);

	@Autowired
	private ShipRepository shipRepository;

	@Autowired
	private ReservationService reservationService;

	public Ship save(Ship ship) {
		return shipRepository.save(ship);
	}
	
	public List<Ship> findAll() {
		return shipRepository.findAll();
	}

	@Cacheable("ship")
	public Ship findOne(Long id) {
		LOG.info("Ship with id: " + id + " successfully cached!");
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
	
	public List<Ship> findActiveShips() {
		return shipRepository.findActiveShips();
	}
	
	public List<Ship> findDeletedShips(){
		return shipRepository.findDeletedShips();
	}

    public Offer findOneTryOccupation(long offerId) {
		return shipRepository.findOneTryOccupation(offerId);
    }

	@CacheEvict(cacheNames = {"ship"}, allEntries = true)
	void removeFromCache(){
		LOG.info("Products removed from cache!");
	}
	@CacheEvict(cacheNames = {"ship"},key = "#id")
	public void removeOneFromCacheById(@Param("id") long id){
		LOG.info("Ship: " + id + " removed from cache!");
	}
	@Transactional
	public boolean deleteShip(long id) {
		Ship ship = shipRepository.findOneTryOccupation(id);
		if (ship != null && !(reservationService.haveFutureReservations(id))) {
			ship.setDeleted(true);
			save(ship);				// logičko brisanje
			removeOneFromCacheById(ship.getId());
			return true;
		}
		return false;
	}
}
