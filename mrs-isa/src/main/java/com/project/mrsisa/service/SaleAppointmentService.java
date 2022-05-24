package com.project.mrsisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mrsisa.domain.SaleAppointment;
import com.project.mrsisa.repository.SaleAppointmentRepository;

@Service
public class SaleAppointmentService {
	@Autowired
	private SaleAppointmentRepository saleAppointmentRepository;

	
	public List<SaleAppointment> findAll(){
		return saleAppointmentRepository.findAll();
	}
	
	public SaleAppointment findOneById(Long id) {
		return saleAppointmentRepository.findOneById(id);
	}
	
	public SaleAppointment save(SaleAppointment saleAppointment) {
		return saleAppointmentRepository.save(saleAppointment);
	}
}
