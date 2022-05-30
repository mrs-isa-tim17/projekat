package com.project.mrsisa.service;

import java.util.ArrayList;
import java.util.List;

import com.project.mrsisa.domain.AdditionalServices;
import com.project.mrsisa.domain.Offer;
import com.project.mrsisa.domain.Ship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mrsisa.domain.Client;
import com.project.mrsisa.repository.ClientRepository;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
    public void subscribeToOffer(Long clientId, Long offerId) {
		Client c = clientRepository.findById(clientId).orElse(null);
		List<Offer> subs = cottageService.findAllByClientId(clientId);
		subs.addAll(shipService.findAllByClientId(clientId));
		subs.addAll(adventureService.findAllByClientId(c));

		try {
			Offer cottage = cottageService.findOne(offerId);
			subs.add(cottage);
			System.out.println("cottage");
		}catch (NullPointerException npe){

		}
		try {
			Offer ship = shipService.findOne(offerId);
			if (ship != null) {
				subs.add(ship);
				System.out.println("ship");
			}
		}catch (NullPointerException npe){

		}
		try {
			Offer adventure = adventureService.findOneById(offerId);
			if (adventure != null) {
				subs.add(adventure);
				System.out.println("adventure");
			}
		}catch (NullPointerException npe){

		}
		System.out.println("nothing?");

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
			clientRepository.updateEnabledById(true, c.getId());
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
		//clientRepository.findAllBySubscriptionsId(offerId).size();
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
		Client c = clientRepository.findById(clientId).orElse(null);
		List<Offer> subs = cottageService.findAllByClientId(clientId);
		subs.addAll(shipService.findAllByClientId(clientId));
		subs.addAll(adventureService.findAllByClientId(c));

		try {
			Offer cottage = cottageService.findOne(offerId);
			subs.remove(cottage);
			System.out.println("cottage");
		}catch (NullPointerException npe){

		}
		try {
			Offer ship = shipService.findOne(offerId);
			if (ship != null) {
				subs.remove(ship);
				System.out.println("ship");
			}
		}catch (NullPointerException npe){

		}
		try {
			Offer adventure = adventureService.findOneById(offerId);
			if (adventure != null) {
				subs.remove(adventure);
				System.out.println("adventure");
			}
		}catch (NullPointerException npe){

		}
		System.out.println("nothing?");

		c.setSubscriptions(subs);
		save(c);

	}
}
