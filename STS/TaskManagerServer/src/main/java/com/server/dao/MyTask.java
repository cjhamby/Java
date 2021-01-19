package com.server.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MyTask {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer taskId;
	
	private String taskName;
	private String taskDesc;
	private String startDate;
	private String endDate;
	private String email;
	private String priority;
}
