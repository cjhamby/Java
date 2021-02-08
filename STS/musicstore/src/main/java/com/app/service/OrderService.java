package com.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.Address;
import com.app.dao.Cart;
import com.app.dao.Order;
import com.app.data.OrderRepository;
import com.exception.OrderNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	public List<Order> getOrdersByUser(String username) {
		return orderRepo.findByUsernameIgnoreCase(username);
	}
	
	public Iterable<Order> getAllOrders() {
		return orderRepo.findAll();
	}
	
	public Order getOrderById(int id) throws OrderNotFoundException {
		return orderRepo.findById(id).orElseThrow(OrderNotFoundException::new);
	}
	
	
	public void placeOrder(String username, Cart cart, Address address) {
		Order order = new Order();
		order.setUsername(username);
		order.setOrderDate(new Date());
		order.addProductsFromCart(cart);
		order.setShippingAddress(address);
		orderRepo.save(order);
	}
	
	
}
