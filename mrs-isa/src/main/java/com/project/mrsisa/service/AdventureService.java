package com.project.mrsisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.project.mrsisa.domain.Adventure;
import com.project.mrsisa.repository.AdventureRepository;

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
	
	
	public Adventure findOneById(Long id) {
		return adventureRepository.findOneById(id);
	}

/*	public Adventure fetchAdventureWithOther(Long id) {
		return adventureRepository.fetchAdventureWithOther(id);
	}*/
}
