package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.app.dao.Instrument;
import com.app.dao.Product;
import com.app.data.ProductRepository;

@Service
public class ProductService<T extends Product> {
	
	//TODO fix this or something
	//@Autowired
	//private ProductRepository<T> repository;
	
	//TODO - items are currently added to the store via data sql files
	public T addProductToStore(T newProduct) { 
		return null;
	};

	//TODO - items are currently added to the store via data sql files
	public void removeProductFromStore(int productId) {
		
	};
	
	//TODO - remove the need to pass an argument to this 
	public List<T> getProductsInStore(CrudRepository<T, Integer> repository) {
		List<T> productList = new ArrayList<>();
		repository.findAll().forEach(k -> {
			productList.add((T)k);
		});
		return productList;
	}
	
	
	
}
