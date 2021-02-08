package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CD;
import com.app.dao.Instrument;
import com.app.dao.Order;
import com.app.dao.Product;
import com.app.data.CDRepository;
import com.app.data.InstrumentRepository;
import com.exception.ProductNotFoundException;

@Service
public class ProductService {
	@Autowired
	private InstrumentRepository instrumentRepo;
	
	@Autowired
	private CDRepository cdRepo;
	
	public List<Instrument> getInstruments() {
		List<Instrument> instruments = new ArrayList<>();
		instrumentRepo.findAll().forEach(instrument -> instruments.add(instrument));
		return instruments;
	}
	
	public List<CD> getCDs() {
		List<CD> cds = new ArrayList<>();
		cdRepo.findAll().forEach(cd -> cds.add(cd));
		return cds;
	}
	
	
	public List<Product> getProductsInOrder(Order order)
		throws ProductNotFoundException	{
		
		List<Product> products = new ArrayList<>();
		for(int id: order.getProducts()) {
			products.add((Product)(findProductById(id).get()));
		}
		return products;
	}
	
	
	public Optional<? extends Product> findProductById(int id) 
			throws ProductNotFoundException {
		
		Optional <? extends Product> product;
		
		product = instrumentRepo.findById(id);
		if(product.isPresent()) {
			return product;
		}
		product = cdRepo.findById(id);
		if(product.isPresent()) {
			return product;
		}
		
		throw new ProductNotFoundException();
	}
}
