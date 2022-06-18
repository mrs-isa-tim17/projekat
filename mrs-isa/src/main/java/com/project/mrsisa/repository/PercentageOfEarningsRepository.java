package com.project.mrsisa.repository;

import java.time.LocalDate;
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
	
	@Query(value="SELECT * from percentage_of_earnings p WHERE  p.end_date is not null and p.start_date>=?1 and p.end_date<?1", nativeQuery=true)
	public PercentageOfEarnings getPercentageOfEarningsForDate(LocalDate date);
	
}
