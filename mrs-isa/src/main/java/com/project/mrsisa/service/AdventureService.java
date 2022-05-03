package com.project.mrsisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.mrsisa.domain.Adventure;
import com.project.mrsisa.repository.AdventureRepository;
import org.springframework.stereotype.Service;

@Service
public class AdventureService {
	
	@Autowired
	private AdventureRepository adventureRepository;
	
	
	public Adventure save(Adventure adventure) {
		return adventureRepository.save(adventure);
	}
	
	public List<Adventure> findAll(){
		return adventureRepository.findAll();
	}

}
