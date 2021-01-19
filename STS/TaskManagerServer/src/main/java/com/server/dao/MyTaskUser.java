package com.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.server.exception.TaskNotFoundException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data				
@Entity				
public class MyTaskUser {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer userId;
	
	private String userName;
	private String userPass;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<MyTask> userTasks;
	
	public MyTaskUser() {
		userTasks = new ArrayList<>();
	}
	
	public MyTaskUser(String name, String pass) {
		userName = name;
		userPass = pass;
		userTasks = new ArrayList<>();
	}
	
	public MyTask addNewTask(MyTask newTask) {
		try {
			userTasks.add(newTask);
			return newTask;
		} catch (Exception e) {
			return null;
		}
	}
	
	public MyTask updateTask(MyTask updatedTask) 
			throws TaskNotFoundException {
		int taskId = updatedTask.getTaskId();
		System.out.println("update " + taskId);
		for(int i=0; i<userTasks.size(); i++) {
			MyTask task = userTasks.get(i);			
			if(task.getTaskId() == taskId) {
				userTasks.remove(i);
				userTasks.add(updatedTask);
				return updatedTask;
			}
		}
		// task not found; use addNewTask instead
		throw new TaskNotFoundException();
	}
	
	public void removeTask(int taskId) 
			throws TaskNotFoundException {
		for(int i=0; i<userTasks.size(); i++) {
			MyTask temp = userTasks.get(i);			
			if(temp.getTaskId() == taskId) {
				userTasks.remove(i);
				return;
			}
		}
		throw new TaskNotFoundException();
	}
}
