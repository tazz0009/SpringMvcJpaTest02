package com.tistory.tazz009.dao;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.data.jpa.domain.Specifications.*;

import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;

import com.tistory.tazz009.model.Customer;
import static com.tistory.tazz009.model.CustomerSpecifications.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={  
		"classpath:spring/context-common.xml",   
        "classpath:spring/context-datasource.xml",   
        })
public class CustomerDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private CustomerDao dao;
	
	@BeforeTransaction
	public void setupData() throws Exception {
		if (countRowsInTable("Customer") == 0) {
			executeSqlScript("classpath:data.sql", false);
		}
	}
	
	@Test
	public void findsAllCustomers() throws Exception {
		Iterable<Customer> result = dao.findAll();
		assertThat(result, is(notNullValue()));
		assertTrue(result.iterator().hasNext());
	}
	
	@Test
	public void findsFirstPageOfMatthews() throws Exception {
		Page<Customer> customers = dao.findByLastname("Matthews", new PageRequest(0, 2));

		assertThat(customers.getContent().size(), is(2));
		assertFalse(customers.hasPreviousPage());
	}

	@Test
	public void findsCustomerById() throws Exception {

		Customer customer = dao.findOne(2L);

		assertThat(customer.getFirstname(), is("Carter"));
		assertThat(customer.getLastname(), is("Beauford"));
	}

	@Test
	public void findsCustomersBySpecification() throws Exception {

		Customer dave = dao.findOne(1L);

		LocalDate expiryLimit = new LocalDate(2011, 3, 1);
		List<Customer> result = dao.findAll(where(accountExpiresBefore(expiryLimit)));

		assertThat(result.size(), is(1));
		assertThat(result, hasItems(dave));
	}
	
}
