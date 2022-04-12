package com.project.mrsisa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.project.mrsisa.controller.AdventureController;
import com.project.mrsisa.domain.Address;
import com.project.mrsisa.domain.Place;
import com.project.mrsisa.dto.AdventureDTO;
import com.project.mrsisa.repository.AdventureRepository;
import com.project.mrsisa.service.AdventureService;

@SpringBootApplication
public class MrsIsaApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MrsIsaApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(MrsIsaApplication.class);
	}
}
