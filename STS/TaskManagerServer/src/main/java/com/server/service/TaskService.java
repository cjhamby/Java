package com.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.dao.MyTask;
import com.server.dao.MyTaskUser;
import com.server.exception.TaskNotFoundException;
import com.server.exception.UserNotFoundException;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class TaskService {
	
	@Autowired
	private ServerService serverService;
	

	public List<MyTask> getUserTasks(String id)
			throws UserNotFoundException {
		return serverService.getUserById(id).getUserTasks();
	}
	
	public MyTaskUser addTaskToUser(String id, MyTask task) {
		try {
			MyTaskUser user = serverService.getUserById(id);	// throws error if not found
			user.addNewTask(task);		
			return serverService.updateUser(user);
		} catch (Exception e) {
			return null;
		}
	}
	
	public MyTaskUser modifyTask(String id, MyTask task) {
		try {
			MyTaskUser user = serverService.getUserById(id);
			System.out.println("got user: " + user);
			System.out.println("putting task: " + task);
			user.updateTask(task);
			System.out.println("updating repo");
			return serverService.updateUser(user);
		} catch (Exception e) {
			return null;
		}
	}
	
	public MyTaskUser removeTaskFromUser(String id, String taskId)
			throws UserNotFoundException, TaskNotFoundException,
			IndexOutOfBoundsException {
		MyTaskUser user = serverService.getUserById(id);
		user.removeTask(Integer.parseInt(taskId));
		return serverService.updateUser(user);
	}
}
