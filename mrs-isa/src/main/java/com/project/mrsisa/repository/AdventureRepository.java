package com.project.mrsisa.repository;


import com.project.mrsisa.domain.Client;
import com.project.mrsisa.domain.Offer;
import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.project.mrsisa.domain.Adventure;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;



public interface AdventureRepository extends JpaRepository<Adventure, Long> {
		
	public Page<Adventure> findAll(Pageable pageable);
	
	public Adventure findOneById(Long id);

    //List<Offer> findAllByClientId(Long clientId);

	List<Offer> findAllByClientId(long subscribers);


	@Query(value="select * from adventure a where a.owner=?1",nativeQuery = true)
	public List<Adventure> findByOwnerId(Long id);

	@Transactional
    @Query(value = "SELECT * FROM adventure c WHERE c.deleted is false", nativeQuery = true)
	public List<Adventure> findActiveAdventures();

	@Transactional
    @Query(value = "SELECT * FROM adventure c WHERE c.deleted is true", nativeQuery = true)
	public List<Adventure> findDeletedAdventures();

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query(value = "select p from Adventure p where p.id = :id")
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
	Adventure findOneTryOccupation(@Param("id")long id);

	
/*	@Query(value = "SELECT a FROM adventure JOIN FETCH a.behavior_rule br WHERE a.id=?1")
	public Adventure fetchAdventureWithBehaviorRule(Long id);
*/	
}


