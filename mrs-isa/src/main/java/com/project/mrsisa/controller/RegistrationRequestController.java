package com.project.mrsisa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mrsisa.domain.Adventure;
import com.project.mrsisa.domain.Client;
import com.project.mrsisa.domain.CottageOwner;
import com.project.mrsisa.domain.FishingInstructor;
import com.project.mrsisa.domain.ProcessingStatus;
import com.project.mrsisa.domain.RegistrationRequest;
import com.project.mrsisa.domain.RegistrationType;
import com.project.mrsisa.domain.ShipOwner;
import com.project.mrsisa.domain.User;
import com.project.mrsisa.dto.AdventureDTO;
import com.project.mrsisa.dto.RegistrationRequestDTO;
import com.project.mrsisa.dto.TextDTO;
import com.project.mrsisa.service.ClientService;
import com.project.mrsisa.service.CottageOwnerService;
import com.project.mrsisa.service.FishingInstructorService;
import com.project.mrsisa.service.RegistrationRequestService;
import com.project.mrsisa.service.ShipOwnerService;
import com.project.mrsisa.service.UserService;

@RestController
@RequestMapping(value="/request/registration", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationRequestController {

	@Autowired
	private RegistrationRequestService registrationRequestService;
	
	@Autowired
	private ShipOwnerService shipOwnerService;
	
	@Autowired
	private CottageOwnerService cottageOwnerService;
	
	@Autowired
	private FishingInstructorService fishingInstructorService;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping(value = "/unprocessed")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<RegistrationRequestDTO>> getAllRegistrationRequest() {
		System.out.println("R - controler");
		
		List<RegistrationRequest> requests =  registrationRequestService.findUnprocessedRegistrationRequest();
	
		List<RegistrationRequestDTO> registrationRequestDTO = new ArrayList<>();
		for (RegistrationRequest request : requests) {
			System.out.println("RRR");
			System.out.println(request.getUserRef().getId());
			User u = null;
			System.out.println("ZAHTEV ZA REG");
			System.out.println(request.getUserRef().getId());
			if(request.getRegistrationType()==RegistrationType.COTTAGE_OWNER) {
				u = cottageOwnerService.findOne(request.getUserRef().getId());
			}else if(request.getRegistrationType()==RegistrationType.FISHING_INSTRUCTOR) {
				u = fishingInstructorService.findOne(request.getUserRef().getId());
			}else if(request.getRegistrationType()==RegistrationType.SHIP_OWNER) {
				u = shipOwnerService.findOne(request.getUserRef().getId());
			}
			System.out.println(request.getRegistrationType().toString());
			registrationRequestDTO.add(new RegistrationRequestDTO(u.getName(), u.getSurname(), request.getRegistrationType().toString(), u.getEmail(), request.getId(), request.getRequestMessage()));
		}
		return new ResponseEntity<>(registrationRequestDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/approve/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<TextDTO> approveRequest(@PathVariable Long id) {

		TextDTO res = registrationRequestService.approveRegistrationRequest(id);
		if (res.isSuccessfull()) {
			try {
				User u = userService.findById(Long.valueOf(res.getText()));
				registrationRequestService.sendMailAboutRegistrationProcessing(u.getEmail(), "");
				res.setText("Prihvatanje je uspešno");
				res.setSuccessfull(true);
			} catch (MailSendException me) {
				res.setText("Prihvatanje je uspešno");
				res.setSuccessfull(true);
			} catch (Exception e) {
				res.setSuccessfull(false);
				res.setText("Došlo je do greška tokom obrada zahteva");
			}
		}
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/reject/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<TextDTO> rejectRequest(@PathVariable Long id, @RequestBody TextDTO text ) {


		TextDTO res = registrationRequestService.rejectRegistrationRequest(id);
		if (res.isSuccessfull()) {
			try {
				User u = userService.findById(Long.valueOf(res.getText()));
				registrationRequestService.sendMailAboutRegistrationProcessing(u.getEmail(),text.getText());
				res.setText("Odbijanje je uspešno");
				res.setSuccessfull(true);
			} catch (MailSendException me) {
				res.setText("Odbijanje je uspešno");
				res.setSuccessfull(true);
			} catch (Exception e) {
				res.setSuccessfull(false);
				res.setText("Došlo je do greška tokom obrada zahteva");
			}
		}


		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
