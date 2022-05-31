package com.project.mrsisa.repository;


import java.util.List;

import com.project.mrsisa.domain.AdditionalServices;
import com.project.mrsisa.domain.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.project.mrsisa.domain.Cottage;
import com.project.mrsisa.domain.CottageOwner;


public interface CottageRepository extends  JpaRepository<Cottage, Long>{
	
	public Page<Cottage> findAll(Pageable pageable);
	
	public List<Cottage> findByOwner(CottageOwner owner);

	@Transactional
    @Query(value = "SELECT * FROM cottage c WHERE c.deleted is false", nativeQuery = true)
	public List<Cottage> findActiveCottages();
	
	@Transactional
    @Query(value = "SELECT * FROM cottage c WHERE c.deleted is true", nativeQuery = true)
	public List<Cottage> findDeletedCottages();


    List<Offer> findAllByClientId(Long id);
}
