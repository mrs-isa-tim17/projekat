package com.project.mrsisa.repository;

import java.util.List;

import com.project.mrsisa.domain.SaleAppointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.mrsisa.domain.AdditionalServices;

public interface AdditionalServicesRepository extends JpaRepository<AdditionalServices, Long> {
	
	public Page<AdditionalServices> findAll(Pageable pageable);

	public AdditionalServices findOneById(Long id);
	
	public List<AdditionalServices> findAllByOfferId(Long id);

	public AdditionalServices findOneByName(String name);

	List<AdditionalServices> findAllBySaleAppointmentId(Long id);




}