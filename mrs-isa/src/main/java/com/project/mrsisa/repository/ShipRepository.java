package com.project.mrsisa.repository;

import java.util.List;
import com.project.mrsisa.domain.ShipOwner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.project.mrsisa.domain.Ship;

public interface ShipRepository extends JpaRepository<Ship,Long>{
	public Page<Ship> findAll(Pageable pageable);
	public List<Ship> findByOwner(ShipOwner owner);
	
	@Transactional
    @Query(value = "SELECT * FROM ship c WHERE c.deleted is false", nativeQuery = true)
	public List<Ship> findActiveShips();
	
	@Transactional
    @Query(value = "SELECT * FROM ship c WHERE c.deleted is true", nativeQuery = true)
	public List<Ship> findDeletedShips();

}
