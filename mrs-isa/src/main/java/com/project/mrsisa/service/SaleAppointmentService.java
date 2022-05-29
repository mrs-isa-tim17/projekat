package com.project.mrsisa.service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;

import com.project.mrsisa.converter.LocalDateTimeToString;
import com.project.mrsisa.domain.Client;
import com.project.mrsisa.domain.Reservation;
import com.project.mrsisa.dto.client.ReserveSaleAppointmentRequestDTO;
import com.project.mrsisa.exception.TooHighPenaltyNumber;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.project.mrsisa.domain.SaleAppointment;
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
	public void reserveSaleAppointment(ReserveSaleAppointmentRequestDTO dto) throws MessagingException, UnsupportedEncodingException, TooHighPenaltyNumber {
		SaleAppointment sa = findOneById(dto.getSaleAppointmentId());
		sa.setReserved(true);
		save(sa);
		Reservation r = new Reservation();
		Client c = clientService.findOne(dto.getClientId());
		if (c.getPenaltyNumber() >= 3)
			throw new TooHighPenaltyNumber("Zbog broja penala ste onemogućeni da rezervišete.");
		r.setStartDateTime(sa.getStartSaleDate());
		r.setEndDateTime(sa.getStartSaleDate().plusHours((long) sa.getDuration()));
		r.setPrice(sa.getDiscount());
		r.setOfferType(sa.getOfferType());
		r.setReviewed(false);
		r.setQuick(true);
		r.setCanceled(false);
		r.setClient(c);
		r.setOffer(sa.getOffer());//pukne?
		r.setShipOwnerPresent(false);
		reservationService.save(r);
		sendConfirmationMail(c, "offer name", r.getStartDateTime(), r.getEndDateTime());
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
}
