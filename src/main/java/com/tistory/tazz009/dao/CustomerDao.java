package com.tistory.tazz009.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.tistory.tazz009.model.Customer;

public interface CustomerDao extends CrudRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

	Page<Customer> findByLastname(String string, Pageable pageable);

}
