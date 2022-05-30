package com.project.mrsisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	
	public Adventure findOneById(Long id) {
		return adventureRepository.findOneById(id);
	}
	
	public void remove(Long id) {
		adventureRepository.deleteById(id);
	}
	
	public List<Adventure> findActiveAdventures(){
		return adventureRepository.findActiveAdventures();
	}
	
	public List<Adventure> findDeletedAdventures(){
		return adventureRepository.findDeletedAdventures();
	}
	
/*	public Adventure fetchAdventureWithOther(Long id) {
		return adventureRepository.fetchAdventureWithOther(id);
	}*/
}
