package com.project.mrsisa.repository;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.mrsisa.domain.Cottage;

public interface CottageRepository extends  JpaRepository<Cottage, Long>{
	
	public Page<Cottage> findAll(Pageable pageable);

}
