package com.project.mrsisa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.mrsisa.domain.CancelCondition;


public interface CancelConditionRepository extends JpaRepository<CancelCondition, Long> {
	
	public Page<CancelCondition> findAll(Pageable pageable);

	public CancelCondition findOneById(Long id);
	
	public List<CancelCondition> findAllByOfferId(Long id);

	public CancelCondition findOneByOfferIdAndDays(Long id, int days);

}
