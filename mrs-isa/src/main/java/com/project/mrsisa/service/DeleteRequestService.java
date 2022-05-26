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

@Service
public class DeleteRequestService {

    @Autowired
    private DeleteRequestRepository deleteRequestRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private Environment env;

    public DeleteRequest save(DeleteRequest deleteRequest){
        return deleteRequestRepository.save(deleteRequest);
    }

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
    
    public DeleteRequest save(DeleteRequest deleteRequest, String mail, String text, boolean accepted) {
    	try {
    		sendMeesgeAboutAcceptRejectDeleteRequest(mail, text, accepted);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return deleteRequestRepository.save(deleteRequest);
    	
    }
    
    @Async
    private void sendMeesgeAboutAcceptRejectDeleteRequest(String mail, String text, boolean accepted) throws MessagingException, UnsupportedEncodingException {
        String acceptedRejected = "";
    	if (accepted){
    		acceptedRejected = "prihvaćen";
        }
    	else {
    		acceptedRejected = "odbijen";
    	}
    	
    	String subject = "Odgovor na zahtev za brisanje naloga";
        String senderName = "BookerTeam";
        String mailContent = "<p>Poštovani, "+ acceptedRejected +" je vaš zahtev za brisanje naloga na Book sajtu.\n</p>" + " <p>Obrazloženje:\n</p>"
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
