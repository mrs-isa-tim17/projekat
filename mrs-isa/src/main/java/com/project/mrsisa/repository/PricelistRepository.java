package com.project.mrsisa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.mrsisa.domain.Pricelist;

public interface PricelistRepository extends JpaRepository<Pricelist, Long> {
	
	public Page<Pricelist> findAll(Pageable pageable);

	public Pricelist findOneById(Long id);
	
	public List<Pricelist> findAllByOfferId(Long id);

}