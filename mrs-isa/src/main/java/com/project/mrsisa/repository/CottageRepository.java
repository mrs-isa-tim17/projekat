package com.project.mrsisa.repository;


import java.util.List;
import java.util.Optional;

import com.project.mrsisa.domain.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.transaction.annotation.Transactional;

import com.project.mrsisa.domain.Cottage;
import com.project.mrsisa.domain.CottageOwner;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;


public interface CottageRepository extends  JpaRepository<Cottage, Long>{
	
	public Page<Cottage> findAll(Pageable pageable);
	
	public List<Cottage> findByOwner(CottageOwner owner);

	public Optional<Cottage> findById(Long id);

	@Transactional
    @Query(value = "SELECT * FROM cottage c WHERE c.deleted is false", nativeQuery = true)
	public List<Cottage> findActiveCottages();
	
	@Transactional
    @Query(value = "SELECT * FROM cottage c WHERE c.deleted is true", nativeQuery = true)
	public List<Cottage> findDeletedCottages();


    List<Offer> findAllByClientId(Long id);

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query(value = "select p from Cottage p where p.id = :id")
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    Cottage findOneTryOccupation(Long id);
}
