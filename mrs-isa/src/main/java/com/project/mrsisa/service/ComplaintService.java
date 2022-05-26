package com.project.mrsisa.service;

import com.project.mrsisa.domain.Complaint;
import com.project.mrsisa.domain.Image;
import com.project.mrsisa.repository.ComplaintRepository;

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

import java.util.List;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private Environment env;

    public Complaint save(Complaint complaint){
        return complaintRepository.save(complaint);
    }

    public List<Complaint> findAllByOfferId(Long id){
        return complaintRepository.findAllByOfferId(id);
    }

    
    public List<Complaint>findUnprocessedComplaint(){
    	return complaintRepository.findUnprocessedComplaint();
    }
    
	public Complaint findOne(Long id) {
		return complaintRepository.findOneById(id);
	}
    
    public Complaint save(Complaint complaint, String clientEmail, String clientName, String clientSurname, String ownerEmail, String answer) {
    	try {
			sendMeesgeClientAboutAnswerToComplaint(complaint.getText(), answer, clientEmail );
		} catch (Exception e) {
			e.printStackTrace();
		}
    	try {
			sendMeesgeOwnerAboutAnswerToComplaint(complaint.getText(), answer, ownerEmail, clientName, clientSurname);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return complaintRepository.save(complaint);
		
    }

    
    @Async
    private void sendMeesgeClientAboutAnswerToComplaint(String complaintText, String answer, String clientMail) throws MessagingException, UnsupportedEncodingException {
        String subject = "Odgovor na vašu žalbu";
        String senderName = "BookerTeam";
        String mailContent = "<p>Poštovani, dobili ste odgovor na vašu žalbu.</p> <br><br>" 
        		+ "<p> Žalba klijenta: \n\t" + complaintText  +"</p>   <br><br>"
        		+ "<p> Odgovor administratora: \n\t" + answer + "</p>  <br><br>"
        		+ senderName;

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(mailContent, true);
        helper.setTo(clientMail);
        helper.setSubject(subject);
        helper.setFrom(env.getProperty("spring.mail.username"));
        javaMailSender.send(mimeMessage);
        
        System.out.println("Poslat mail");
    }
    
    
    @Async
    private void sendMeesgeOwnerAboutAnswerToComplaint(String complaintText, String answer, String ownerMail, String clientName, String clientSurname) throws MessagingException, UnsupportedEncodingException {
        String subject = "Odgovor na žalbu Vaše ponude";
        String senderName = "BookerTeam";
        String mailContent = "<p>Poštovani, administrator je odgovorio na žalbu klijenta "+clientName + " " + clientSurname +".</p> <br><br>" 
        		+ "<p> Vaša žalba: \n\t" + complaintText  +"</p>   <br><br>"
        		+ "<p> Odgovor administratora: \n\t" + answer + "</p>  <br><br>"
        		+ senderName;

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(mailContent, true);
        helper.setTo(ownerMail);
        helper.setSubject(subject);
        helper.setFrom(env.getProperty("spring.mail.username"));
        javaMailSender.send(mimeMessage);
        
        System.out.println("Poslat mail");
    }

}
