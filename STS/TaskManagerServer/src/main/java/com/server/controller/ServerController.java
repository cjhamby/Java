package com.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.dao.MyTask;
import com.server.dao.MyTaskUser;
import com.server.exception.InvalidLoginException;
import com.server.exception.UserNotFoundException;
import com.server.service.ServerService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class ServerController {

	@Autowired
	private ServerService serverService;
	
	
	@GetMapping("/users")
	public Iterable<MyTaskUser> getAllUsers() {		// return a list of all users
		log.info("SERVER: getting all users");
		return serverService.getAllUsers();
	}
	
	
	// post a new user to the repository
	// id is not specified
	@PostMapping("/users")
	public MyTaskUser postUserAtId(	@RequestBody List<String> vals) {
		try {
			log.info("SERVER: posting user: " + vals.get(0) + ", " + vals.get(1));
			return serverService.addNewUser(vals.get(0), vals.get(1));
			
		} catch (Exception e) {
			log.error("SERVER: could not post user");
			return null;
		}
	}

	// return a test user
	@GetMapping("users/test")
	public MyTaskUser getNewUser() {
		return new MyTaskUser("chros", "hambug");
	}
	
	@GetMapping("users/testAdd")
	public MyTaskUser testAdd() {
		return serverService.addNewUser("cool", "bean");
	}
	
	@GetMapping("users/{id}")
	public MyTaskUser getUserById(@PathVariable("id") String id) {
		log.info("SERVER: getting user by id");
		try {
			return serverService.getUserById(id);
		} catch (UserNotFoundException e) {
			log.warn("SERVER: user not found");
			return null;
		}
	}
	
	@PutMapping("users/{id}")
	public void addTaskToUser(@PathVariable("id") String id, @RequestBody MyTask task) {
		log.info("SERVER: adding task to user");
		try {
			serverService.addTaskToUser(id, task);
		} catch (UserNotFoundException e) {
			log.warn("SERVER: user not found");
		}
	}

	@DeleteMapping("users/{id}")
	public void removeUserById(@PathVariable("id") String id) {
		log.info("SERVER: getting user by id");
		try {
			serverService.removeUserById(id);
		} catch (UserNotFoundException e) {
			log.warn("SERVER: user not found");
		}
	}
	
	@PostMapping("/login")
	public MyTaskUser loginUserAccount(@RequestBody List<String> vals ) {
		log.info("SERVER: attempting user login");
		try {
			MyTaskUser user = serverService.login(vals.get(0), vals.get(1));
			return user;
		} catch (UserNotFoundException e) {
			log.warn("SERVER: user not found");
			return null;
		} catch (InvalidLoginException e) {
			log.warn("SERVER: invalid password");
			return null;
		}
	}

}
