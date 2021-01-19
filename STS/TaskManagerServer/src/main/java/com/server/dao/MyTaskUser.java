package com.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data				
@NoArgsConstructor	
@Entity				
public class MyTaskUser {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer userId;
	
	private String userName;
	private String userPass;
	
	@OneToMany
	private List<MyTask> userTasks;
	
	
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
}
