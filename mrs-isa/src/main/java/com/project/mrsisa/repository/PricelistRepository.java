package com.project.mrsisa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.project.mrsisa.domain.Pricelist;

public interface PricelistRepository extends JpaRepository<Pricelist, Long> {
	
	public Page<Pricelist> findAll(Pageable pageable);

	public Pricelist findOneById(Long id);
	
	public List<Pricelist> findAllByOfferId(Long id);

	@Transactional
    @Query(value = "SELECT * FROM pricelist p WHERE p.offer_id = ?1 and p.end_date is null", nativeQuery = true)
    public Pricelist findOffersCurrentPriceById(Long offerId);

}