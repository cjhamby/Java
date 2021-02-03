package com.app;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyOrderRepository extends PagingAndSortingRepository<MyOrder, Integer>{
	/*
	 * Repositories have several interesting and useful features
	 * I recommend the article about them on spring's website:
	 * https://docs.spring.io/spring-data/mongodb/docs/1.2.0.RELEASE/reference/html/repositories.html
	 * 
	 * when this project was created,
	 * I added the "Rest Repositories" starter from Spring Initializr
	 * With that dependency, Spring boot creates a REST API from repositories
	 * 
	 * The API has a base URI specified in application properties file
	 * the links are based on the name of the repository, as follows:
	 * 
	 * MyOrderRepository -> MyOrder -> myOrders
	 * (full repo name)  -> (type)  -> (lowercase first letter and pluralized)
	 * 
	 * In Postman, 
	 * make a GET request to the following path:
	 * 		localhost:8080/<BASE URI>/myOrders
	 * 
	 * and you should see results
	 */
}
