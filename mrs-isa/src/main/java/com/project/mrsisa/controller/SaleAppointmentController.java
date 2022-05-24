package com.project.mrsisa.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.AnnotationNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mrsisa.domain.AdditionalServices;
import com.project.mrsisa.domain.Address;
import com.project.mrsisa.domain.Adventure;
import com.project.mrsisa.domain.PeriodAvailability;
import com.project.mrsisa.domain.PeriodUnavailability;
import com.project.mrsisa.domain.Reservation;
import com.project.mrsisa.domain.SaleAppointment;
import com.project.mrsisa.dto.SaleAppointmentDTO;
import com.project.mrsisa.dto.StartEndDateDTO;
import com.project.mrsisa.dto.TextDTO;
import com.project.mrsisa.service.AdditionalServicesService;
import com.project.mrsisa.service.AdventureService;
import com.project.mrsisa.service.PeriodAvailabilitySerivce;
import com.project.mrsisa.service.PeriodUnavailabilityService;
import com.project.mrsisa.service.ReservationService;
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
	@Autowired
	private PeriodUnavailabilityService periodUnavailabilityService;
	@Autowired
	private ReservationService reservationService;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	
	
	
	@PostMapping(value = "/define/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('FISHINSTRUCTOR')")
	public ResponseEntity<TextDTO> defineSaleAppointment(@PathVariable Long id, @RequestBody SaleAppointmentDTO saleAppointmentDTO){
		
		Adventure adventure = adventureService.findOneById(id);
	
		System.out.println("CENa: "+saleAppointmentDTO.getDuration() );
		
		Address address = new Address(saleAppointmentDTO.getLongitude(), saleAppointmentDTO.getLatitude());
		
		
		if (isInCorrectPeriod(id, saleAppointmentDTO.getStartDateTime(), saleAppointmentDTO.getDuration())) {
		
			SaleAppointment saleAppointment = new SaleAppointment();
			saleAppointment.setAddress(address);
			saleAppointment.setDiscount(saleAppointmentDTO.getPrice());
			saleAppointment.setDuration(saleAppointmentDTO.getDuration());
			saleAppointment.setPeopleQuantity(saleAppointmentDTO.getPeopleQuantity());
			saleAppointment.setStartSaleDate(saleAppointmentDTO.getStartDateTime().plusHours(2));
			
			List<AdditionalServices> additionalServices = new ArrayList<AdditionalServices>();
			for(String service : saleAppointmentDTO.getAdditionalServices()) 
			{
				AdditionalServices as = additionalServicesService.findOneByName(service);
				additionalServices.add(as);	
			}
			saleAppointment.setOffer(adventure);
			saleAppointment.setAdditionalServices(additionalServices);
			
			saleAppointmentService.save(saleAppointment);
			return new ResponseEntity<>(new TextDTO("Uspešno dodata akcija") , HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(new TextDTO("Definistite akciju u periodu dostupnosti") , HttpStatus.OK);
			
		}
	}
	
	private Boolean isInCorrectPeriod(Long id, LocalDateTime dateTime, double duration) {
		List<PeriodAvailability> availabilityPeriods = periodAvailabilityService.getListOfAvailbilityForOffer(id);
		List<PeriodUnavailability> unavailability = periodUnavailabilityService.getListOfUnavailbilityForOffer(id);
		List<Reservation> reservations = reservationService.getAllReservationsForAdventure(id);
	
		List<StartEndDateDTO> intersectionAll = periodAvailabilityService.intersectionPeriodsForAvailability(availabilityPeriods, unavailability, reservations);
			
		
		String bob = Double.toString(duration);
		LocalDateTime endDateTime;
		if(bob.contains("\\.")) {
			String[] convert = bob.split("\\.");
			long hours = Integer.parseInt(convert[0]);
			long minutes  = (long) (Integer.parseInt(convert[1]) * 0.6);
			endDateTime = dateTime.plusHours(hours+2).plusMinutes(minutes);
			
		}else {
			endDateTime = dateTime.plusHours((long) duration).plusHours(2);
		}
		
		for (StartEndDateDTO availPeriod : intersectionAll) {
			System.out.println(availPeriod);
			if((LocalDateTime.parse(availPeriod.getStart(), formatter)).isBefore(dateTime) && (LocalDateTime.parse(availPeriod.getEnd(), formatter).isAfter(endDateTime))){
				System.out.println("PROSLOOO");
				System.out.println("start: " + dateTime + " end: "  + endDateTime);
				return true;
			}
		}
		return false;
		
	}
	
}
