package com.project.mrsisa.controller;

import com.project.mrsisa.domain.Admin;
import com.project.mrsisa.domain.User;
import com.project.mrsisa.dto.AdminChangePasswordDTO;
import com.project.mrsisa.dto.JwtAuthenticationRequest;
import com.project.mrsisa.dto.PasswordDTO;
import com.project.mrsisa.dto.UserRequest;
import com.project.mrsisa.dto.UserTokenState;
import com.project.mrsisa.exception.ResourceConflictException;
import com.project.mrsisa.service.UserService;
import com.project.mrsisa.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

//Kontroler zaduzen za autentifikaciju korisnika
@RestController
@RequestMapping(value = "/book/site", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserTokenState> createAuthenticationToken(
			@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {

		// if email and password not valid AuthenticationException
		// if you want to add aditional params you do it here
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		
			//handle if email already exists
			// valid credentials
			SecurityContextHolder.getContext().setAuthentication(authentication);
	
			// create token for user
			User user = (User) authentication.getPrincipal();
			String jwt = tokenUtils.generateToken(user.getUsername());
			int expiresIn = tokenUtils.getExpiredIn();
	
			//User u = userService.findByUsername(authenticationRequest.getUsername());
			// return created token
			UserTokenState uts =new UserTokenState(jwt, expiresIn, user.getRoleId(), user.getId());
			if(user.getRoleId()==2) {
				Admin a = (Admin)user;
				if(a.isInitLoginChanged()==false) {
					uts.setChangePassword(true);
				}
				else {
					uts.setChangePassword(false);
				}
			}else {
				uts.setChangePassword(false);
			}
			
			return ResponseEntity.ok(uts);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<User> addUser(@RequestBody UserRequest userRequest) {

		User existUser = this.userService.findByUsername(userRequest.getEmail());

		if (existUser != null) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		User user = this.userService.save(userRequest);

		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping("/password/matches/{id}")
	public ResponseEntity<Boolean> matchesOldPassword(@RequestBody PasswordDTO passwordDTO, @PathVariable Long id) {
		//encoder = new BCryptPasswordEncoder();
		User user = userService.findById(id);
		System.out.println(user.getPassword());
	    
		Boolean matches = encoder.matches(passwordDTO.getOld_password(),user.getPassword());
		Boolean success = true;
		
		if(matches) {
			user.setPassword(encoder.encode(passwordDTO.getNew_password()));
	    	Date date = new Date();
			user.setLastPasswordResetDate(new Timestamp(date.getTime()));
			if(user.getRoleId()==2) {
				Admin a = (Admin) user;
				a.setInitLoginChanged(true);
				User new_user = userService.save(a);
				System.out.println(new_user.getPassword() + new_user.getLastPasswordResetDate());

			}else {
				User new_user = userService.save(user);
				System.out.println(new_user.getPassword() + new_user.getLastPasswordResetDate());
				}
			}
		
		else {
			success = false;
		}
		return ResponseEntity.ok(success);
	}
	
	//public ResponseEntity<> adminFirstChangePassword(@RequestBody AdminChangePasswordDTO adminShangePasswordDTO){
		
		
	//}
	
}