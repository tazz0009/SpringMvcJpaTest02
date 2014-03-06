package com.tistory.tazz009.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tistory.tazz009.model.Account;
import com.tistory.tazz009.model.Customer;

public interface AccountDao extends CrudRepository<Account, Long> {

	List<Account> findByCustomer(Customer customer);

}
