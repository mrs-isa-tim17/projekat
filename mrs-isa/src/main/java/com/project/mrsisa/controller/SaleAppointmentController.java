package com.project.mrsisa.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.project.mrsisa.domain.*;
import com.project.mrsisa.service.*;
import org.aspectj.weaver.AnnotationNameValuePair;
import java.util.ArrayList;
import java.util.List;

import com.project.mrsisa.dto.client.SaleAppoinmentClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.project.mrsisa.dto.SaleAppointmentDTO;
import com.project.mrsisa.dto.StartEndDateDTO;
import com.project.mrsisa.dto.TextDTO;
import com.project.mrsisa.domain.SaleAppointment;
import com.project.mrsisa.dto.SaleAppointmentDTO;
import com.project.mrsisa.service.AdditionalServicesService;
import com.project.mrsisa.service.AdventureService;
import com.project.mrsisa.service.PeriodAvailabilitySerivce;


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

	@Autowired
	private CottageService cottageService;
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

	@PostMapping(value = "/cottage/define/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('COTTAGE_OWNER')")
	public ResponseEntity<TextDTO> defineSaleAppointmentCottage(@PathVariable Long id, @RequestBody SaleAppointmentDTO saleAppointmentDTO){

		Cottage cottage = cottageService.findOne(id);

		System.out.println("CENa: "+saleAppointmentDTO.getDuration() );

		Address address = new Address(saleAppointmentDTO.getLongitude(), saleAppointmentDTO.getLatitude());


		if (isInCorrectPeriod(id, saleAppointmentDTO.getStartDateTime(), saleAppointmentDTO.getDuration())) {

			SaleAppointment saleAppointment = new SaleAppointment();
			saleAppointment.setAddress(address);
			saleAppointment.setDiscount(saleAppointmentDTO.getPrice());
			saleAppointment.setDuration(saleAppointmentDTO.getDuration());
			saleAppointment.setPeopleQuantity(saleAppointmentDTO.getPeopleQuantity());
			System.out.println(saleAppointmentDTO.getStartDateTime());
			saleAppointment.setStartSaleDate(saleAppointmentDTO.getStartDateTime());

			List<AdditionalServices> additionalServices = new ArrayList<AdditionalServices>();
			for(String service : saleAppointmentDTO.getAdditionalServices())
			{
				AdditionalServices as = additionalServicesService.findOneByName(service);
				additionalServices.add(as);
			}
			saleAppointment.setOffer(cottage);
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
		List<Reservation> reservations = reservationService.getAllReservationsForOffer(id);
	
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
	
	@GetMapping(value="/quick/reservation/periods/{id}")
	@PreAuthorize("hasRole('FISHINSTRUCTOR') or hasRole('ROLE_COTTAGE_OWNER') or hasRole('ROLE_SHIP_OWNER')")
	public ResponseEntity<List<StartEndDateDTO>> getQuickReservationPeriods(@PathVariable Long id){
		List<StartEndDateDTO> quickReservationPeriods = new ArrayList<StartEndDateDTO>();
		
		Adventure adventure = adventureService.findOneById(id);
		List<SaleAppointment> apponiments = saleAppointmentService.findAllByOfferId(id);
		for(SaleAppointment sale : apponiments) {
			
			LocalDateTime end  = sale.getStartSaleDate().plusHours((long) sale.getDuration() +2);
			
			StartEndDateDTO period = new StartEndDateDTO(sale.getStartSaleDate().format(formatter), end.format(formatter), adventure.getName());
			quickReservationPeriods.add(period);
		}
		
		return new ResponseEntity<>(quickReservationPeriods, HttpStatus.OK);
	}

}
