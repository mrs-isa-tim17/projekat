package com.project.mrsisa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mrsisa.domain.Adventure;
import com.project.mrsisa.domain.Client;
import com.project.mrsisa.domain.Role;
import com.project.mrsisa.domain.User;
import com.project.mrsisa.dto.AdminUserDTO;
import com.project.mrsisa.dto.AdventureDTO;
import com.project.mrsisa.service.RoleService;
import com.project.mrsisa.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	
	
	@GetMapping(value = "/all")
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<AdminUserDTO>> getAllUsers() {
		System.out.println("IN CONTROLLER");

		List<User> users = userService.findAllActive();
		System.out.println("ACTIVEEEE : " + users.size());
		
		List<AdminUserDTO> userDTO = new ArrayList<AdminUserDTO>();
		for (User user : users) {
			Role role = roleService.findOneById(user.getRoleId());
			System.out.println(role.getName());
			 AdminUserDTO newUser = new AdminUserDTO(user.getId() ,user.getName(), user.getSurname(), user.getPhoneNumber(), user.getEmail(), role.getName(), user.getLoyaltyPoints(),
					user.getUserType().toString(), 0, user.isDeleted());
			 if(role.getName()=="ROLE_CLIENT") {
				 Client client = (Client) user;
				 newUser.setPenalties(client.getPenaltyNumber());
			 }
			 userDTO.add(newUser);
			 
		}
		return new ResponseEntity<List<AdminUserDTO>>(userDTO, HttpStatus.OK);

	}
	
	@GetMapping(value = "/all/deleted")
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<AdminUserDTO>> getDeletedUsers() {
		System.out.println("IN CONTROLLER");

		List<User> users = userService.findAllDeleted();
		System.out.println("ACTIVEEEE : " + users.size());
		
		List<AdminUserDTO> userDTO = new ArrayList<AdminUserDTO>();
		for (User user : users) {
			Role role = roleService.findOneById(user.getRoleId());
			System.out.println(role.getName());
			 AdminUserDTO newUser = new AdminUserDTO(user.getId() ,user.getName(), user.getSurname(), user.getPhoneNumber(), user.getEmail(), role.getName(), user.getLoyaltyPoints(),
					user.getUserType().toString(), 0, user.isDeleted());
			 if(role.getName()=="ROLE_CLIENT") {
				 Client client = (Client) user;
				 newUser.setPenalties(client.getPenaltyNumber());
			 }
			 userDTO.add(newUser);
			 
		}
		return new ResponseEntity<List<AdminUserDTO>>(userDTO, HttpStatus.OK);
	}
	
	
	
	
	@GetMapping(value="/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Boolean> deleteUser(@PathVariable Long id){
		User user = userService.findById(id);
		if (user != null) {
			user.setDeleted(true);
			userService.save(user);				// logičko brisanje
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}
	}


}
