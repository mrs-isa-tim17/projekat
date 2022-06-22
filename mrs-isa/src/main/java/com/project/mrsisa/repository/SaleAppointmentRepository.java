package com.project.mrsisa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.mrsisa.domain.SaleAppointment;
import org.springframework.data.jpa.repository.Query;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface SaleAppointmentRepository extends JpaRepository<SaleAppointment, Long> {
	
	public Page<SaleAppointment> findAll(Pageable pageable);

	public SaleAppointment findOneById(Long id);


	@Transactional
	@Query(value="SELECT * from sale_appointment r WHERE r.offer_id=?1 and r.start_sale_date > CURRENT_DATE and reserved = false", nativeQuery = true)
	List<SaleAppointment> findActiveFreeSaleAppoinments(Long id);

	@Transactional
	@Query(value="SELECT * from sale_appointment r WHERE r.offer_id=?1 order by start_sale_date", nativeQuery = true)
	public List<SaleAppointment> findAllByOfferId(Long offerId);

	@Query(value="SELECT * FROM sale_appointment r WHERE r.offer_id=?1 and (r.start_sale_date < ?2 or r.start_sale_date < ?3)", nativeQuery = true)
    public List<SaleAppointment> findCurrentSaleAppointmentInInterval(Long id, LocalDateTime dateFrom, LocalDateTime dateUntil);

	@Query(value="SELECT * from sale_appointment r WHERE r.offer_id=?1 and r.start_sale_date = ?2", nativeQuery = true)
	SaleAppointment findSaleAppointmentByOfferIdAndPeriod(Long id, LocalDateTime startDateTime);
}
