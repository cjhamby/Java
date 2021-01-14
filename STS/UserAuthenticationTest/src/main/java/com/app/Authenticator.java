package com.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/*
 * given some credentials
 * the authenticator attempts to log into an account
 * 
 * as a login authenticator, this does not prevent invalid account creation
 */
@Service
public class Authenticator {
	
	// mock users
	private static List<Account> accounts;
	
	/*
	 * conditions to be met by account creation (i.e. elsewhere)
	 * - no two usernames or emails can be the same
	 * - ensure a valid email
	 */
	static {
		accounts = new ArrayList<>();
		accounts.add(new Account("chris", "chris@hamby.com", "pass123!"));
		accounts.add(new Account("amar", "amar@jeet.com", "pass456!"));
	}
	
	
	public static boolean login(String name, String pass) {
		for(Account account: accounts) {
			if(account.getName().equals(name) || account.getEmail().equals(name)) {
				if(account.getPass().equals(pass)) {
					System.out.println("login success");
					return true;
				} else {
					System.out.println("incorrect password");
					return false;
				}
			}
		}
		System.out.println("account " + name + " does not exist");
		return false;
	}
}
