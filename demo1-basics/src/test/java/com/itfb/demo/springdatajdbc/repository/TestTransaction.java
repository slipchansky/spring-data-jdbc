package com.itfb.demo.springdatajdbc.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.test.context.junit4.SpringRunner;

import com.itfb.demo.springdatajdbc.conveters.FromContactsToStringConverter;
import com.itfb.demo.springdatajdbc.conveters.FromStringToContactsConverter;
import com.itfb.demo.springdatajdbc.model.Customer;
import com.itfb.demo.springdatajdbc.service.CustomerService;
import com.itfb.demo.springdatajdbc.service.ForceErrorException;

@RunWith(SpringRunner.class)
@DataJdbcTest
@Import(TestTransaction.CustomerTestConfiguration.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class TestTransaction {
	
    @Autowired
    CustomerService customerService;
    
    @Test
    public void testRollbackTransaction() {
    	var customer = customerService.save(new Customer("customer 1"));
    	try {
    		customer.setName("customer 1a");
    		customerService.saveWithError(customer);
    	} catch(ForceErrorException e) {
    		// ignore
    	} catch(Exception e) {
    		Assert.
    		fail("ForceErrorException is expected");
    	} finally {
    		var found = customerService.findById(customer.getId());
    		assertEquals("customer 1", found.get().getName());
    		customerService.deleteById(customer.getId());
    	}
    }
	
	
    static class CustomerTestConfiguration {

    	@Bean
    	CustomerService customerService(CustomerRepository customerRepository) {
    		return new CustomerService(customerRepository);
    	}
    	
    	@Bean
    	public JdbcCustomConversions jdbcCustomConversions(){
    		return new JdbcCustomConversions(List.of(
    				new FromContactsToStringConverter(), 
    				new FromStringToContactsConverter()
    				));
    	} 
    	
    } 
	

}
