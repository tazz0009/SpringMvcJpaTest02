package com.tistory.tazz009.dao;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;

import com.tistory.tazz009.model.Account;
import com.tistory.tazz009.model.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={  
		"classpath:spring/context-common.xml",   
        "classpath:spring/context-datasource.xml",   
        })
public class AccountDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@BeforeTransaction
	public void setupData() throws Exception {
		if (countRowsInTable("Customer") == 0) {
			executeSqlScript("classpath:data.sql", false);
		}
	}
	
	@Test
	public void savesAccount() throws Exception {
		
		Account account = accountDao.save(new Account());
		assertThat(account.getId(), is(notNullValue()));
	}
	
	@Test
	public void findsCustomersAccounts() throws Exception {
		
		Customer customer = customerDao.findOne(1L);
		List<Account> accounts = accountDao.findByCustomer(customer);
		
		assertFalse(accounts.isEmpty());
		assertThat(accounts.get(0).getCustomer(), is(customer));
	}

}
