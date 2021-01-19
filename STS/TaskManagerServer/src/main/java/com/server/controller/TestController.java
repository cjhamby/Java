package com.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.server.dao.MyTask;
import com.server.dao.MyTaskUser;
import com.server.service.ServerService;
import com.server.service.TaskService;

@RestController
public class TestController {

	@Autowired
	private ServerService serverService;
	
	@Autowired
	private TaskService taskService;

// 	return a test user
	@GetMapping("users/test")
	public MyTaskUser getNewUser() {
		return new MyTaskUser("chros", "hambug");
	}

	@GetMapping("users/testAdd")
	public MyTaskUser testAdd() {
		return serverService.addNewUser("cool", "bean");
	}

	
	@GetMapping("users/{id}/tasks/testAdd")
	public MyTask addATestTask(@PathVariable("id") String id) {
		try {
			MyTask test = new MyTask();
			test.setTaskName("test task");
			test.setTaskDesc("test desc");
			test.setEmail("testEmail");
			test = taskService.addTaskToUser(id, test);
			return test;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}
