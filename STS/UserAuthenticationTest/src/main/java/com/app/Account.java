package com.app;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/*
 * represents login credentials
 * a user can login using a username or an email 
 * 
 */
@Component
@Scope("prototype")
public class Account {
	private String name;
	private String email;
	private String pass;

	public Account() {
		super();
	}

	public Account(String name, String email, String pass) {
		super();
		this.name = name;
		this.email = email;
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
