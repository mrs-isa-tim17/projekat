package com.project.mrsisa.repository;

import java.util.List;

import com.project.mrsisa.domain.Offer;
import com.project.mrsisa.domain.ShipOwner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.project.mrsisa.domain.Ship;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

public interface ShipRepository extends JpaRepository<Ship,Long>{
	public Page<Ship> findAll(Pageable pageable);
	public List<Ship> findByOwner(ShipOwner owner);
	
	@Transactional
    @Query(value = "SELECT * FROM ship c WHERE c.deleted is false", nativeQuery = true)
	public List<Ship> findActiveShips();
	
	@Transactional
    @Query(value = "SELECT * FROM ship c WHERE c.deleted is true", nativeQuery = true)
	public List<Ship> findDeletedShips();

    //List<Offer> findBySubscribers_Id(Long clientId);

	List<Offer> findAllByClientId(Long id);

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query(value = "select p from Ship p where p.id = :id")
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    Ship findOneTryOccupation(@Param("id")Long id);
}
