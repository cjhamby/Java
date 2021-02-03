package com.app;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * This is a sample POJO for demonstration purposes
 * 
 * these are all standard annotations that should be familiar :-)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity	
public class MyOrder {		// mysql hard-reserves the keyword 'Order'
	
	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String orderName;	// mysql soft-reserves the keyword 'Name'
	private String cost;
}
