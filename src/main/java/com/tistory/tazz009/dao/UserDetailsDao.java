package com.tistory.tazz009.dao;

import org.springframework.data.repository.CrudRepository;

import com.tistory.tazz009.model.UserDetails;

public interface UserDetailsDao extends CrudRepository<UserDetails, String>{

}
