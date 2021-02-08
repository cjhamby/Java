package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.dao.StoreAccount;
import com.app.data.AccountRepository;


@Service
public class StoreAccountService implements UserDetailsService{
	
	@Autowired
	private AccountRepository accountRepo;
	
	/*
	 * MUST NEVER RETURN NULL
	 */
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		UserDetails user = accountRepo.findByUsername(username);
		
		// MUST NEVER RETURN NULL
		if(user != null) {
			return user;
		}
		
		// MUST NEVER RETURN NULL
		throw new UsernameNotFoundException("user " + username + " not found");
	}
	
	public List<StoreAccount> getAllAccounts() {
		List<StoreAccount> accounts = new ArrayList<>();
		
		accountRepo.findAll().forEach(account -> accounts.add(account));
		
		return accounts;
	}
}
