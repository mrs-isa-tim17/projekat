package com.project.mrsisa.repository;


import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.project.mrsisa.domain.Adventure;



public interface AdventureRepository extends JpaRepository<Adventure, Long> {
		
	public Page<Adventure> findAll(Pageable pageable);
	
	public Adventure findOneById(Long id);

	@Transactional
    @Query(value = "SELECT * FROM adventure c WHERE c.deleted is false", nativeQuery = true)
	public List<Adventure> findActiveAdventures();
	
	@Transactional
    @Query(value = "SELECT * FROM adventure c WHERE c.deleted is true", nativeQuery = true)
	public List<Adventure> findDeletedAdventures();
	
/*	@Query(value = "SELECT a FROM adventure JOIN FETCH a.behavior_rule br WHERE a.id=?1")
	public Adventure fetchAdventureWithBehaviorRule(Long id);
*/	
}


