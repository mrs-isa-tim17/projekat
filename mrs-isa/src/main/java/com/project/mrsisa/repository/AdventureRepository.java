package com.project.mrsisa.repository;


import com.project.mrsisa.domain.Client;
import com.project.mrsisa.domain.Offer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.mrsisa.domain.Adventure;

import java.util.List;


public interface AdventureRepository extends JpaRepository<Adventure, Long> {
		
	public Page<Adventure> findAll(Pageable pageable);
	
	public Adventure findOneById(Long id);

    //List<Offer> findAllByClientId(Long clientId);

	List<Offer> findAllByClientId(long subscribers);


	
	
/*	@Query(value = "SELECT a FROM adventure JOIN FETCH a.behavior_rule br WHERE a.id=?1")
	public Adventure fetchAdventureWithBehaviorRule(Long id);
*/	
}


