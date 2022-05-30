package com.project.mrsisa.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mrsisa.domain.PercentageOfEarnings;
import com.project.mrsisa.dto.PercentageOfEarningsDTO;
import com.project.mrsisa.service.PercentageOfEarningsService;

@RestController
@RequestMapping(value="/percentage", produces = MediaType.APPLICATION_JSON_VALUE)
public class PercentageOfEarningsContoller {

	@Autowired
	private PercentageOfEarningsService percentageOfEarningsService;
	
	
    @PostMapping(value = "/define", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PercentageOfEarningsDTO> definePercentage(@RequestBody PercentageOfEarningsDTO percentageOfEarningsDTO){
    		
    	PercentageOfEarnings activePercentage = percentageOfEarningsService.findActivePercentage();
    	activePercentage.setEndDate(percentageOfEarningsDTO.getStartDate().minusDays(1));
    	percentageOfEarningsService.save(activePercentage);
    	
    	
    	PercentageOfEarnings newPercent = new PercentageOfEarnings();
    	newPercent.setPercentage(percentageOfEarningsDTO.getPercentage());
    	newPercent.setStartDate(percentageOfEarningsDTO.getStartDate());
    	
    	PercentageOfEarnings savedPercentage = percentageOfEarningsService.save(newPercent);
    	percentageOfEarningsDTO.setId(savedPercentage.getId());
    	percentageOfEarningsDTO.setPercentage(savedPercentage.getPercentage());
    	percentageOfEarningsDTO.setStartDate(savedPercentage.getStartDate());
    	
        return new ResponseEntity<>(percentageOfEarningsDTO, HttpStatus.CREATED);
        }
    


	@GetMapping(value="/all")
	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PercentageOfEarningsDTO>> getAllPercentages(){
		List<PercentageOfEarnings> allePrcentageOfEarnings = percentageOfEarningsService.findAll();
		List<PercentageOfEarningsDTO> all = new ArrayList<PercentageOfEarningsDTO>();
		
		for (PercentageOfEarnings percentageOfEarnings : allePrcentageOfEarnings) {
			
			all.add(new PercentageOfEarningsDTO(percentageOfEarnings.getId(), percentageOfEarnings.getStartDate(), 
					percentageOfEarnings.getEndDate(), percentageOfEarnings.getPercentage()));
		}
		Collections.reverse(all);
		return new ResponseEntity<>(all, HttpStatus.OK);
	
	}
}

