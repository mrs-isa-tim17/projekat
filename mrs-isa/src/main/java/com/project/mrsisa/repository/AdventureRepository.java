package com.project.mrsisa.repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.mrsisa.domain.Adventure;



public interface AdventureRepository extends JpaRepository<Adventure, Long> {
		
	public Page<Adventure> findAll(Pageable pageable);
	
	public Adventure findOneById(Long id);
	
/*	@Query(value = "SELECT a FROM adventure JOIN FETCH a.behavior_rule br WHERE a.id=?1")
	public Adventure fetchAdventureWithBehaviorRule(Long id);
*/	
}


