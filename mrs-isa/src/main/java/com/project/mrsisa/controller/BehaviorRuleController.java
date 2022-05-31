package com.project.mrsisa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mrsisa.domain.BehaviorRule;
import com.project.mrsisa.dto.TextDTO;
import com.project.mrsisa.service.BehaviorRuleService;

@RestController
@RequestMapping(value="/behavior", produces = MediaType.APPLICATION_JSON_VALUE)
public class BehaviorRuleController {
	@Autowired
	private BehaviorRuleService behaviorRuleService;
	
	

	@GetMapping(value = "/all")
    @PreAuthorize("hasRole('FISHINSTRUCTOR')")
	public ResponseEntity<List<String>> getBehaviorRules(){
		List<BehaviorRule> rules = behaviorRuleService.findAll();
		List<String> rulesDTO = new ArrayList<String>();
		
		for (BehaviorRule br : rules) {
			rulesDTO.add(br.getText());
		}
	
		return new ResponseEntity<>(rulesDTO, HttpStatus.OK);
	}


}
