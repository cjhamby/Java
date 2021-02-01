package com.app.dao;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)		// don't call these methods from super
public class CD extends Product {
	
	// most information is covered by the parent class, Product
	// here are the extra, instrument-specific details
	private String band;
	private String genre;
}
