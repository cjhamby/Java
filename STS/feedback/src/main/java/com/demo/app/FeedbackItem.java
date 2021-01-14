package com.demo.app;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class FeedbackItem {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String message;
	private int numStars;
}
