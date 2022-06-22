package com.project.mrsisa.service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;

import com.project.mrsisa.converter.LocalDateTimeToString;
import com.project.mrsisa.domain.*;
import com.project.mrsisa.dto.client.ReserveSaleAppointmentRequestDTO;
import com.project.mrsisa.exception.AlreadyCanceled;
import com.project.mrsisa.exception.NotDefinedValue;
import com.project.mrsisa.exception.TooHighPenaltyNumber;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.project.mrsisa.repository.SaleAppointmentRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SaleAppointmentService {
	@Autowired
	private SaleAppointmentRepository saleAppointmentRepository;

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Environment env;

	@Autowired
	private CottageService cottageService;

	@Autowired
	private ShipService shipService;

	@Autowired
	private AdventureService adventureService;

	@Autowired
	private DeleteRequestService deleteRequestService;

	public List<SaleAppointment> findAll(){
		return saleAppointmentRepository.findAll();
	}
	
	public SaleAppointment findOneById(Long id) {
		return saleAppointmentRepository.findOneById(id);
	}
	
	public SaleAppointment save(SaleAppointment saleAppointment) {
		return saleAppointmentRepository.save(saleAppointment);
	}
	public List<SaleAppointment> findActiveByOfferId(Long offerId) {
		return saleAppointmentRepository.findActiveFreeSaleAppoinments(offerId);
	}

	@Transactional
	@Async
	public void reserveSaleAppointment(ReserveSaleAppointmentRequestDTO dto) throws MessagingException, UnsupportedEncodingException, TooHighPenaltyNumber, NotDefinedValue {
		SaleAppointment sa = findOneById(dto.getSaleAppointmentId());
		Reservation r = reservationService.findOneById(sa.getReservation().getId());
		if (r == null){
			throw new NotDefinedValue("Ne postoji rezervacija sa datom identifikatorom");
		}
		sa.setReserved(true);
		save(sa);
		//Reservation r = new Reservation();
		Client c = clientService.findOne(dto.getClientId());

		System.out.println("SALEEEE");
		System.out.println(r.getOffer().getId());
		System.out.println(r.getStartDateTime());
		System.out.println(r.getEndDateTime());
		System.out.println(c.getName());

		if (c != null) {
			if (reservationService.checkIfCanceledReservationWithSameParametars(c, r.getOffer().getId(), r.getStartDateTime(), r.getEndDateTime()))
				throw new AlreadyCanceled("Već je otkazao rezervaciju sa istim parametrima");
		}


		r.setClient(c);
		System.out.println(c.getId());
		if (deleteRequestService.getIfUserMadeDeleteRequest(r.getClient().getId())){
			throw new NotDefinedValue("Napravili ste zahtev za brisanje naloga, dok zahtev ne bude odbijen, nećete moći da rezervišete");
		}
		if (c.getPenaltyNumber() >= 3)
			throw new TooHighPenaltyNumber("Zbog broja penala ste onemogućeni da rezervišete.");
		r.setClient(c);
		try {
			reservationService.save(r);
			reservationService.addLoyaltyPointsToClient(c);
			Offer o = null;
			if (r.getOfferType() == OfferType.COTTAGE){
				o = cottageService.findOne(r.getOffer().getId());
			}else if (r.getOfferType() == OfferType.SHIP){
				o = shipService.findOne(r.getOffer().getId());
			}else if (r.getOfferType() == OfferType.ADVENTURE){
				o = adventureService.findOneById(r.getOffer().getId());
			}
			reservationService.addLoyaltyPointsToOwner(o, r.getOfferType());
		}catch (Exception e){
			e.printStackTrace();
			throw new NotDefinedValue("Greška se desilo prilikom napravljanje rezervacije");
		}
		System.out.println("");
		try{
		sendConfirmationMail(c, "offer name", r.getStartDateTime(), r.getEndDateTime());

		}catch (Exception e){
			e.printStackTrace();
		}
    }


	@Async
	private void sendConfirmationMail(Client c, String offerName, LocalDateTime from, LocalDateTime until) throws MessagingException, UnsupportedEncodingException {
		String subject = "Potvrda o rezervaciji";
		LocalDateTimeToString dateTimeFormatter = new LocalDateTimeToString();
		String mailContent = "<p>Uspešno se rezervisali entitet: " + offerName +
				", u periodu od <strong> " + dateTimeFormatter.format(from) + "</strong> do: <strong>" + dateTimeFormatter.format(until) + "</strong>.</p>";

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		helper.setText(mailContent, true);
		helper.setTo(c.getEmail());
		helper.setSubject(subject);
		helper.setFrom(env.getProperty("spring.mail.username"));
		javaMailSender.send(mimeMessage);
	}
	
	public List<SaleAppointment> findAllByOfferId(Long offerId){
		return saleAppointmentRepository.findAllByOfferId(offerId);
	}

	public List<SaleAppointment> getListOfReservationByOfferInInterval(Long id, LocalDateTime dateFrom, LocalDateTime dateUntil) {
		return saleAppointmentRepository.findCurrentSaleAppointmentInInterval(id, dateFrom, dateUntil);
	}

    public SaleAppointment findSaleAppointmentByOfferIdAndPeriod(Long id, LocalDateTime startDateTime) {
		return saleAppointmentRepository.findSaleAppointmentByOfferIdAndPeriod(id, startDateTime);
    }
}
