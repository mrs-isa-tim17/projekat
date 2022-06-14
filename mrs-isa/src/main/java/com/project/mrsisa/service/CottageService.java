package com.project.mrsisa.service;

import java.util.List;

import com.project.mrsisa.domain.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mrsisa.domain.Cottage;
import com.project.mrsisa.domain.CottageOwner;
import com.project.mrsisa.repository.CottageRepository;

@Service
public class CottageService {

	@Autowired
	private CottageRepository cottageRepository;
	
	public Cottage save(Cottage cottage) {
		return cottageRepository.save(cottage);
	}
	
	public List<Cottage> findAll() {
		return cottageRepository.findAll();
	}
	
	
	public List<Cottage> getCottagesByOwner(CottageOwner owner){
		return cottageRepository.findByOwner(owner);
	}
	
	public Cottage findOne(Long id) {
		return cottageRepository.findById(id).orElseGet(null);
	}
	
	public void remove(Long id) {
		cottageRepository.deleteById(id);
	}

	public List<Offer> findAllByClientId(Long id) {
		return cottageRepository.findAllByClientId(id);
	}
	
	public List<Cottage> findActiveCottages() {
		return cottageRepository.findActiveCottages();
	}
	
	public List<Cottage> findDeletedCottages(){
		return cottageRepository.findDeletedCottages();
	}

    public Cottage findOneTryOccupation(Long id) {
		return cottageRepository.findOneTryOccupation(id);
    }
}
