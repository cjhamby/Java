package com.cjhamby.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/*
 * annotations to mark this as the main entry point of the application
 * and to bring components in from other packages
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.cjhamby.*"} )
@EntityScan("com.cjhamby.data")
@EnableJpaRepositories("com.cjhamby.data")
public class UserSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserSearchApplication.class, args);
	}

}
