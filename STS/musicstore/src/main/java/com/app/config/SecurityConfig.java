package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.service.StoreAccountService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public UserDetailsService userDetailsService() {
		return new StoreAccountService();
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
		
	@Override
	protected void configure(AuthenticationManagerBuilder auth) 
			throws Exception {
				
		auth
			.userDetailsService(userDetailsService())
			.passwordEncoder(encoder())
		;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
			.antMatchers("/admin/**").hasAnyRole("ADMIN")
			.antMatchers("/user/**").hasAnyRole("USER","ADMIN")
			.antMatchers("/**").permitAll()	// change later
			.and()
		.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/user/home")
			.successForwardUrl("/user/home")
			.and()
		.logout()
			.permitAll()
			.and()
		.exceptionHandling()
			.accessDeniedPage("/no")
			.and()
		.httpBasic();
	}
}