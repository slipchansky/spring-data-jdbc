package com.itfb.demo.springdatajdbc.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.test.context.junit4.SpringRunner;

import com.itfb.demo.springdatajdbc.conveters.FromContactsToStringConverter;
import com.itfb.demo.springdatajdbc.conveters.FromStringToContactsConverter;
import com.itfb.demo.springdatajdbc.listeners.CustomerEventListener;
import com.itfb.demo.springdatajdbc.model.Contacts;
import com.itfb.demo.springdatajdbc.model.Customer;
import com.itfb.demo.springdatajdbc.model.Requisites;

@RunWith(SpringRunner.class)
@DataJdbcTest
@Import(CustomerRepositoryTest.CustomerTestConfiguration.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

	@Autowired
	ApplicationEventPublisher applicationEventPublisher;
    
    @Test
    public void testCreate() {
    	var saved = customerRepository.save(new Customer("customer 1"));
    	var kept = customerRepository.findById(saved.getId()).get();
    	assertEquals(saved, kept);
    }

    @Test
    public void testFindAllByNameIgnoreCase() {
    	customerRepository.save(new Customer("customer 1"));
		customerRepository.save(new Customer("customer 2"));
    	List<Customer> found = customerRepository.findAllByNameIgnoreCase("Customer 2");
    	assertEquals(1, found.size());
    }

	@Test
    public void testUpdate() {
    	var customer = customerRepository.save(new Customer("customer 1"));
		customer.setName("Customer 1a");
		customerRepository.save(customer);
    	var kept = customerRepository.findById(customer.getId()).get();
    	assertEquals("Customer 1a", kept.getName());
    }

	@Test
    public void testDelete() {
    	var customer = customerRepository.save(new Customer("customer 1"));
    	customerRepository.deleteById(customer.getId());
    	assertFalse(customerRepository.findById(customer.getId()).isPresent());
    }

    @Test
    public void testConverters() {
    	Customer customer = new Customer("customer 1");
    	customer.setContacts(new Contacts("address", "phone"));
		var saved = customerRepository.save(customer);
    	var kept =  customerRepository.findById(saved.getId()).get();
    	assertEquals(saved, kept);
    }

    @Test
    public void testEmbedded() {
    	Customer customer = new Customer("customer 1");
    	customer.setRequisites(new Requisites("account number", "enterprise inn"));
		var saved = customerRepository.save(customer);
    	var kept = customerRepository.findById(saved.getId()).get();
    	assertEquals(saved, kept);
    }
    
    @Test
    public void testProjection() {
    	var saved = customerRepository.save(new Customer("customer 1"));
    	var found = customerRepository.findAllIdByName(("customer 1"));
    	assertEquals(saved.getId(), found.get(0).getId());
    }

   
    static class CustomerTestConfiguration {

    	@Bean
    	public JdbcCustomConversions jdbcCustomConversions(){
    		return new JdbcCustomConversions(List.of(
    				new FromContactsToStringConverter(), 
    				new FromStringToContactsConverter()
    				));
    	} 
    	
    	@Bean
    	public CustomerEventListener customerEventListener() {
    		return new CustomerEventListener();
    	}
    	
    } 
	
}

