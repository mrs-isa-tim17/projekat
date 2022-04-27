package com.project.mrsisa.controller;

import com.project.mrsisa.domain.User;
import com.project.mrsisa.dto.JwtAuthenticationRequest;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
	private UserService userService;
	
	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserTokenState> createAuthenticationToken(
			@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {

		// if email and password not valid AuthenticationException
		// if you want to add aditional params you do it here
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
		return ResponseEntity.ok(new UserTokenState(jwt, expiresIn, 1));
	}

	@PostMapping("/signup")
	public ResponseEntity<User> addUser(@RequestBody UserRequest userRequest, UriComponentsBuilder ucBuilder) {

		User existUser = this.userService.findByUsername(userRequest.getEmail());

		if (existUser != null) {
			throw new ResourceConflictException(userRequest.getEmail(), "Email already exists");
		}

		User user = this.userService.save(userRequest);

		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
}