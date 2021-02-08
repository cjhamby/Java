package com.app.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name="store_orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int orderId;
	
	@ElementCollection
	@CollectionTable(name = "store_orders_products")
	private List<Integer> products;		// only contains the product ids 
	
	@Embedded
	private Address shippingAddress;	
	private String username;			// account that placed the order
	
	@Temporal(TemporalType.DATE)
	private Date orderDate;				// when the order was placed
	
	public Order() {
		products = new ArrayList<>();
	}
	
	public void addProductsFromCart(Cart cart) {
		List<Product> items = cart.getCartItems();
		for(Product item: items) {
			this.products.add(item.getProductId());
		}
	}
	
}
