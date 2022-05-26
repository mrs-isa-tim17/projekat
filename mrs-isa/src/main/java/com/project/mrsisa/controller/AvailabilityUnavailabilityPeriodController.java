package com.project.mrsisa.controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mrsisa.domain.Adventure;
import com.project.mrsisa.domain.PeriodAvailability;
import com.project.mrsisa.domain.PeriodUnavailability;
import com.project.mrsisa.domain.Reservation;
import com.project.mrsisa.dto.StartEndDateDTO;
import com.project.mrsisa.dto.StartEndDateTimeDefineDTO;
import com.project.mrsisa.service.AdventureService;
import com.project.mrsisa.service.PeriodAvailabilitySerivce;
import com.project.mrsisa.service.PeriodUnavailabilityService;
import com.project.mrsisa.service.ReservationService;



@RestController
@RequestMapping(value="/period", produces = MediaType.APPLICATION_JSON_VALUE)
public class AvailabilityUnavailabilityPeriodController {
	
	@Autowired
	private PeriodAvailabilitySerivce periodAvailabilityService;
	
	@Autowired
	private PeriodUnavailabilityService periodUnavailabilityService;
	
	@Autowired
	private AdventureService adventureService;
	@Autowired
	private ReservationService reservationService;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	
	@PostMapping(value = "/availability/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('FISHINSTRUCTOR')")
	public ResponseEntity<Boolean> defineAvailabilityPeriod(@PathVariable Long id, @RequestBody StartEndDateTimeDefineDTO startEndDateTimeDefineDTO){
		System.out.println("MILICAAA");
		
		PeriodAvailability periodAvailability = new PeriodAvailability();
		Adventure adventure = adventureService.findOneById(id);
		periodAvailability.setOffer(adventure);
		periodAvailability.setStartDate(startEndDateTimeDefineDTO.getStart().plusHours(2));
		periodAvailability.setEndDate(startEndDateTimeDefineDTO.getEnd().plusHours(2));
		
		periodAvailabilityService.save(periodAvailability);
		
		return new ResponseEntity<>(true , HttpStatus.CREATED);	
	}
	
	
	@PostMapping(value = "/unavailability/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('FISHINSTRUCTOR')")
	public ResponseEntity<Boolean> defineUnavailabilityPeriod(@PathVariable Long id, @RequestBody StartEndDateTimeDefineDTO startEndDateTimeDefineDTO){
		
		PeriodUnavailability periodUnavailability = new PeriodUnavailability();
		
		Adventure adventure = adventureService.findOneById(id);
		List<PeriodAvailability> availabilityPeriods = periodAvailabilityService.getListOfAvailbilityForOffer(adventure.getId());
		System.out.println("OK-"+availabilityPeriods.size());
		
		boolean isCorect = false;
		for(PeriodAvailability pa : availabilityPeriods) {
			if((pa.getStartDate().isBefore(startEndDateTimeDefineDTO.getStart())) && (pa.getEndDate().isAfter(startEndDateTimeDefineDTO.getEnd())))
			{
				isCorect = true;
				break;
			}
		}
		if (isCorect==false) {	
			return new ResponseEntity<>(false , HttpStatus.CREATED);	
		}
		else {
			periodUnavailability.setOffer(adventure);
			periodUnavailability.setStartDate(startEndDateTimeDefineDTO.getStart().plusHours(2));
			periodUnavailability.setEndDate(startEndDateTimeDefineDTO.getEnd().plusHours(2));
			periodUnavailabilityService.save(periodUnavailability);
			return new ResponseEntity<>(true , HttpStatus.CREATED);	
		}
	}
	

	@GetMapping(value="/availability/all/{id}")
	public ResponseEntity<List<StartEndDateDTO>> getAvailabilityPeriodsForOffer(@PathVariable Long id) {
		List<PeriodAvailability> availability = periodAvailabilityService.getListOfAvailbilityForOffer(id);
		List<PeriodUnavailability> unavailability = periodUnavailabilityService.getListOfUnavailbilityForOffer(id);
		List<Reservation> reservations = reservationService.getAllReservationsForAdventure(id);
	
		List<StartEndDateDTO> intersectionAll = periodAvailabilityService.intersectionPeriodsForAvailability(availability, unavailability, reservations);
		
		return new ResponseEntity<>(intersectionAll, HttpStatus.OK);
	}
	
	

	@GetMapping(value="/unavailability/all/{id}")
	@PreAuthorize("hasRole('FISHINSTRUCTOR')")
	public ResponseEntity<List<StartEndDateDTO>> getUnavailabilityPeriodsForOffer(@PathVariable Long id) {

		List<PeriodUnavailability> unavailabilityPeriods = periodUnavailabilityService.getListOfUnavailbilityForOffer(id);
		List<StartEndDateDTO> startEndDateDTOs = new ArrayList<StartEndDateDTO>();
		for(PeriodUnavailability p : unavailabilityPeriods) {
			StartEndDateDTO period = new StartEndDateDTO(p.getStartDate().format(formatter), p.getEndDate().format(formatter), "  ");
			startEndDateDTOs.add(period);
		}
		return new ResponseEntity<>(startEndDateDTOs, HttpStatus.OK);
	}
	
	
}
