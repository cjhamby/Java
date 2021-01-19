package com.client.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyTask {
	
	private Integer taskId;	
	private String taskName;
	private String taskDesc;
	private String startDate;
	private String endDate;
	private String email;
	private String priority;
}