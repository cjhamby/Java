package com.cjhamby.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjhamby.data.*;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public void makeDummyUsers() {
		// don't re-add users
		if(userRepository.count() > 0) {
			return;
		}
		userRepository.save(new MyUser("Charlie"));
		userRepository.save(new MyUser("Sabrina"));
		userRepository.save(new MyUser("Washington"));
		userRepository.save(new MyUser("Woop"));
		userRepository.save(new MyUser("RealPerson"));
		
	}
}
