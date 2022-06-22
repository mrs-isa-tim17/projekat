package com.project.mrsisa.service;

import com.project.mrsisa.domain.ReservationReport;
import com.project.mrsisa.dto.ReservationReportAdminDTO;
import com.project.mrsisa.repository.ReservationReportRepository;

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
public class ReservationReportService {
    @Autowired
    ReservationReportRepository reservationReportRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private Environment env;

    public ReservationReport save(ReservationReport rr){
        return reservationReportRepository.save(rr);
    }



    public ReservationReport haveReservationReport(Long id){
        return reservationReportRepository.haveReservationReport(id);
    }

    
    public List<ReservationReport> findUnprocessedReport(){
    	return reservationReportRepository.findUnprocessedReport();
    }
    
    public ReservationReport findOneById(Long id) {
    	return reservationReportRepository.findOneById(id); 
    }

    @Transactional(readOnly = false)
	public ReservationReport saveAccept(ReservationReport reservationReport) {	
		return reservationReportRepository.save(reservationReport);
		
	}
    
    public void sendAcceptMails(ReservationReportAdminDTO dto) {
    	try {
	//		sendMeesgeClientAboutAcceptReport(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
//			sendMeesgeOwnerAboutAcceptReport(dto);

		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
	
	@Async
	private void sendMeesgeOwnerAboutAcceptReport(ReservationReportAdminDTO dto) throws MessagingException {
        String subject = "Odgovor na izvestaj o rezervaciji";
        String senderName = "BookerTeam";
        String mailContent = "<p>Poštovani, administrator je odobrio vaš izveštaj o rezervaciji za klijenta "+ dto.getClientName() + " " + dto.getClientSurname() +" koji je kod Vas boravio u periodu od " + dto.getStartDate() + " do " + dto.getEndDate() +".</p> <br><br>" ;
        	if(dto.getPenalty()) {
        		mailContent += " <p> Klijent je kažnjen sa jednim penalom.</p>";
        	}
        	mailContent += " <br><br>" + "Tekst izveštaja: " + dto.getReportText();
        	mailContent += " <br><br>"+ senderName;

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(mailContent, true);
        helper.setTo(dto.getOwnerEmail());
        helper.setSubject(subject);
        helper.setFrom(env.getProperty("spring.mail.username"));
        //javaMailSender.send(mimeMessage);
        
        System.out.println("Poslat mail");
		
	}
	
	@Async
	private void sendMeesgeClientAboutAcceptReport(ReservationReportAdminDTO dto) throws MessagingException {
		String subject = "Odgovor na izvestaj o rezervaciji";
        String senderName = "BookerTeam";
        String mailContent = "<p>Poštovani, administrator je odobrio izveštaj vlasnika " + dto.getOwnerName() + " " + dto.getOwnerSurname() + " kod kog ste boravili u periodu od " + dto.getStartDate() + " do " + dto.getEndDate() +".</p> <br><br>" ;
        	if(dto.getPenalty()) {
        		mailContent += " <p> Kažnjeni ste sa jednim penalom.</p>";
        	}
        	mailContent += " <br><br>" + "Tekst izveštaja: " + dto.getReportText();
        	mailContent += " <br><br>"+ senderName;

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(mailContent, true);
        helper.setTo(dto.getClientEmail());
        helper.setSubject(subject);
        helper.setFrom(env.getProperty("spring.mail.username"));
        //javaMailSender.send(mimeMessage);
        
        System.out.println("Poslat mail");
		

	}

	@Transactional(readOnly = false)
	public ReservationReport saveReject(ReservationReport reservationReport ) {
		return reservationReportRepository.save(reservationReport);
	}
	
	public void sendRejectmail(ReservationReportAdminDTO dto){
		try {
		//	sendMeesgeClientAboutRejectReport(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
		//	sendMeesgeOwnerAboutRejectReport(dto);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Async
	private void sendMeesgeOwnerAboutRejectReport(ReservationReportAdminDTO dto) throws MessagingException {
        String subject = "Odgovor na izvestaj o rezervaciji";
        String senderName = "BookerTeam";
        String mailContent = "<p>Poštovani, administrator je odbio vaš izveštaj o rezervaciji za klijenta "+ dto.getClientName() + " " + dto.getClientSurname() +" koji je kod Vas boravio u periodu od " + dto.getStartDate() + " do " + dto.getEndDate() +".</p> <br><br>" ;
        mailContent += " <br><br>" + "Tekst izveštaja: " + dto.getReportText();
        	mailContent += " <br><br>"+ senderName;

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(mailContent, true);
        helper.setTo(dto.getOwnerEmail());
        helper.setSubject(subject);
        helper.setFrom(env.getProperty("spring.mail.username"));
        //javaMailSender.send(mimeMessage);
        
        System.out.println("Poslat mail");
		
	}
	
	@Async
	private void sendMeesgeClientAboutRejectReport(ReservationReportAdminDTO dto) throws MessagingException {
		String subject = "Odgovor na izvestaj o rezervaciji";
        String senderName = "BookerTeam";
        String mailContent = "<p>Poštovani, administrator je odbio izveštaj vlasnika " + dto.getOwnerName() + " " + dto.getOwnerSurname()+ " kod kog ste boravili u periodu od " + dto.getStartDate() + " do " + dto.getEndDate() +".</p> <br><br>" ;
        mailContent += " <br><br>" + "Tekst izveštaja: " + dto.getReportText();	
        mailContent += " <br><br>"+ senderName;

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(mailContent, true);
        helper.setTo(dto.getClientEmail());
        helper.setSubject(subject);
        helper.setFrom(env.getProperty("spring.mail.username"));
        //javaMailSender.send(mimeMessage);
        
        System.out.println("Poslat mail");
	
	}

}
