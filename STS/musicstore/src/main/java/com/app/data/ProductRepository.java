package com.app.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface ProductRepository<Product> extends CrudRepository<Product, Integer>  {

}
