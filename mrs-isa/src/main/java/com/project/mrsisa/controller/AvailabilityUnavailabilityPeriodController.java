package com.project.mrsisa.controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.project.mrsisa.domain.*;
import com.project.mrsisa.service.*;
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

import com.project.mrsisa.dto.StartEndDateDTO;
import com.project.mrsisa.dto.StartEndDateTimeDefineDTO;


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
	private CottageService cottageService;
	@Autowired
	private ShipService shipService;
	@Autowired
	private ReservationService reservationService;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	@Autowired
	private SaleAppointmentService saleAppointmentService;

	
	@PostMapping(value = "/availability/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('FISHINSTRUCTOR') or hasRole('COTTAGE_OWNER') or hasRole('SHIP_OWNER')")
	public ResponseEntity<Boolean> defineAvailabilityPeriod(@PathVariable Long id, @RequestBody StartEndDateTimeDefineDTO startEndDateTimeDefineDTO){
		//System.out.println("MILICAAA");
		
		PeriodAvailability periodAvailability = new PeriodAvailability();
		Adventure adventure = adventureService.findOneById(id);
		periodAvailability.setOffer(adventure);
		periodAvailability.setStartDate(startEndDateTimeDefineDTO.getStart().plusHours(2));
		periodAvailability.setEndDate(startEndDateTimeDefineDTO.getEnd().plusHours(2));
		
		periodAvailabilityService.save(periodAvailability);
		
		return new ResponseEntity<>(true , HttpStatus.CREATED);	
	}
	
	
	@PostMapping(value = "/unavailability/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('FISHINSTRUCTOR') or hasRole('COTTAGE_OWNER') or hasRole('SHIP_OWNER')")
	public ResponseEntity<Boolean> defineUnavailabilityPeriod(@PathVariable Long id, @RequestBody StartEndDateTimeDefineDTO startEndDateTimeDefineDTO){
		
		PeriodUnavailability periodUnavailability = new PeriodUnavailability();
		if(startEndDateTimeDefineDTO.getOfferType().equals("cottage")){
			Cottage cottage = cottageService.findOne(id);
			periodUnavailability.setOffer(cottage);
		}
		else if(startEndDateTimeDefineDTO.getOfferType().equals("ship")){
			Ship ship = shipService.findOne(id);
			periodUnavailability.setOffer(ship);
		}
		else{
			Adventure adventure = adventureService.findOneById(id);
			periodUnavailability.setOffer(adventure);
		}

		List<PeriodAvailability> availabilityPeriods = periodAvailabilityService.getListOfAvailbilityForOffer(id);
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
		List<Reservation> reservations = reservationService.getAllReservationsForOffer(id);

		List<SaleAppointment> actions = saleAppointmentService.findAllByOfferId(id);

		List<StartEndDateDTO> intersectionAll = periodAvailabilityService.intersectionPeriodsForAvailability(availability, unavailability, reservations, actions);

		return new ResponseEntity<>(intersectionAll, HttpStatus.OK);
	}
	
	

	@GetMapping(value="/unavailability/all/{id}")
	@PreAuthorize("hasRole('FISHINSTRUCTOR') or hasRole('COTTAGE_OWNER')or hasRole('SHIP_OWNER')")
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
