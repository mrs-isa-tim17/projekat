package com.project.mrsisa.repository;

import java.util.List;

import com.project.mrsisa.domain.Offer;
import com.project.mrsisa.domain.ShipOwner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.mrsisa.domain.Ship;

public interface ShipRepository extends JpaRepository<Ship,Long>{
	public Page<Ship> findAll(Pageable pageable);
	public List<Ship> findByOwner(ShipOwner owner);

    //List<Offer> findBySubscribers_Id(Long clientId);

	List<Offer> findAllByClientId(Long id);


}
