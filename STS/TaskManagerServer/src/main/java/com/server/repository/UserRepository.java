package com.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.server.dao.MyTaskUser;

@Repository
public interface UserRepository extends CrudRepository <MyTaskUser, Integer>{

}
