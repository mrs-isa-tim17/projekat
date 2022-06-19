package com.project.mrsisa.service;

import java.util.List;

import com.project.mrsisa.domain.Client;
import com.project.mrsisa.domain.Offer;
import com.project.mrsisa.domain.Cottage;
import com.project.mrsisa.domain.CottageOwner;
import com.project.mrsisa.domain.FishingInstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.mrsisa.domain.Adventure;
import com.project.mrsisa.repository.AdventureRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdventureService {
	private final Logger LOG = LoggerFactory.getLogger(CottageService.class);

	@Autowired
	private AdventureRepository adventureRepository;
	@Autowired
	private ReservationService reservationService;
	
	
	public Adventure save(Adventure adventure) {
		return adventureRepository.save(adventure);
	}
	
	public List<Adventure> findAll(){
		return adventureRepository.findAll();
	}

	public List<Adventure> getAdventuresByOwner(FishingInstructor owner){
		return adventureRepository.findByOwnerId(owner.getId());
	}


	@Cacheable("adventure")
	public Adventure findOneById(Long id) {
		LOG.info("Product with id: " + id + " successfully cached!");
		return adventureRepository.findOneById(id);
	}
	
	public void remove(Long id) {
		adventureRepository.deleteById(id);
	}

    public List<Offer> findAllByClientId(Client clientId) {
		return adventureRepository.findAllByClientId(clientId.getId());
    }

	public List<Adventure> findActiveAdventures(){
		return adventureRepository.findActiveAdventures();
	}
	
	public List<Adventure> findDeletedAdventures(){
		return adventureRepository.findDeletedAdventures();
	}

    public Offer findOneTryOccupation(long offerId) {
		return adventureRepository.findOneTryOccupation(offerId);
    }

	@Transactional
	public boolean deleteAdventure(Long id){
		Adventure adventure = adventureRepository.findOneTryOccupation(id);
		if (adventure != null && !(reservationService.haveFutureReservations(id))) {
			adventure.setDeleted(true);
			save(adventure);				// logičko brisanje
			removeOneFromCacheById(adventure.getId());
			return true;
		}
		return false;

	}

	@CacheEvict(cacheNames = {"adventure"}, allEntries = true)
	public void removeFromCache(){
		LOG.info("Products removed from cache!");
	}

	@CacheEvict(cacheNames = {"adventure"},key = "#id")
	public void removeOneFromCacheById(@Param("id") long id){
		LOG.info("Adventure: " + id + " removed from cache!");
	}

	public void setAdventureRepository(AdventureRepository adventureRepository) {
		this.adventureRepository = adventureRepository;
	}

	public void setReservationService(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

/*	public Adventure fetchAdventureWithOther(Long id) {
		return adventureRepository.fetchAdventureWithOther(id);
	}*/
}
