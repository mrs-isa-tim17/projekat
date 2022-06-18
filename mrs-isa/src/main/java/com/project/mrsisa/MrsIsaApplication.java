package com.project.mrsisa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;


@EnableCaching
@SpringBootApplication
public class MrsIsaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MrsIsaApplication.class, args);
	}

}
