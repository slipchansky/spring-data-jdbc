package com.itfb.demo.springdatajdbc.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import com.itfb.demo.springdatajdbc.model.Customer;
import com.itfb.demo.springdatajdbc.model.CustomerAddress;

@RunWith(SpringRunner.class)
@DataJdbcTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;
    
    @Test
    public void testCreateOneToOne() {
    	var customer = new Customer("customer 1", new CustomerAddress("Moscow", "Lenina 1"));
    	var saved = customerRepository.save(customer);
    	Customer kept = customerRepository.findById(saved.getId()).get();
    	assertEquals(saved, kept);
    }
    
    @Test
    public void testUpdateOneToOne() {
    	var customer = new Customer("customer 1", new CustomerAddress("Moscow", "Lenina 1"));
    	var saved = customerRepository.save(customer);
    	Customer kept = customerRepository.findById(saved.getId()).get();
    	kept.setName("customer 1a");
    	customerRepository.save(kept);
    }

    
    @Test
    public void testDeleteOneToOne() {
    	var customer = new Customer("customer 1", new CustomerAddress("Moscow", "Lenina 1"));
    	var saved = customerRepository.save(customer);
    	Customer kept = customerRepository.findById(saved.getId()).get();
    	customerRepository.delete(kept);
    }
    
    @Test
    public void testFindByNestedProperty() {
    	var customer = new Customer("customer 1", new CustomerAddress("Moscow", "Lenina 1"));
    	customerRepository.save(customer);
    	List<Customer> found = (List<Customer>)customerRepository.findAll();
    	found = customerRepository.findAllByAddress("Lenina 1");
    	assertEquals(1, found.size());
    }
    
}

