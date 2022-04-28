package com.project.mrsisa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.mrsisa.domain.FishingEquipment;

public interface FishingEquipmentRepository  extends JpaRepository<FishingEquipment, Long> {
	
	public Page<FishingEquipment> findAll(Pageable pageable);

	public FishingEquipment findOneById(Long id);
	
	public List<FishingEquipment> findAllByAdventureId(Long id);

}

