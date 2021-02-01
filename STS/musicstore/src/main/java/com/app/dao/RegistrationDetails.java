package com.app.dao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/*
 * these details used to create a store account
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDetails {
	
	@NotNull(message="Please Add A Username")
	@Size(min=4, message="Username must be at least 4 characters")
	private String username;
	
	@NotNull(message="Please Add An Email")
	@Size(min=1, message="Please Add A Valid Email")
	@Email(message="Please Add A Valid Email")
	private String email;
	
	@NotNull(message="Please Add A Password")
	@Size(min=4, message="Password must be at least 4 characters")
	private String password;
}
