package com.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.app.service.RegistrationService;

@Configuration
public class AppInitializer implements CommandLineRunner {

	@Autowired
	private RegistrationService registrationService;
	
	@Override
	public void run(String... args) throws Exception {
		registrationService.preloadUsers();
	}

}
