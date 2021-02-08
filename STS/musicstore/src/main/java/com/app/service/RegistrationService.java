package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dao.Address;
import com.app.dao.RegistrationDetails;
import com.app.dao.StoreAccount;
import com.app.data.AccountRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class RegistrationService {
	
	@Autowired
	private AccountRepository accountRepo;
	
	/*
	 * encodes passwords before storing them in the database
	 * storing plaintext passwords is a bad idea
	 */
	public PasswordEncoder encoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	/*
	 * creates sample users
	 * definitely not a secure production practice :-)
	 */
	public void preloadUsers() {
		log.info("Registering default users");
		registerAdmin(new RegistrationDetails("admin", "admin@admin.com", "admin"));
		registerUser(new RegistrationDetails("user", "user@user.com", "user"));
		
		Address temp = new Address();
		temp.setCity("cityville");
		temp.setFullname("Adam Adamson");
		temp.setPhone("123-345-5678");
		temp.setState("ST");
		temp.setStreet("123 street st");
		temp.setZip("12345");
		addAddressToUser("admin", temp);
		
		temp.setFullname("Brianne Briannity");
		addAddressToUser("user", temp);
	}
	
	
	public void addAddressToUser(String username, Address address) {
		StoreAccount user = accountRepo.findByUsername(username);
		user.setAddress(address);
		accountRepo.save(user); 
	}
	
	/*
	 * ordinarily, the controller will verify the registration details are valid
	 * this method attempts to:
	 * - encode the password
	 * - save the new account to the repo 
	 */
	public boolean registerUser(RegistrationDetails details) {
		try {
			accountRepo.save( new StoreAccount(
					details.getUsername(),
					details.getEmail(), 
					encoder().encode(details.getPassword()),
					false
			));
			return true;
		} catch (Exception e) {	// should only be thrown if new account is null
			return false;
		}
	}
	
	public boolean registerAdmin(RegistrationDetails details) {
		try {
			accountRepo.save( new StoreAccount(
					details.getUsername(),
					details.getEmail(), 
					encoder().encode(details.getPassword()),
					true
			));
			return true;
		} catch (Exception e) {	// should only be thrown if new account is null
			return false;
		}
	}
}