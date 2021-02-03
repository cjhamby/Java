package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/*
 * by implementing CommandLineRunner, 
 * this class signals to Spring that it needs to RUN at RUNTIME
 */
@Configuration
public class DataLoader implements CommandLineRunner{
	
	@Autowired
	private MyOrderRepository repository;
	
	/*
	 * this just preloads some sample orders to the repository
	 * the ID argument is 0 by default, 
	 * since ID is always overwritten when stored to the repository
	 */
	public void run(String... args) throws Exception {
		this.repository.save(new MyOrder(0, "adam bobson", "$50.00"));
		this.repository.save(new MyOrder(0, "charli xcx", "$52.00"));
		this.repository.save(new MyOrder(0, "dancing master", "$40.00"));
	}
	
}
