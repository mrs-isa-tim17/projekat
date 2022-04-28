package com.project.mrsisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mrsisa.domain.BehaviorRule;
import com.project.mrsisa.repository.BehaviorRuleRepository;


@Service
public class BehaviorRuleService {
	
	@Autowired
	private BehaviorRuleRepository behaviorRuleRepository;
	
	
	public BehaviorRule save(BehaviorRule behaviorRule) {
		return behaviorRuleRepository.save(behaviorRule);
	}
	
	public List<BehaviorRule> findAll(){
		return behaviorRuleRepository.findAll();
	}
	
	
	public BehaviorRule findOneById(Long id) {
		return behaviorRuleRepository.findOneById(id);
	}
	
	public List<BehaviorRule> findAllByAdventureId(Long id){
		return behaviorRuleRepository.findAllByOfferId(id);
	}
}
