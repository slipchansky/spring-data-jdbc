package com.itfb.demo.springdatajdbc.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.itfb.demo.springdatajdbc.model.Customer;

@RunWith(SpringRunner.class)
@DataJdbcTest
public class CustomerRepositoryPersistableTest {

    @Autowired
    CustomerRepository customerRepository;
    
    
    @Test
    public void testSavePersistable() {
    	Customer customer = new Customer(255l, "customer 1");
		var saved = customerRepository.save(customer);
    	var kept = customerRepository.findById(saved.getId()).get();
    	kept.setNew(true); // just for comparison operation
    	assertEquals(saved, kept);
    }
    
    
	
}

