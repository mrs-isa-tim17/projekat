package com.project.mrsisa.repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import com.project.mrsisa.domain.*;

@Repository
public interface ShipOwnerRepository extends JpaRepository<ShipOwner,Long>{

	public Page<ShipOwner> findAll(Pageable pageable);


	//ShipOwner findByVerificationCode(String verificationCode);

	@Transactional
	@Modifying
	@Query("update ShipOwner so set so.enabled = ?1 where so.id = ?2")
	void updateEnabledById(boolean enabled, Long id);
}
