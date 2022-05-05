package com.project.mrsisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.mrsisa.domain.ShipOwner;
import com.project.mrsisa.repository.ShipOwnerRepository;

@Service
public class ShipOwnerService {
	@Autowired
	private ShipOwnerRepository soRepository;

	public ShipOwner save(ShipOwner so) {
		return soRepository.save(so);
	}
	
	public List<ShipOwner> findAll(){
		return soRepository.findAll();
	}
	
	public ShipOwner findOne(Long id) {
		return soRepository.findById(id).orElseGet(null);
	}
}
