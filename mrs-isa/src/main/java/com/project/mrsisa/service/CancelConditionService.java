package com.project.mrsisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mrsisa.domain.CancelCondition;
import com.project.mrsisa.repository.CancelConditionRepository;

@Service
public class CancelConditionService {
	
	@Autowired
	private CancelConditionRepository cancelConditionRepository;
	
	
	public CancelCondition save(CancelCondition cancelCondition) {
		return cancelConditionRepository.save(cancelCondition);
	}
	
	public List<CancelCondition> findAll(){
		return cancelConditionRepository.findAll();
	}
	
	
	public CancelCondition findOneById(Long id) {
		return cancelConditionRepository.findOneById(id);
	}
	
	public List<CancelCondition> findAllByAdventureId(Long id){
		return cancelConditionRepository.findAllByOfferId(id);
	}
}