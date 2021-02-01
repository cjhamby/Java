package com.app.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.dao.StoreAccount;

@Repository
public interface AccountRepository extends CrudRepository<StoreAccount, Long>{
	
	// the repo can search by the field ____ of findBy____
	StoreAccount findByUsername(String name);
}
