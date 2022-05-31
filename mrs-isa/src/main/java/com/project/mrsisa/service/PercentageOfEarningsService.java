package com.project.mrsisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mrsisa.domain.PercentageOfEarnings;
import com.project.mrsisa.repository.PercentageOfEarningsRepository;

@Service
public class PercentageOfEarningsService {

	@Autowired
	private PercentageOfEarningsRepository percentageOfEarningsRepository;
	
	public PercentageOfEarnings save(PercentageOfEarnings percentageOfEarnings) {
		return percentageOfEarningsRepository.save(percentageOfEarnings);
	}
	
	public List<PercentageOfEarnings> findAll(){
		return percentageOfEarningsRepository.findAll();
	}
	
	public PercentageOfEarnings findOneById(Long id) {
		return percentageOfEarningsRepository.findOneById(id);
	}
	
	public PercentageOfEarnings findActivePercentage() {
		return percentageOfEarningsRepository.findActivePercentage();
	}


	
}
