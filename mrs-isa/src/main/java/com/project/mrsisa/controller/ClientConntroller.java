package com.project.mrsisa.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.mrsisa.domain.UserType;
import com.project.mrsisa.dto.ClientDTO;

@RestController
@RequestMapping("/client")
public class ClientConntroller {
	
	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping("/profile")
    public ResponseEntity<ClientDTO> getClient(){
        System.out.println("IN CONTROLLER");
        ClientDTO clientDTO = new ClientDTO("Amanda", "Milanovic", "example@gmail.com", "",
                "5588", UserType.REGULAR, 20, 3, "Za sad nista ali radite dalje",
                "Novi Sad", "Serbia", "Jovan Jobanovic Zmaj 11");

        return new ResponseEntity<ClientDTO>(clientDTO, HttpStatus.OK);
    }
}
