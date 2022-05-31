package com.project.mrsisa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.mrsisa.domain.SaleAppointment;
import org.springframework.data.jpa.repository.Query;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface SaleAppointmentRepository extends JpaRepository<SaleAppointment, Long> {
	
	public Page<SaleAppointment> findAll(Pageable pageable);

	public SaleAppointment findOneById(Long id);


	@Transactional
	@Query(value="SELECT * from sale_appointment r WHERE r.offer_id=?1 and r.start_sale_date > CURRENT_DATE and reserved = false", nativeQuery = true)
	List<SaleAppointment> findActiveFreeSaleAppoinments(Long id);

	@Transactional
	@Query(value="SELECT * from sale_appointment r WHERE r.offer_id=?1", nativeQuery = true)
	public List<SaleAppointment> findAllByOfferId(Long offerId);


}
