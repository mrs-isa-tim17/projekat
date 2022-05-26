package com.project.mrsisa.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.project.mrsisa.dto.client.SaleAppoinmentClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.project.mrsisa.domain.AdditionalServices;
import com.project.mrsisa.domain.Address;
import com.project.mrsisa.domain.Adventure;
import com.project.mrsisa.domain.PeriodAvailability;
import com.project.mrsisa.domain.SaleAppointment;
import com.project.mrsisa.dto.SaleAppointmentDTO;
import com.project.mrsisa.service.AdditionalServicesService;
import com.project.mrsisa.service.AdventureService;
import com.project.mrsisa.service.PeriodAvailabilitySerivce;
import com.project.mrsisa.service.SaleAppointmentService;


@RestController
@RequestMapping(value="/sale", produces = MediaType.APPLICATION_JSON_VALUE)
public class SaleAppointmentController {
	@Autowired
	private SaleAppointmentService saleAppointmentService;
	@Autowired
	private AdventureService adventureService;
	@Autowired
	private AdditionalServicesService additionalServicesService;
	@Autowired
	private PeriodAvailabilitySerivce periodAvailabilityService;

	@GetMapping(value = "/quick/reservation/{id}")
	@PreAuthorize("hasRole('CLIENT')")
	public ResponseEntity<List<SaleAppoinmentClientDTO>> getSalesAppoinment(@PathVariable Long id){
		List<SaleAppointment> saleAppointments = saleAppointmentService.findActiveByOfferId(id);
		List<SaleAppoinmentClientDTO> saleAppointmentDTOs = new ArrayList<>();
		for (SaleAppointment sa : saleAppointments){
			sa.setAdditionalServices(additionalServicesService.findBySaleAppointmentId(sa.getId()));
			saleAppointmentDTOs.add(new SaleAppoinmentClientDTO(sa));
		}
		return new ResponseEntity<List<SaleAppoinmentClientDTO>>(saleAppointmentDTOs, HttpStatus.OK);
	}

}
