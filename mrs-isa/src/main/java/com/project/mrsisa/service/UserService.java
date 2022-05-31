package com.project.mrsisa.service;

import com.project.mrsisa.domain.*;
import org.springframework.core.env.Environment;
import com.project.mrsisa.dto.UserRequest;
import com.project.mrsisa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import net.bytebuddy.utility.RandomString;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

@Service
public class UserService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private Environment env;

    public User findByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmailEquals(email);
    }

    public User findById(Long id) throws AccessDeniedException {
        return userRepository.findById(id).orElseGet(null);
    }

    @Async
    private void sendVerificationMail(Client c, String siteUrl) throws MessagingException, UnsupportedEncodingException {
        String randomCode = RandomString.make(64);
        c.setVerificationCode(randomCode);
        String subject = "Molimo Vas potvrdite vašu email adresu";
        String senderName = "BookerTeam";
        String mailContent = "<p>Molimo vas, klikom na link potvrdite validnost vaše imejl adrese" +
                ", koji ste dali prilikom registracije na Book sajt. U slučaju ako niste vi registrovali molimo Vas" +
                "zanemarite ovaj imejl.</p>";
        String verifyURL = "http://localhost:8081/client/verify/" + c.getVerificationCode();
        mailContent += "<a style='font-size: 30px;' href='" + verifyURL + "'> Verifikujte nalog  </a>";

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(mailContent, true);
        helper.setTo(c.getEmail());
        helper.setSubject(subject);
        helper.setFrom(env.getProperty("spring.mail.username"));
        javaMailSender.send(mimeMessage);

    }

    public List<User> findAll() throws AccessDeniedException {
        return userRepository.findAll();
    }

    public List<User> findAllActive(){
    	return userRepository.findAllActive();
    }
    
    public User save(UserRequest userRequest) {
        int userId = userRequest.getUserRole().intValue();
        switch (userId) {
            case 1:
                Client c = new Client();
                c = (Client) fillUserAttributes(c, userRequest);
                c.setPenaltyNumber(0);
                c.setEnabled(false);
                try {
                    sendVerificationMail(c, "");
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;//better option??
                }
                return userRepository.save(c);
            case 2:
                Admin a = new Admin();
                a = (Admin) fillUserAttributes(a, userRequest);
                a.setInitLoginChanged(false);
                return userRepository.save(a);
            case 3:
                CottageOwner co = new CottageOwner();
                co = (CottageOwner) fillUserAttributes(co, userRequest);
                RegistrationRequest rrCO = makeRegistrationRequest(co);
                co.setRegistrationRequest(rrCO);
                return userRepository.save(co);
            case 4:
                ShipOwner so = new ShipOwner();
                so = (ShipOwner) fillUserAttributes(so, userRequest);
                RegistrationRequest rrSO = makeRegistrationRequest(so);
                so.setRegistrationRequest(rrSO);
                return userRepository.save(so);
            case 5:
                FishingInstructor fi = new FishingInstructor();
                fi = (FishingInstructor) fillUserAttributes(fi, userRequest);
                RegistrationRequest rrFI = makeRegistrationRequest(fi);
                fi.setRegistrationRequest(rrFI);
                return userRepository.save(fi);
        }
        return null;
    }

    private RegistrationRequest makeRegistrationRequest(User u) {
        RegistrationRequest rr = new RegistrationRequest();
        rr.setUserRef(u);
        rr.setRegistrationType(RegistrationType.valueOf(u.getRoleId().intValue()));
        rr.setStatus(ProcessingStatus.UNPROCESSED);
        return  rr;
    }

    private User fillUserAttributes(User u, UserRequest userRequest) {
        u.setUsername(userRequest.getEmail());
        u.setName(userRequest.getName());
        u.setSurname(userRequest.getSurname());
        u.setAddress(new Address(userRequest.getLongitude(), userRequest.getLatitude()));
        u.setUserType(UserType.REGULAR);
        //before saving password we are hashing it
        u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        u.setRoleId(userRequest.getUserRole());
        u.setPhoneNumber(userRequest.getPhoneNumber());
        return u;
    }
    
    public User save(User user) {
    	return userRepository.save(user);
    }
    
	public void remove(Long id) {
		userRepository.deleteById(id);
	}

	public List<User> findAllDeleted() {
		return userRepository.findAllDeleted();
	}
   
    }


