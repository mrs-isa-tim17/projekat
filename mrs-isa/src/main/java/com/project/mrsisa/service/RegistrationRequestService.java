package com.project.mrsisa.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.project.mrsisa.domain.ProcessingStatus;
import com.project.mrsisa.domain.User;
import com.project.mrsisa.dto.TextDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.project.mrsisa.domain.RegistrationRequest;
import com.project.mrsisa.repository.RegistrationRequestRepository;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RegistrationRequestService {

	@Autowired
	private RegistrationRequestRepository registrationRequestRepository;
	
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
    private Environment env;

    @Autowired
    private UserService userService;

    public RegistrationRequest save(RegistrationRequest registrationRequest) {
        return registrationRequestRepository.save(registrationRequest);
    }

    public void sendMailAboutRegistrationProcessing(String email, String text){
        if(text.equals("")) {
            try {
                sendMeesgeAboutApprovementRegistration(email);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            try {
                sendMeesgeAboutRejectRegistration(email, text);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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
        //javaMailSender.send(mimeMessage);
        
        System.out.println("Poslat mail");
    }
    
    
    @Async
    private void sendMeesgeAboutRejectRegistration(String mail, String text) throws MessagingException, UnsupportedEncodingException {
        String subject = "Odbijanje registracije";
        String senderName = "BookerTeam";
        String mailContent = "<p>Poštovani, odbijen je vaš zahtev za registraciju na Book sajtu.\n</p>" + " <p>Obrazloženje:\n</p>"
         +text + "<br><br>" + senderName;
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(mailContent, true);
        helper.setTo(mail);
        helper.setSubject(subject);
        helper.setFrom(env.getProperty("spring.mail.username"));
        //javaMailSender.send(mimeMessage);
        
        System.out.println("Poslat mail");

    }

    @Transactional
    public TextDTO approveRegistrationRequest(Long id) {

        TextDTO response = new TextDTO();
        RegistrationRequest request = findOneById(id);

        if (request == null) {
            response.setText("Zahtev nije pronađen");
            response.setSuccessfull(false);
            return response;
        } else if (request.getStatus() != ProcessingStatus.UNPROCESSED) {
            response.setSuccessfull(false);
            response.setText("Neko je odradio zahtev pre tebe");

            return response;
        }

        request.setStatus(ProcessingStatus.APPROVED);
        
        String text = "";
        response.setSuccessfull(false);
        try {
            save(request);
            
            User u = userService.findById(request.getUserRef().getId());
            u.setEnabled(true);
            userService.save(u);

            response.setSuccessfull(true);
            response.setText("" + request.getUserRef().getId());
            
        }catch (ObjectOptimisticLockingFailureException changedException){
            response.setSuccessfull(false);
            response.setText("Neko je odradio zahtev pre tebe");
        } catch (Exception e) {
            response.setSuccessfull(false);
            response.setText("Neko je obradio pre Vas");
        }
        return response;
    }

    @Transactional
    public TextDTO rejectRegistrationRequest(Long id) {
        TextDTO response = new TextDTO();

        RegistrationRequest request = findOneById(id);
        System.out.println("REquuuuuest");
        System.out.println(request.getId());
        System.out.println(request.getRegistrationType());
        System.out.println(request.getStatus());

        if (request == null) {
            response.setText("Zahtev nije pronađen");
            response.setSuccessfull(false);
            return response;
        }else if (request.getStatus() != ProcessingStatus.UNPROCESSED){
            response.setSuccessfull(false);
            response.setText("Neko je odradio zahtev pre tebe");
            return response;
        }

        request.setStatus(ProcessingStatus.REJECTED);

        System.out.println("STATUUSSSS");
        System.out.println(request.getStatus());
        
        String text = "";
        response.setSuccessfull(false);
        try {
            save(request);
            System.out.println(request.getStatus());
            
            response.setSuccessfull(true);
            response.setText("" + request.getUserRef().getId());
            
            System.out.println(response.getText());
            System.out.println(response.isSuccessfull());
        }catch (ObjectOptimisticLockingFailureException changedException){
            response.setSuccessfull(false);
            response.setText("Neko je odradio zahtev pre tebe");
        } catch (Exception e) {
            response.setSuccessfull(false);
            response.setText("Neko je obradio pre Vas");
        }
        return response;
    }
}
