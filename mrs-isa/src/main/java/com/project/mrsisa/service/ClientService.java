package com.project.mrsisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mrsisa.domain.Client;
import com.project.mrsisa.repository.ClientRepository;

@Service
public class ClientService {
	@Autowired
	private ClientRepository clientRepository;

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
}
