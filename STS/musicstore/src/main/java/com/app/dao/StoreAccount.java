package com.app.dao;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@AllArgsConstructor
public class StoreAccount implements UserDetails{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private static final long serialVersionUID = 1L;

	// details from RegistrationDetails
	private final String username;
	private final String password;	// encoded by RegistrationService
	private final String email; 	
	private String role;			// used by Spring Security to give authorization
	
	// an account can have a mailing, billing, etc. address
	@Embedded
	private Address address;
	
	public StoreAccount(String username, String email, String password, boolean isAdmin) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.address = new Address();
		this.role = isAdmin? "ROLE_ADMIN" : "ROLE_USER";
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(this.role));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
