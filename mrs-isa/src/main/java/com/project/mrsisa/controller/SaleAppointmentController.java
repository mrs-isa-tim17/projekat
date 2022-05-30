package com.project.mrsisa.controller;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.project.mrsisa.domain.*;
import com.project.mrsisa.dto.client.ReserveSaleAppointmentRequestDTO;
import com.project.mrsisa.dto.client.SuccessResponseDTO;
import com.project.mrsisa.exception.TooHighPenaltyNumber;
import com.project.mrsisa.service.*;
import org.aspectj.weaver.AnnotationNameValuePair;
import java.util.ArrayList;
import java.util.List;

import com.project.mrsisa.dto.client.SaleAppoinmentClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.project.mrsisa.dto.SaleAppointmentDTO;
import com.project.mrsisa.dto.StartEndDateDTO;
import com.project.mrsisa.dto.TextDTO;
import com.project.mrsisa.domain.SaleAppointment;
import com.project.mrsisa.dto.SaleAppointmentDTO;
import com.project.mrsisa.service.AdditionalServicesService;
import com.project.mrsisa.service.AdventureService;
import com.project.mrsisa.service.PeriodAvailabilitySerivce;

import javax.mail.MessagingException;


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

	@Autowired
	private ClientService clientService;
	
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
			saleAppointment.setOfferType(OfferType.ADVENTURE);
			
			List<AdditionalServices> additionalServices = new ArrayList<AdditionalServices>();
			for(String service : saleAppointmentDTO.getAdditionalServices()) 
			{
				AdditionalServices as = additionalServicesService.findOneByName(service);
				additionalServices.add(as);	
			}
			saleAppointment.setOffer(adventure);
			saleAppointment.setAdditionalServices(additionalServices);
			
			saleAppointmentService.save(saleAppointment);

			try {
				clientService.sendNotification(adventure, saleAppointment);
			} catch (MessagingException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

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

	@PostMapping(value = "/quick/reserve")
	@PreAuthorize("hasRole('CLIENT')")
	public ResponseEntity<SuccessResponseDTO> reserveSaleAppointment(@RequestBody ReserveSaleAppointmentRequestDTO dto){
		SuccessResponseDTO res = new SuccessResponseDTO();
		try {
			saleAppointmentService.reserveSaleAppointment(dto);
			res.setSuccessful(true);
			return new ResponseEntity<SuccessResponseDTO>(res, HttpStatus.OK);
		}catch (ObjectOptimisticLockingFailureException e){
			res.setSuccessful(false);
			res.setExplanation("Ups, neko je stigao pre Vas");
			return new ResponseEntity<SuccessResponseDTO>(res, HttpStatus.OK);
		} catch (MessagingException me){
			res.setSuccessful(false);
			res.setExplanation("Izvinjavamo se, imamo problem sa slanjem mejla. Jeste li sigurni da ste dali ispravan mejl?");
			return new ResponseEntity<SuccessResponseDTO>(res, HttpStatus.OK);
		}catch (TooHighPenaltyNumber th){
			res.setSuccessful(false);
			res.setExplanation(th.getMessage());
			return new ResponseEntity<SuccessResponseDTO>(res, HttpStatus.OK);
		}catch (Exception e){
			res.setSuccessful(false);
			res.setExplanation("Došlo je do greške, probajte da rezervišete malo kasnije");
			return new ResponseEntity<SuccessResponseDTO>(res, HttpStatus.OK);
		}

	}
}
