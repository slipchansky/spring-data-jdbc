package com.itfb.demo.springdatajdbc.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itfb.demo.springdatajdbc.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{
	
	@Query("select c.* from customer c, customer_address ca where ca.customer = c.id and ca.address = :address")
	public List<Customer> findAllByAddressesAddress(String address);
}
