package com.project.mrsisa.service;

import java.util.List;

import com.project.mrsisa.domain.Client;
import com.project.mrsisa.domain.Offer;
import com.project.mrsisa.domain.Cottage;
import com.project.mrsisa.domain.CottageOwner;
import com.project.mrsisa.domain.FishingInstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.mrsisa.domain.Adventure;
import com.project.mrsisa.repository.AdventureRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdventureService {
	
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


	public Adventure findOneById(Long id) {
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
			return true;
		}
		return false;

	}

/*	public Adventure fetchAdventureWithOther(Long id) {
		return adventureRepository.fetchAdventureWithOther(id);
	}*/
}
