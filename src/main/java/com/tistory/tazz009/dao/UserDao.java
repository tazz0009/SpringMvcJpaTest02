package com.tistory.tazz009.dao;

import org.springframework.data.repository.CrudRepository;

import com.tistory.tazz009.model.User;

public interface UserDao extends CrudRepository<User, String>{

}
