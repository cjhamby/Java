package com.client.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.client.dao.MyTask;
import com.client.dao.MyTaskUser;

import lombok.extern.log4j.Log4j2;


/*
 * methods that use WebClient to communicate with the Server
 */
@Service
@Log4j2
public class WebClientService {
	
	// WebClient handles HTTP requests
	private static final WebClient webClient;
	
	static {
		log.info("CLIENT: Creating Web Client Service");
		webClient = WebClient.create("http://localhost:8090/");
	}
	
	public static void testConnection() {
		try {
			webClient.get()						// HTTP Get method
				.uri("/users/test")				// request address
				.retrieve()						// send request and get response
				.bodyToMono(MyTaskUser.class)	// read body as publisher
				.subscribe(System.out::println)	// print results
				.dispose();						// end publication subscription
		} catch (Exception e) {
			log.error("server connection test failed");
		}
	}
	
	//register a new user with the given params
	public static void registerUser(String name, String pass) {
		
		try {
			log.info("CLIENT: adding user " + name + ", " + pass);
			
			ArrayList<String> vals = new ArrayList<>();
			vals.add(name);
			vals.add(pass);
			webClient.post()					// start building an HTTP POST request
				.uri("/users")					// where the request is posting
				.bodyValue(vals)		
				.retrieve()						// submit and get response
				//.toEntity(MyTaskUser.class)		// the response body should be an echo
				//.subscribe(System.out::println)	// check the echo
				;
		} catch (Exception e) {
			log.error("CLIENT: could not register user (WebClientService)");
		}
	}
	
	public static MyTaskUser loginUser(String name, String pass) {
		
		try {
			log.info("CLIENT: login attempt " + name + ", " + pass);
			
			ArrayList<String> vals = new ArrayList<>();
			vals.add(name);
			vals.add(pass);
			MyTaskUser user = webClient.post()
				.uri("/login")				// where request is made
				.bodyValue(vals)			// values sent with request
				.retrieve()					// submit request, get response
				.toEntity(MyTaskUser.class)
				.block()
				.getBody();					
			
			return user;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static MyTaskUser addTaskToUser(MyTaskUser user, MyTask task) {
		
		try {
			log.info("adding new task to user...");
			MyTaskUser userAfterAdd = webClient.post()
					.uri("/users/" + user.getUserId() + "/tasks")
					.bodyValue(task)
					.retrieve()
					.toEntity(MyTaskUser.class)
					.block()
					.getBody();
			return userAfterAdd;
			
		} catch (Exception e) {
			return user;
		}		
	}
}
