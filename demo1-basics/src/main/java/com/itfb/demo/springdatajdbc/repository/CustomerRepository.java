package com.itfb.demo.springdatajdbc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itfb.demo.springdatajdbc.model.Customer;


@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	List<Customer> findAllByNameIgnoreCase(String customer);

	public interface CustomerProjection {
		Long getId();
	}
	
	List<CustomerProjection> findAllIdByName(String name);
	
}
