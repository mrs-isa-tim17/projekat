package com.project.mrsisa.service;

import com.project.mrsisa.domain.*;
import com.project.mrsisa.dto.UserRequest;
import com.project.mrsisa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    public User findByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmailEquals(email);
    }

    public User findById(Long id) throws AccessDeniedException {
        return userRepository.findById(id).orElseGet(null);
    }

    public List<User> findAll() throws AccessDeniedException {
        return userRepository.findAll();
    }

    public User save(UserRequest userRequest) {
        int userId = userRequest.getUserRole().intValue();
        switch (userId) {
            case 1:
                Client c = new Client();
                c = (Client) fillUserAttributes(c, userRequest);
                c.setPenaltyNumber(0);
                c.setAuthenticated(false);
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


}
