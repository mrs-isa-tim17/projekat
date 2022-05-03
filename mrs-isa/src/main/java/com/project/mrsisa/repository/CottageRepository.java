package com.project.mrsisa.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.mrsisa.domain.Cottage;
import com.project.mrsisa.domain.CottageOwner;


public interface CottageRepository extends  JpaRepository<Cottage, Long>{
	
	public Page<Cottage> findAll(Pageable pageable);
	
	public List<Cottage> findByOwner(CottageOwner owner);
	
	

}
