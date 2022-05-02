package com.project.mrsisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mrsisa.domain.Pricelist;
import com.project.mrsisa.repository.PricelistRepository;

@Service
public class PricelistService {
	
	@Autowired
	private PricelistRepository pricelistRepository;
	
	
	public Pricelist save(Pricelist pricelist) {
		return pricelistRepository.save(pricelist);
	}
	
	public List<Pricelist> findAll(){
		return pricelistRepository.findAll();
	}
	
	
	public Pricelist findOneById(Long id) {
		return pricelistRepository.findOneById(id);
	}
	
	public List<Pricelist> findAllByAdventureId(Long id){
		return pricelistRepository.findAllByOfferId(id);
	}
}