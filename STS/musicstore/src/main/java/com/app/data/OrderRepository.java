package com.app.data;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.app.dao.Order;

public interface OrderRepository extends PagingAndSortingRepository<Order, Integer>{
	public List<Order> findByUsernameIgnoreCase(String username);
}
