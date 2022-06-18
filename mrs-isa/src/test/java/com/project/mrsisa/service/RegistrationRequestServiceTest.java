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
import com.project.mrsisa.dto.TextDTO;
import com.project.mrsisa.repository.RegistrationRequestRepository;
import static com.project.mrsisa.constants.ReservationRequestConstants.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistrationRequestServiceTest {    
	@Mock
	private RegistrationRequestRepository registrationRequestRepositoryMock;
	
	
	@InjectMocks
	private RegistrationRequestService registrationRequestService;
    
	@Test 
	public void testApproveRegistrationRequest() {
		
		RegistrationRequest r = new RegistrationRequest();
		r.setId(NEW_ID);
		r.setRegistrationType(RegistrationType.valueOf(NEW_REGISTRATION_TYPE));
		r.setStatus(ProcessingStatus.valueOf(NEW_STATUS));		
		
		when(registrationRequestRepositoryMock.findById(null)).thenReturn(Optional.of(r));
			
		TextDTO text = registrationRequestService.approveRegistrationRequest(NEW_ID);
				
        assertEquals(text.isSuccessfull(), true);
	}
	
	@Test 
	public void testRejectRegistrationRequest() {
		
		RegistrationRequest r = new RegistrationRequest();
		r.setId(R_ID);
		r.setRegistrationType(RegistrationType.valueOf(R_REGISTRATION_TYPE));
		r.setStatus(ProcessingStatus.valueOf(R_STATUS));		
		
		when(registrationRequestRepositoryMock.findById(null)).thenReturn(Optional.of(r));
			
		TextDTO text = registrationRequestService.rejectRegistrationRequest(R_ID);
				
        assertEquals(text.isSuccessfull(), true);
	}
}
