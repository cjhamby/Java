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
import com.server.exception.TaskNotFoundException;
import com.server.exception.UserNotFoundException;
import com.server.service.ServerService;
import com.server.service.TaskService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class ServerController {

	@Autowired
	private ServerService serverService;

	@Autowired
	private TaskService taskService;
	
// 	return a list of all users
	@GetMapping("/users")
	public Iterable<MyTaskUser> getAllUsers() {	
		log.info("SERVER: getting all users");
		return serverService.getAllUsers();
	}
	
//	post a new user to the repository
//	id is not specified
	@PostMapping("/users")
	public MyTaskUser addNewUser( @RequestBody String[] vals) {
		try {
			log.info("Server is creating new user");
			String userName = vals[0];
			String userPass = vals[1];
			log.info("SERVER: posting user: " + userName + ", " + userPass);
			return serverService.addNewUser(userName, userPass);
			
		} catch (Exception e) {
			log.error("SERVER: could not post user");
			return null;
		}
	}
	
//	return the user if found,
//	or throw exception otherwise
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
	
//	return a list of tasks associated with the user
	@GetMapping("users/{id}/tasks")
	public List<MyTask> getUserTasks(@PathVariable("id") String id) {
		log.info("SERVER: getting user tasks");
		try {
			return taskService.getUserTasks(id);
		} catch (UserNotFoundException e) {
			log.warn("SERVER: user not found");
			return null;
		}
	}
	
	@PostMapping("users/{id}/tasks")
	public MyTaskUser addTaskToUser(@PathVariable("id") String id, @RequestBody MyTask task) {
		log.info("SERVER: adding task to user");
		try {
			return taskService.addTaskToUser(id, task);
		} catch (Exception e) {
			log.warn("SERVER: user not found");
			return null;
		}
	}

	@PutMapping("users/{id}/tasks")
	public MyTaskUser modifyTaskController(@PathVariable("id") String id,
									 @RequestBody MyTask task) {
		log.info("updating task");
		try {
			return taskService.modifyTask(id, task);
		} catch (Exception e) {
			log.warn("SERVER: user not found");
			return null;
		}
	}
	
	@DeleteMapping("users/{id}/tasks/{taskId}")
	public MyTaskUser removeTaskController(@PathVariable("id") String id,
									@PathVariable("taskId") String taskId) {
		log.info("removing task");
		try {
			return taskService.removeTaskFromUser(id, taskId);
		} catch (UserNotFoundException e) {
			log.warn("SERVER: user not found");
			return null;
		} catch (TaskNotFoundException e) {
			log.warn("SERVER: task not found");
			return null;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	@DeleteMapping("users/{id}")
	public void removeUserController(@PathVariable("id") String id) {
		log.info("SERVER: getting user by id");
		try {
			serverService.removeUser(id);
		} catch (UserNotFoundException e) {
			log.warn("SERVER: user not found");
		} 
	}
	
	@PostMapping("/login")
	public MyTaskUser loginUserController(@RequestBody List<String> vals ) {
		log.info("SERVER: attempting user login");
		try {
			String userName = vals.get(0);
			String userPass = vals.get(1);
			return serverService.login(userName, userPass);
		} catch (UserNotFoundException e) {
			log.warn("SERVER: user not found");
			return null;
		} catch (InvalidLoginException e) {
			log.warn("SERVER: invalid password");
			return null;
		}
	}

}
