package com.project.mrsisa.repository;
import com.project.mrsisa.domain.CottageOwner;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
@Repository
public interface CottageOwnerRepository extends JpaRepository<CottageOwner,Long>{
	public Page<CottageOwner> findAll(Pageable pageable);


	CottageOwner findByVerificationCode(String verificationCode);

	@Transactional
	@Modifying
	@Query("update CottageOwner c set c.enabled = ?1 where c.id = ?2")
	void updateEnabledById(boolean enabled, Long id);
}
