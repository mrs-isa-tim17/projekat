package com.project.mrsisa.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.project.mrsisa.domain.OfferType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.project.mrsisa.domain.Client;
import com.project.mrsisa.domain.ExperienceReview;
import com.project.mrsisa.repository.ExperienceReviewRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExperienceReviewService {
	
	@Autowired
	private ExperienceReviewRepository experienceReviewRepository;
	@Autowired
	private ClientService clientService;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private Environment env;

	public double getReatingByOfferId(long id, OfferType offerType){
		return experienceReviewRepository.findOffersCurrentPriceById(id, offerType.getValue()).orElse(0.0);
	}
	
	public ExperienceReview save(ExperienceReview experienceReview) {
		return experienceReviewRepository.save(experienceReview);
	}
	
	public List<ExperienceReview> findAll(){
		return experienceReviewRepository.findAll();
	}
	
	
	public ExperienceReview findOneById(Long id) {
		return experienceReviewRepository.findOneById(id);
	}

	@Transactional
	public List<ExperienceReview> findAllApprovedByOfferId(Long id){
		List<ExperienceReview> exRevs = experienceReviewRepository.findAllByOfferId(id);
		for (ExperienceReview e: exRevs) {
			e.setClient(clientService.findOne(e.getClient().getId()));
		}
		return exRevs;

	}
	
	public List<ExperienceReview> findAllUnprocessedExperienceReview(){
		return experienceReviewRepository.findAllUnprocessedExperienceReview();
	}

	public ExperienceReview save(ExperienceReview experience, String ownerEmail, Client client) {
		try {
			sendMeesgeOwnerAboutApprovedExperienceReview(experience, ownerEmail, client);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return experienceReviewRepository.save(experience);
	}
	
    @Async
    private void sendMeesgeOwnerAboutApprovedExperienceReview(ExperienceReview experience, String ownerEmail, Client client) throws MessagingException, UnsupportedEncodingException {
        String subject = "Nova revizija za vašu ponudu";
        String senderName = "BookerTeam";
        String mailContent = "<p>Poštovani, administrator je odobrio reviziju klijenta "+client.getName() + " " + client.getSurname() +".</p> <br><br>" 
        		+ "<p> Ocena klijenta: \n\t" + experience.getRate()  +"</p>   <br><br>"
        		+ "<p> Revizija: \n\t" + experience.getText() + "</p>  <br><br>"
        		+ senderName;

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(mailContent, true);
        helper.setTo(ownerEmail);
        helper.setSubject(subject);
        helper.setFrom(env.getProperty("spring.mail.username"));
        javaMailSender.send(mimeMessage);
        
        System.out.println("Poslat mail");
    }

}
