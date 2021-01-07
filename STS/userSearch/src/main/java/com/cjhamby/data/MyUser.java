package com.cjhamby.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MyUser {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userID;
	private String name;

	public MyUser() {
	}

	public MyUser(String name) {
		//this.userID = userID;
		this.name = name;
	};

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
