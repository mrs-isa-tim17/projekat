package com.project.mrsisa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.CrudRepositoryExtensionsKt;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.mrsisa.domain.LoyaltyScale;

public interface LoyaltyScaleRepository extends JpaRepository<LoyaltyScale, Long> {
    public Page<LoyaltyScale> findAll(Pageable pageable);

    List<LoyaltyScale> findByTrashholdIsLessThan(int trashhold);
    /* @Query(value = "",nativeQuery = true)
    LoyaltyScale findByThresholdLessThan();*/

    @Transactional
    @Query(value="SELECT * from loyalty_scale l WHERE l.role_id=?1 and l.end_date is null order by user_type", nativeQuery = true)
	public List<LoyaltyScale> findScaleByRole(Long roleId);
    
    public LoyaltyScale findOneById(Long id);

    @Query(value="SELECT * from loyalty_scale l WHERE l.role_id=?1 and (l.end_date is null or l.end_date > CURRENT_DATE) order by start_date", nativeQuery = true)
    List<LoyaltyScale> findCurrentActiveScalesByType(Long roleId);

    @Query(value="SELECT * from loyalty_scale l WHERE l.role_id=?1 and l.user_type=?2 and (l.end_date is null or l.end_date > CURRENT_DATE) order by start_date", nativeQuery = true)
    List<LoyaltyScale> findCurrentActivePointsByUserType(long roleId, int value);
}
