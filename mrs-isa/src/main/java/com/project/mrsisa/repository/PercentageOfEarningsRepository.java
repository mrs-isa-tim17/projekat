package com.project.mrsisa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.project.mrsisa.domain.PercentageOfEarnings;

public interface PercentageOfEarningsRepository extends JpaRepository< PercentageOfEarnings, Long> {
   
	public Page<PercentageOfEarnings> findAll(Pageable pageable);

	public PercentageOfEarnings findOneById(Long id);
	
	public List<PercentageOfEarnings> findAll();
	
	@Transactional
	@Query(value="SELECT * from percentage_of_earnings WHERE end_date is null", nativeQuery = true)
	public PercentageOfEarnings findActivePercentage();
	
	
	
}
