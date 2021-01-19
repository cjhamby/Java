package com.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.dao.MyTask;
import com.server.dao.MyTaskUser;
import com.server.exception.InvalidLoginException;
import com.server.exception.UserNotFoundException;
import com.server.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ServerService {

	@Autowired
	private UserRepository repository;
	
	// add a new user to the repo
	public MyTaskUser addNewUser(String name, String pass) {
		MyTaskUser newUser = new MyTaskUser(name, pass);
		System.out.println(newUser);
		repository.save(newUser);
		return newUser;
	}
	
	// get all users in the repo
	public Iterable<MyTaskUser> getAllUsers(){
		return repository.findAll();
	}
	
	public void removeUserById(String id) 
			throws UserNotFoundException{
		repository.deleteById(Integer.parseInt(id));
	}
	
	// return the first user with the given name
	public MyTaskUser getUserByName(String name) 
			throws UserNotFoundException {
		Iterable<MyTaskUser> users = getAllUsers();
		for(MyTaskUser user: users) {
			if(user.getUserName().equals(name)) {
				return user;
			}
		}
		throw new UserNotFoundException();
	}
	
	public MyTaskUser getUserById(String id) 
			throws UserNotFoundException {
		return repository.findById(Integer.parseInt(id))
				.orElseThrow(UserNotFoundException::new);
	}
	
	public MyTask addTaskToUser(String id, MyTask task) 
			throws UserNotFoundException {
		MyTaskUser user = getUserById(id);
		user.addNewTask(task);
		repository.save(user);
		return task;
	}
	
	public void removeTaskFromUser(String id, String taskIndex)
			throws UserNotFoundException, IndexOutOfBoundsException {
		MyTaskUser user = getUserById(id);
		user.getUserTasks().remove(Integer.parseInt(taskIndex));
		repository.save(user);
	}
	
	public MyTaskUser login(String name, String pass) 
			throws UserNotFoundException, InvalidLoginException {
		log.info("login attempt");
		MyTaskUser user = getUserByName(name);
		if(user.getUserPass().equals(pass)) {
			log.info("successful login");
			return user;
		}
		throw new InvalidLoginException();
	}
}
