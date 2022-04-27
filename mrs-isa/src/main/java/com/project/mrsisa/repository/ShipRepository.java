package com.project.mrsisa.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.mrsisa.domain.Ship;

public interface ShipRepository extends JpaRepository<Ship,Long>{
	public Page<Ship> findAll(Pageable pageable);

}
