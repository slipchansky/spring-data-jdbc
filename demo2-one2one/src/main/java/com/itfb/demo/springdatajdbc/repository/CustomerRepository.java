package com.itfb.demo.springdatajdbc.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itfb.demo.springdatajdbc.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{
	
	@Query("select * from customer, customer_address where "
			+ " customer_address.customer = customer.id and customer_address.address = :address")
	List<Customer> findAllByAddress(String address);
}
