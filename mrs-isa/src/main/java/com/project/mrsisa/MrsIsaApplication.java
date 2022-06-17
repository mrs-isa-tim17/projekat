package com.project.mrsisa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class MrsIsaApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MrsIsaApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(MrsIsaApplication.class);
	}

}
