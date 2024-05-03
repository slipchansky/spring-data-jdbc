package com.itfb.demo.springdatajdbc.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
public class CustomerRepositoryOneToManyTest {

    @Autowired
    CustomerRepository customerRepository;
    
    @Test
    public void testCreateOneToMany() {
    	var customer = new Customer("customer 1", 
    			new CustomerAddress("Moscow", "Lenina 1"),
    			new CustomerAddress("Piter", "Lenina 2")
    			);
    	var saved = customerRepository.save(customer);
    	Customer kept = customerRepository.findById(saved.getId()).get();
    	assertEquals(customer.getAddresses().size(), saved.getAddresses().size());
    	assertEquals(customer.getAddresses().size(), kept.getAddresses().size());
    }
    
    @Test
    public void testUpdateOneToMany() {
    	var customer = new Customer("customer 1",
    			new CustomerAddress("Moscow", "Lenina 1"),
    			new CustomerAddress("Piter", "Lenina 2")
    			);
    	var saved = customerRepository.save(customer);
    	Customer kept = customerRepository.findById(saved.getId()).get();
    	kept.setName("customer 1a");
		var a1 = kept.getAddresses().stream().findFirst().get();
		kept.getAddresses().remove(a1);

    	customer = customerRepository.save(kept);
    	assertEquals("customer 1a", customer.getName());
    	assertEquals(1, customer.getAddresses().size());
    }
    
    @Test
    public void testDeleteOneToMany() {
    	var customer = new Customer("customer 1", new CustomerAddress("Moscow", "Lenina 1"));
    	var saved = customerRepository.save(customer);
    	Customer kept = customerRepository.findById(saved.getId()).get();
    	customerRepository.delete(kept);
    	Iterable<Customer> found = customerRepository.findAll();
    	assertFalse(found.iterator().hasNext());
    }
    
    @Test
    public void testFindByNestedProperty() {
    	var customer = new Customer("customer 1", 
    			new CustomerAddress("Moscow", "Lenina 1"),
    			new CustomerAddress("Piter", "Lenina 2")
    			);
    	customerRepository.save(customer);
    	List<Customer> found = customerRepository.findAllByAddressesAddress("Lenina 1");
    	assertEquals(1, found.size());
    }
    
}

