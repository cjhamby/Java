package com.client.dao;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor	
@AllArgsConstructor	
public class MyTaskUser {
	private Integer userId;
	private String userName;
	private String userPass;	
	private List<MyTask> userTasks;
}
