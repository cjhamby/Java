package com.app.dao;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PRODUCT_TYPE")
public abstract class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected int productId;
	protected String productCost;
	protected String productTitle;
	protected String productMaker;
	protected String productImage;
}
