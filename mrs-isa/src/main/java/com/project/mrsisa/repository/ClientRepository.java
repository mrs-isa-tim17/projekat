package com.project.mrsisa.repository;

import com.project.mrsisa.domain.Offer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.project.mrsisa.domain.Client;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	public Page<Client> findAll(Pageable pageable);


	Client findByVerificationCode(String verificationCode);

	@Modifying
	@Query("update Client c set c.enabled = ?1 where c.id = ?2")
	void updateEnabledById(boolean enabled, Long id);

	@Query(value="SELECT * FROM subscriptions p WHERE p.client_id=?1", nativeQuery = true)
	List<Long> getOfferClientSubscribedFor(long clientId);

	List<Client> findAllBySubscriptionsId(long subscribers);

}
