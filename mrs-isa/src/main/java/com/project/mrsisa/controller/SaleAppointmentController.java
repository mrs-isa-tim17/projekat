package com.project.mrsisa.controller;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.project.mrsisa.domain.*;
import com.project.mrsisa.dto.*;
import com.project.mrsisa.dto.client.ReserveSaleAppointmentRequestDTO;
import com.project.mrsisa.dto.client.SuccessResponseDTO;
import com.project.mrsisa.exception.AlreadyCanceled;
import com.project.mrsisa.exception.NotAvailable;
import com.project.mrsisa.exception.NotDefinedValue;
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
import org.springframework.mail.MailSendException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.project.mrsisa.dto.ReserveEntityDTO;
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

	@Autowired
	private CottageService cottageService;

	@Autowired
	private ShipService shipService;
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
		
			ReserveEntityDTO reservation = new ReserveEntityDTO();
			
			
		    reservation.setClientId(-1);
			reservation.setOfferId(adventure.getId());
			reservation.setOfferType("adventure");
			reservation.setChosenAdditionalServices(saleAppointmentDTO.getAdditionalServices());
			reservation.setFromDate(saleAppointmentDTO.getStartDateTime().plusHours(2));
			String bob = Double.toString(saleAppointmentDTO.getDuration());
			LocalDateTime endDateTime;
			if(bob.contains("\\.")) {
				String[] convert = bob.split("\\.");
				long hours = Integer.parseInt(convert[0]);
				long minutes  = (long) (Integer.parseInt(convert[1]) * 0.6);
				endDateTime = saleAppointmentDTO.getStartDateTime().plusHours(2).plusMinutes(minutes);
			}else {
				endDateTime = saleAppointmentDTO.getStartDateTime().plusHours((long) saleAppointmentDTO.getDuration()).plusHours(2);
			}

			reservation.setUntilDate(endDateTime);
			reservation.setShipOwnerPresent(false);
			
			try {
				Reservation madeReservation = reservationService.makeReservation(reservation);
			
				SaleAppointment saleAppointment = new SaleAppointment();
				saleAppointment.setReservation(madeReservation);
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
	//			clientService.sendNotification(adventure, saleAppointment);
			
			} catch (MailSendException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
				return new ResponseEntity<>(new TextDTO("Za ovaj period je već napravljena rezervacija od strane klijenta.") , HttpStatus.OK);
			}
			
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
			saleAppointment.setOfferType(OfferType.COTTAGE);

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

	@PostMapping(value = "/ship/define/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('SHIP_OWNER')")
	public ResponseEntity<TextDTO> defineSaleAppointmentShip(@PathVariable Long id, @RequestBody SaleAppointmentDTO saleAppointmentDTO) throws NotAvailable, TooHighPenaltyNumber, MessagingException, AlreadyCanceled, NotDefinedValue {

		Ship ship = shipService.findOne(id);

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
			saleAppointment.setOfferType(OfferType.SHIP);

			List<AdditionalServices> additionalServices = new ArrayList<AdditionalServices>();
			for(String service : saleAppointmentDTO.getAdditionalServices())
			{
				AdditionalServices as = additionalServicesService.findOneByName(service);
				additionalServices.add(as);
			}
			saleAppointment.setOffer(ship);
			saleAppointment.setAdditionalServices(additionalServices);
			saleAppointment.setPeopleQuantity(ship.getCapacity());
			ReserveEntityDTO reserveDTO = new ReserveEntityDTO();
			reserveDTO.setOfferType("ship");
			reserveDTO.setShipOwnerPresent(false); ////ovo proveri!!!!!!
			reserveDTO.setChosenAdditionalServices(saleAppointmentDTO.getAdditionalServices());
			reserveDTO.setOfferId(ship.getId());
			reserveDTO.setFromDate(saleAppointmentDTO.getStartDateTime());
			reserveDTO.setUntilDate(saleAppointmentDTO.getStartDateTime().plusDays((long) saleAppointmentDTO.getDuration()));
			reserveDTO.setPrice(saleAppointmentDTO.getPrice());
			Reservation r = reservationService.makeReservation(reserveDTO);
			saleAppointment.setReservation(r);
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
		List<Reservation> reservations = reservationService.findAllOrdinaryReservationsForOffer(id);
		List<Reservation> actions = reservationService.findAllQuickReservationsForOffer(id);
	
		List<StartEndDateDTO> intersectionAll = periodAvailabilityService.intersectionPeriodsForAvailability(availabilityPeriods,
				unavailability, reservations, actions);
			
		
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
		List<Reservation> apponiments = reservationService.findAllQuickReservationsForOffer(id);
		for(Reservation sale : apponiments) {
			
			//LocalDateTime end  = sale.getStartSaleDate().plusHours((long) sale.getDuration() +2);
			
			StartEndDateDTO period = new StartEndDateDTO(sale.getStartDateTime().format(formatter), sale.getEndDateTime().format(formatter), adventure.getName());
			quickReservationPeriods.add(period);
		}
		
		return new ResponseEntity<>(quickReservationPeriods, HttpStatus.OK);
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
