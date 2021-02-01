package com.app.service;

import org.springframework.stereotype.Service;

import com.app.dao.Order;
import com.app.dao.Product;

@Service
public interface CartService {
	
	// add a product to the cart
	public Product addToCart(int productId);
	
	// remove a product from the cart
	public void removeFromCart(int productId);

	// convert the cart items to an order that can then be placed
	public Order createOrder();
}
