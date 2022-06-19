package com.project.mrsisa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.mrsisa.domain.ProcessingStatus;
import com.project.mrsisa.domain.RegistrationRequest;
import com.project.mrsisa.domain.RegistrationType;
import com.project.mrsisa.domain.User;
import com.project.mrsisa.dto.TextDTO;
import com.project.mrsisa.repository.RegistrationRequestRepository;
import com.project.mrsisa.repository.UserRepository;

import static com.project.mrsisa.constants.ReservationRequestConstants.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistrationRequestServiceTest {    
	@Mock
	private RegistrationRequestRepository registrationRequestRepositoryMock;
	@Mock
	private UserRepository userRepositoryMock;
	@Mock
	private UserService userService;
	
	@InjectMocks
	private RegistrationRequestService registrationRequestService;

    
	@Test 
	public void testApproveRegistrationRequest() {
		
		User u = new User();
		u.setId(USER_ID);
		u.setEmail(EMAIL);
		
		RegistrationRequest r = new RegistrationRequest();
		r.setId(NEW_ID);
		r.setRegistrationType(RegistrationType.valueOf(NEW_REGISTRATION_TYPE));
		r.setStatus(ProcessingStatus.valueOf(NEW_STATUS));	
		r.setUserRef(u);
		
		when(registrationRequestRepositoryMock.findOneById(NEW_ID)).thenReturn(r);
		when(userRepositoryMock.findById(USER_ID)).thenReturn(Optional.of(u));	
		when(userService.findById(USER_ID)).thenReturn(u);
		
		TextDTO text = registrationRequestService.approveRegistrationRequest(NEW_ID);
				
        assertEquals(true, text.isSuccessfull());
	}
	
    
	@Test 
	public void testFalseApproveRegistrationRequest() {
		
		User u = new User();
		u.setId(USER_ID);
		u.setEmail(EMAIL);
		
		RegistrationRequest r = new RegistrationRequest();
		r.setId(NEW_ID);
		r.setRegistrationType(RegistrationType.valueOf(NEW_REGISTRATION_TYPE));
		r.setStatus(ProcessingStatus.valueOf(NEW_STATUS));	
		r.setUserRef(u);
		
		when(registrationRequestRepositoryMock.findOneById(NEW_ID)).thenReturn(r);
		when(userRepositoryMock.findById(USER_ID)).thenReturn(Optional.of(u));	
		when(userService.findById(USER_ID)).thenReturn(u);
		
		TextDTO text = registrationRequestService.approveRegistrationRequest(USER_ID);
				
        assertEquals(false, text.isSuccessfull());
	}	
	
	@Test 
	public void testRejectRegistrationRequest() {
		User u = new User();
		u.setId(USER_ID);
		u.setEmail(EMAIL);
		
		RegistrationRequest r = new RegistrationRequest();
		r.setId(R_ID);
		r.setRegistrationType(RegistrationType.valueOf(R_REGISTRATION_TYPE));
		r.setStatus(ProcessingStatus.valueOf(R_STATUS));		
		r.setUserRef(u);
		
		when(registrationRequestRepositoryMock.findOneById(R_ID)).thenReturn(r);
		when(userRepositoryMock.findById(USER_ID)).thenReturn(Optional.of(u));	
		when(userService.findById(USER_ID)).thenReturn(u);
					
		TextDTO text = registrationRequestService.rejectRegistrationRequest(R_ID);
				
        assertEquals(true, text.isSuccessfull());
	}
}
