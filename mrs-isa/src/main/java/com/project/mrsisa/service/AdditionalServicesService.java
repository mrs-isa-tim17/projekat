package com.project.mrsisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mrsisa.domain.AdditionalServices;
import com.project.mrsisa.repository.AdditionalServicesRepository;

@Service
public class AdditionalServicesService {
	
	@Autowired
	private AdditionalServicesRepository additionalServicesRepository;
	
	
	public AdditionalServices save(AdditionalServices additionalServices) {
		return additionalServicesRepository.save(additionalServices);
	}
	
	public List<AdditionalServices> findAll(){
		return additionalServicesRepository.findAll();
	}
	
	public AdditionalServices findOneById(Long id) {
		return additionalServicesRepository.findOneById(id);
	}
	public AdditionalServices findOneByName(String name) {
		return additionalServicesRepository.findOneByName(name);
	}
	
	public List<AdditionalServices> findAllByOfferId(Long id){
		return additionalServicesRepository.findAllByOfferId(id);
	}

	public List<AdditionalServices> findBySaleAppointmentId(Long id){
		return additionalServicesRepository.findAllBySaleAppointmentId(id);
	}

	public void remove(Long id) {
		additionalServicesRepository.deleteById(id);
	}
}