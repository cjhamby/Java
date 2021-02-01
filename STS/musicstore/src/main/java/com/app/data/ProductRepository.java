package com.app.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository<T> extends CrudRepository<T, Integer>  {

}
