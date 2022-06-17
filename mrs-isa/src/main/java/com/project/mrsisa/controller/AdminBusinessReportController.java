package com.project.mrsisa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mrsisa.domain.Adventure;
import com.project.mrsisa.domain.Cottage;
import com.project.mrsisa.domain.OfferType;
import com.project.mrsisa.domain.PercentageOfEarnings;
import com.project.mrsisa.domain.Reservation;
import com.project.mrsisa.domain.Ship;
import com.project.mrsisa.domain.User;
import com.project.mrsisa.dto.AdminBusinessReportDTO;
import com.project.mrsisa.dto.AdminFullBusinessReportDTO;
import com.project.mrsisa.service.AdventureService;
import com.project.mrsisa.service.CottageService;
import com.project.mrsisa.service.PercentageOfEarningsService;
import com.project.mrsisa.service.ReservationService;
import com.project.mrsisa.service.ShipService;
import com.project.mrsisa.service.UserService;

@RestController
@RequestMapping(value = "/admin/business", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class AdminBusinessReportController {
	
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private PercentageOfEarningsService percentageOfEarningsService;
	@Autowired
	private CottageService cottageService;
	@Autowired
	private ShipService shipService;
	@Autowired
	private AdventureService adventureService;
	@Autowired
	private UserService userService;
	
	
/*	@PostMapping(value="/report") 
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<AdminBusinessReportDTO> getFullincomeForPeriod(@RequestBody AdminBusinessReportDTO adminBusinessReportDTO ) {
		List<Reservation> reservations = reservationService.getReservationsForPeriod(adminBusinessReportDTO.getStartDate(), adminBusinessReportDTO.getEndDate());
		PercentageOfEarnings activePercentage = percentageOfEarningsService.findActivePercentage();
		
		double sum = 0;
		
		for(Reservation r : reservations) {
			if(r.getStartDate().isAfter(activePercentage.getStartDate())) {
				sum += r.getPrice() * activePercentage.getPercentage() / 100;
			}else {
				PercentageOfEarnings percentage = percentageOfEarningsService.getPercentageOfEarningsForDate(r.getStartDate());
				sum += r.getPrice() * percentage.getPercentage() / 100;
			}
		}
		adminBusinessReportDTO.setIncome(sum);
		return new ResponseEntity<>(adminBusinessReportDTO, HttpStatus.OK);
	}
*/
	
	@PostMapping(value="/report") 
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<AdminFullBusinessReportDTO>> getBusinessReportForPeriod(@RequestBody AdminBusinessReportDTO adminBusinessReportDTO ) {
		List<Reservation> reservations = reservationService.getReservationsForPeriod(adminBusinessReportDTO.getStartDate(), adminBusinessReportDTO.getEndDate());
		PercentageOfEarnings activePercentage = percentageOfEarningsService.findActivePercentage();
		
		List<AdminFullBusinessReportDTO> businessReport = new ArrayList<AdminFullBusinessReportDTO>();
		
		if(reservations.size()==0) {
			return new ResponseEntity<>(businessReport, HttpStatus.OK);
		}
		
		for(Reservation r : reservations) {
			if(r.getStartDate().isAfter(activePercentage.getStartDate())) {
				AdminFullBusinessReportDTO dto = new AdminFullBusinessReportDTO();
				dto = setOfferNameAndOwner(r.getOfferType(), r.getOffer().getId(), dto);
				dto.setEndDate(r.getEndDate());
				dto.setStartDate(r.getStartDate());
				dto.setIncome(r.getPrice() * activePercentage.getPercentage() / 100);
				dto.setOfferType(r.getOfferType().toString());
				dto.setPercentage(activePercentage.getPercentage());
				dto.setPrice(r.getPrice());
				businessReport.add(dto);
				
			}else {
				PercentageOfEarnings percentage = percentageOfEarningsService.getPercentageOfEarningsForDate(r.getStartDate());
				
				AdminFullBusinessReportDTO dto = new AdminFullBusinessReportDTO();
				dto = setOfferNameAndOwner(r.getOfferType(), r.getOffer().getId(), dto);
				dto.setEndDate(r.getEndDate());
				dto.setEndDate(r.getEndDate());
				dto.setIncome(r.getPrice() * percentage.getPercentage() / 100);
				dto.setOfferType(r.getOfferType().toString());
				dto.setPercentage(percentage.getPercentage());
				dto.setPrice(r.getPrice());
				businessReport.add(dto);
			}
		}
		return new ResponseEntity<>(businessReport, HttpStatus.OK);
	}
	
	private AdminFullBusinessReportDTO setOfferNameAndOwner(OfferType type, long offerId, AdminFullBusinessReportDTO dto ) {
		String name= "";
		User owner = null;
		
		switch (type.getValue()) {
		case 0:  // cottage
			Cottage cottage = cottageService.findOne(offerId);
			dto.setOfferName(cottage.getName());
			owner = userService.findById(cottage.getOwner().getId());
			dto.setOwnerName(owner.getName());
			dto.setOwnerSurname(owner.getSurname());
			break;
		case 1:   // ship
			Ship ship = shipService.findOne(offerId);
			dto.setOfferName(ship.getName());
			owner = userService.findById(ship.getOwner().getId());
			dto.setOwnerName(owner.getName());
			dto.setOwnerSurname(owner.getSurname());	
			break;
		case 2:  // adventure
			Adventure adventure = adventureService.findOneById(offerId);
			dto.setOfferName(adventure.getName());
			owner = userService.findById(adventure.getOwner().getId());
			dto.setOwnerName(owner.getName());
			dto.setOwnerSurname(owner.getSurname());
			break;
	
		default:
			return dto;
		}
		return dto;
	}
	
}
