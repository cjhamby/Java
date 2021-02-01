package com.app.dao;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected int productId;
	protected String productCost;
	protected String productTitle;
	protected String productMaker;
	protected String productImage;

}
