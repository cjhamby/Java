package com.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.log4j.Log4j2;


@Component
@Log4j2
@Data
public class Cart {
	private List<Product> cartItems;
	
	public Cart() {
		cartItems = new ArrayList<>();
	}
	
	public void addToCart(Product newProduct) {
		try {
			cartItems.add(newProduct);
		} catch (Exception e) {
			log.warn(e);
		}
	}
}
