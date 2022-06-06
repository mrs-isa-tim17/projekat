package com.project.mrsisa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.project.mrsisa.domain.LoyaltyPoints;

public interface LoyaltyPointsRepository  extends JpaRepository<LoyaltyPoints, Long>{
	
	@Transactional
	@Query(value="SELECT * from loyalty_points l WHERE l.user_type_loyalty_points=?1 order by start_date", nativeQuery=true)
	public List<LoyaltyPoints> findPointsByUserTypePoints(int type);

	public LoyaltyPoints findOneById(long id);

	@Transactional
	@Query(value="SELECT * from loyalty_points l WHERE l.user_type_loyalty_points=?1 and l.end_date is null order by start_date", nativeQuery = true)
	public LoyaltyPoints findActivePointsByType(int value);



}
