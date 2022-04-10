package com.project.mrsisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mrsisa.domain.Cottage;
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
}
