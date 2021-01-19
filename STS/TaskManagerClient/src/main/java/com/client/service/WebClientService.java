package com.client.service;

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
		log.info("Creating Web Client Service");
		webClient = WebClient.create("http://localhost:8090/");
	}
	
	/*
	 * Test the ability to connect to SERVER
	 */
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
	
	/*
	 * Register a new account
	 * send a POST request to SERVER/users
	 * with the user credentials
	 */
	public static void registerUser(String name, String pass) {
		try {
			log.info("adding user " + name + ", " + pass);
			String[] cred = {name, pass};		// body of request
			webClient.post()					// start building an HTTP POST request
				.uri("/users")					// where the request is posting
				.bodyValue(cred)				// what the request is sending
				.retrieve()						// submit and get response
				.toEntity(MyTaskUser.class)		// the response body should be an echo
				.subscribe(System.out::println)	// check the echo
			;
		} catch (Exception e) {
			log.error("could not register user (WebClientService)");
		}
	}
	
	/*
	 * Log into an account
	 * send a POST request to SERVER/login
	 * with the user credentials
	 */
	public static MyTaskUser loginUser(String name, String pass) {
		try {
			log.info("login attempt " + name + ", " + pass);
			String[] cred = {name, pass};		// body of request
			MyTaskUser user = webClient.post()	// begin creating request
				.uri("/login")					// where request is made
				.bodyValue(cred)				// values sent with request
				.retrieve()						// submit request, get response
				.toEntity(MyTaskUser.class)		// expected return type
				.block()						// block until user is received
				.getBody();						// get request body (i.e. the user)
			
			return user;
		} catch (Exception e) {
			log.error("could not login user");
			return null;
		}
	}
	
	/*
	 * add a task to the session's current user 
	 * send a POST request to SERVER/users/ID/tasks
	 * the request body contains the task object
	 * 
	 * return the updated user (which has the task added)
	 */
	public static MyTaskUser addTaskToUser(MyTaskUser user, MyTask task) {
		try {
			log.info("adding new task to user...");
			return webClient.post()
					.uri("/users/" + user.getUserId() + "/tasks")
					.bodyValue(task)
					.retrieve()
					.toEntity(MyTaskUser.class)
					.block()
					.getBody();
			
		} catch (Exception e) {
			log.error("could not add task to user");
			return user;
		}		
	}
	
	/*
	 * update a task of the session's current user
	 * send a PUT request to SERVER/users/ID/tasks
	 * the request body contains the updated task
	 * 
	 * the task's ID should not change, 
	 * but it doesn't really affect anything
	 * 
	 * return the updated user (with the task updated)
	 */
	public static MyTaskUser updateTask(MyTaskUser user, MyTask task) {
		try {
			log.info("updating task for user...");
			return webClient.put()
					.uri("/users/" + user.getUserId()+ "/tasks")
					.bodyValue(task)
					.retrieve()
					.toEntity(MyTaskUser.class)
					.block()
					.getBody();
			
		} catch (Exception e) {
			log.error("could not update task for user");
			return user;
		}
	}
	
	
	/*
	 * remove a task from the session's current user
	 * send a DELETE request to SERVER/users/USERID/tasks/TASKID
	 * there is no request body
	 * 
	 * return the updated user (with the task removed)
	 */
	public static MyTaskUser removeTaskFromUser(MyTaskUser user, MyTask task) {
		try {
			log.info("removing task from user...");
			return webClient.delete()
					.uri("/users/" + user.getUserId()+ "/tasks/" + task.getTaskId())
					.retrieve()
					.toEntity(MyTaskUser.class)
					.block()
					.getBody();
		} catch (Exception e) {
			log.error("could not remove task from user");
			return user;
		}
	}
}
