package com.project.mrsisa.service;

import com.project.mrsisa.domain.DeleteRequest;
import com.project.mrsisa.repository.DeleteRequestRepository;

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
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteRequestService {

    @Autowired
    private DeleteRequestRepository deleteRequestRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private Environment env;

    public boolean getIfUserMadeDeleteRequest(long id){
        DeleteRequest dr = deleteRequestRepository.findByUserRef(id);
        if (dr == null)
            return false;
        return true;
    }
    
    public List<DeleteRequest> getUnprocessedDeleteRequest() {
    	return deleteRequestRepository.findUnprocessedDeleteRequest();
    }
    
    public DeleteRequest findOneById(Long id) {
    	return deleteRequestRepository.findOneById(id);
    }
    
    @Transactional(readOnly = false)
    public DeleteRequest save(DeleteRequest deleteRequest) {  	
    	return deleteRequestRepository.save(deleteRequest);
    }
    
    public void sendMailsAboutDeleteRequest(String mail, String text, boolean accepted) {
    	try {
    	//	sendMeesgeAboutAcceptRejectDeleteRequest(mail, text, accepted);
		} catch (Exception e) {
			e.printStackTrace();
		}	
    }
    
    @Async
    private void sendMeesgeAboutAcceptRejectDeleteRequest(String mail, String text, boolean accepted) throws MessagingException, UnsupportedEncodingException {
        String acceptedRejected = "";
    	if (accepted){
    		acceptedRejected = "prihva??en";
        }
    	else {
    		acceptedRejected = "odbijen";
    	}
    	
    	String subject = "Odgovor na zahtev za brisanje naloga";
        String senderName = "BookerTeam";
        String mailContent = "<p>Po??tovani, "+ acceptedRejected +" je va?? zahtev za brisanje naloga na Book sajtu.\n</p>" + " <p>Obrazlo??enje:\n</p>"
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

    public DeleteRequest findOneByUser(Long id) {
        return deleteRequestRepository.findByUserRef(id);
    }
}
