package com.project.mrsisa.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.project.mrsisa.domain.RegistrationRequest;
import com.project.mrsisa.repository.RegistrationRequestRepository;


@Service
public class RegistrationRequestService {

	@Autowired
	private RegistrationRequestRepository registrationRequestRepository;
	
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
    private Environment env;
	

    public RegistrationRequest save(RegistrationRequest registrationRequest,String email, String text) {
        if(text.equals("")) {
        	try {
				sendMeesgeAboutApprovementRegistration(email);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
        }else {
        	try {
				sendMeesgeAboutRejectRegistration(email, text);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} 
        }
    	return registrationRequestRepository.save(registrationRequest);
    }

    public List<RegistrationRequest> findAll(){
        return registrationRequestRepository.findAll();
    }


    public RegistrationRequest findOneById(Long id) {
        return registrationRequestRepository.findOneById(id);
    }
    
    public List<RegistrationRequest> findByStatus(int status) {
    	return registrationRequestRepository.findByStatus(status);
    }
    
    public List<RegistrationRequest> findUnprocessedRegistrationRequest() {
    	return registrationRequestRepository.findUnprocessedRegistrationRequest();
    }

    @Async
    private void sendMeesgeAboutApprovementRegistration(String mail) throws MessagingException, UnsupportedEncodingException {
        String subject = "Odobrenje registracije";
        String senderName = "BookerTeam";
        String mailContent = "<p>Poštovani, odobren je vaš zahtev za registraciju na Book sajtu.</p> <br><br>" + senderName;

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(mailContent, true);
        helper.setTo(mail);
        helper.setSubject(subject);
        helper.setFrom(env.getProperty("spring.mail.username"));
        javaMailSender.send(mimeMessage);
        
        System.out.println("Poslat mail");
    }
    
    
    @Async
    private void sendMeesgeAboutRejectRegistration(String mail, String text) throws MessagingException, UnsupportedEncodingException {
        String subject = "Odobrenje registracije";
        String senderName = "BookerTeam";
        String mailContent = "<p>Poštovani, odbijen je vaš zahtev za registraciju na Book sajtu.\n</p>" + " <p>Obrazloženje:/n</p>"
         +text + "<br><br>" + senderName;
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(mailContent, true);
        helper.setTo(mail);
        helper.setSubject(subject);
        helper.setFrom(env.getProperty("spring.mail.username"));
        javaMailSender.send(mimeMessage);
        
        System.out.println("Poslat mail");

    }

}
