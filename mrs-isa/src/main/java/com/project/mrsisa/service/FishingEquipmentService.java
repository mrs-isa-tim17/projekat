package com.project.mrsisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mrsisa.domain.FishingEquipment;
import com.project.mrsisa.repository.FishingEquipmentRepository;

@Service
public class FishingEquipmentService {
	
	@Autowired
	private FishingEquipmentRepository fishingEquipmentRepository;
	
	
	public FishingEquipment save(FishingEquipment fishingEquipment) {
		return fishingEquipmentRepository.save(fishingEquipment);
	}
	
	public List<FishingEquipment> findAll(){
		return fishingEquipmentRepository.findAll();
	}
		
	public FishingEquipment findOneById(Long id) {
		return fishingEquipmentRepository.findOneById(id);
	}
	
	public List<FishingEquipment> findAllByAdventureId(Long id){
		return fishingEquipmentRepository.findAllByAdventureId(id);
	}
	
	public FishingEquipment findOneByName(String name) {
		return fishingEquipmentRepository.findOneByName(name);
	}
}
