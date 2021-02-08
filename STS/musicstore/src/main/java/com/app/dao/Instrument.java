package com.app.dao;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)		// requirement for extending abstract classes?
@DiscriminatorValue("Instrument")
public class Instrument extends Product{
	
	// most information is covered by the parent class, Product
	// here are the extra, instrument-specific details
	private String instrumentModel;
	private String instrumentCondition;
	
}
