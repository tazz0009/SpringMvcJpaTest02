package com.tistory.tazz009.dao;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tistory.tazz009.model.Address;
import com.tistory.tazz009.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={  
		"classpath:spring/context-common.xml",   
        "classpath:spring/context-datasource.xml",   
        })
public class UserDaoTest {

	@Autowired
	private UserDao dao;
	
	@Test
	public void saveUser() throws Exception {
		User user = new User();
		user.setUserId("test001");
		user.setUserName("First User");
		user.setEmail("test001@gmail.com");
		
		Address address1 = new Address();
		address1.setState("First State");
		address1.setCity("First City");
		address1.setStreet("First Street");
		address1.setPincode("100001");
		
		Address address2 = new Address();
		address2.setState("Second State");
		address2.setCity("Second City");
		address2.setStreet("Second Street");
		address2.setPincode("100001");
		
		user.getAddresses().add(address1);
		user.getAddresses().add(address2);
		
		User savedUser = dao.save(user);
		assertFalse(savedUser.getAddresses().isEmpty());
		assertThat(savedUser.getAddresses().size(), is(2));
		
		System.out.println(savedUser.toString());
	}

	
}
