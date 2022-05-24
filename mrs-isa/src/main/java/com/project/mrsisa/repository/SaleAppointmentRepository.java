package com.project.mrsisa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.mrsisa.domain.SaleAppointment;

@Repository
public interface SaleAppointmentRepository extends JpaRepository<SaleAppointment, Long> {
	
	public Page<SaleAppointment> findAll(Pageable pageable);

	public SaleAppointment findOneById(Long id);
	
}
