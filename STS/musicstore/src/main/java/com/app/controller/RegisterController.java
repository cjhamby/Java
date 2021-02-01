package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.dao.RegistrationDetails;
import com.app.service.RegistrationService;


/*
 * responsible for delegating requests relating to user registration
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
	
	
	// the service is where registration details 
	// are converted to complete store accounts
	@Autowired
	private RegistrationService registrationService;
	
	
	@GetMapping
	public String goToRegisterPage(RegistrationDetails newAccount) {
		return "register";
	}
	
	@PostMapping
	private String registerAccount(
			@Valid @ModelAttribute RegistrationDetails storeAccount,
			BindingResult bindingResult
		) {
		
		if(bindingResult.hasErrors()) {
			
			return "register";
		}

		if(registrationService.registerUser(storeAccount)) {
			return "redirect:/";
		} else {
			return "register";	// TODO make an error page
		}
	}
	
	
}
