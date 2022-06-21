package com.project.mrsisa.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.project.mrsisa.converter.LocalDateTimeToString;
import com.project.mrsisa.domain.*;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.project.mrsisa.repository.ClientRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class ClientService {
	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private CottageService cottageService;
	@Autowired
	private ShipService shipService;
	@Autowired
	private AdventureService adventureService;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Environment env;

	@Autowired
	private ImageService imageService;

	@Transactional
    public void subscribeToOffer(Long clientId, Long offerId) {
		Client c = clientRepository.findById(clientId).orElse(null);
		List<Offer> subs = cottageService.findAllByClientId(clientId);
		subs.addAll(shipService.findAllByClientId(clientId));
		subs.addAll(adventureService.findAllByClientId(c));

		try {
			Offer cottage = cottageService.findOne(offerId);
			subs.add(cottage);
		}catch (Exception npe){

		}
		try {
			Offer ship = shipService.findOne(offerId);
			if (ship != null) {
				subs.add(ship);
			}
		}catch (Exception npe){

		}
		try {
			Offer adventure = adventureService.findOneById(offerId);
			if (adventure != null) {
				subs.add(adventure);
			}
		}catch (Exception npe){

		}

		c.setSubscriptions(subs);
		save(c);


	}

    public Client verify(String verificationCode){
		Client c = clientRepository.findByVerificationCode(verificationCode);
		if (c == null){
			return null;
		}else if(c.isEnabled()){
			return c;
		}else{
			c.setEnabled(true);
			clientRepository.save(c);
			return c;
		}
	}
	
	public Client save(Client client) {
		return clientRepository.save(client);
	}
	
	public List<Client> findAll(){
		return clientRepository.findAll();
	}
	
	public Client findOne(Long id) {
		return clientRepository.findById(id).orElseGet(null);
	}

	@Transactional
	public boolean findIfSubscribed(long clientId, long offerId){
		Client c = clientRepository.findById(clientId).orElse(null);
		List<Offer> subs = cottageService.findAllByClientId(clientId);
		subs.addAll(shipService.findAllByClientId(clientId));
		subs.addAll(adventureService.findAllByClientId(c));
		for(Offer o : subs){
			System.out.println(o.getId());
			if (o.getId() == offerId){
				return true;
			}
		}
		return false;
	}

	@Transactional
	public void unsubscribeFromOffer(Long clientId, Long offerId) {
		System.out.println(clientId);
		System.out.println(offerId);
		Client c = clientRepository.findById(clientId).orElse(null);
		List<Offer> subs = cottageService.findAllByClientId(clientId);
		subs.addAll(shipService.findAllByClientId(clientId));
		subs.addAll(adventureService.findAllByClientId(c));
		System.out.println(offerId);
		try {
			Cottage cottage = cottageService.findOne(offerId);
			subs.remove(cottage);
		}catch (Exception npe){

		}
		try {
			Ship ship = shipService.findOne(offerId);
			if (ship != null) {
				subs.remove(ship);
			}
		}catch (Exception npe){

		}
		try {
			Adventure adventure = adventureService.findOneById(offerId);
			if (adventure != null) {
				subs.remove(adventure);
			}
		}catch (Exception npe){

		}
		c.setSubscriptions(subs);
		save(c);

	}


	@Async
	public void sendNotification(Offer offer, SaleAppointment saleAppointment) throws MessagingException, UnsupportedEncodingException {

		List<Client> clients = clientRepository.findAllBySubscriptionsId(offer.getId());

		if (clients.size() == 0)
			return;

		LocalDateTimeToString format = new LocalDateTimeToString();

		String subject = "Nova mogućnost za brzu rezervaciju";
		String senderName = "BookerTeam";
		String mailContent = "<p>Entitet " + offer.getName() + " ima brzu akciju.</p>" +
				"<br>Početak:" + format.format(saleAppointment.getStartSaleDate()) + "<br>" +
				"Duration: " + saleAppointment.getDuration() +  "<br>" +
				"Broj ljudi: " + saleAppointment.getPeopleQuantity() +  "<br>" +
				"Price: " + saleAppointment.getDiscount() +  "<br>";

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		helper.setText(mailContent, true);
		helper.setSubject(subject);
		helper.setFrom(env.getProperty("spring.mail.username"));
		for (Client c : clients){
			helper.setTo(c.getEmail());
			javaMailSender.send(mimeMessage);
		}
		helper.setTo("evioletta121@gmail.com");
		javaMailSender.send(mimeMessage);

	}
	
	public Client findClientByEmail(String email) {
		return clientRepository.findClientByEmail(email);
	}




    public List<Offer> getEntitiesClientSubscribedFor(Long clientId) {
		Client c = clientRepository.findById(clientId).orElse(null);
		List<Offer> subs = cottageService.findAllByClientId(clientId);
		subs.addAll(shipService.findAllByClientId(clientId));
		subs.addAll(adventureService.findAllByClientId(c));
		for(Offer o : subs){
			o.setImages(imageService.findAllByOfferId(o.getId()));
		}
		return subs;
    }
}
