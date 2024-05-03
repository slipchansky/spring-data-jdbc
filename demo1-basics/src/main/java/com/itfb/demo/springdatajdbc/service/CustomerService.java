package com.itfb.demo.springdatajdbc.service;

import java.util.Optional;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itfb.demo.springdatajdbc.model.Customer;
import com.itfb.demo.springdatajdbc.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class CustomerService {
	
	private final CustomerRepository customerRepository;

	public Customer save(Customer entity) {
		return customerRepository.save(entity);
	}
	
	public void saveWithError(Customer entity) {
		customerRepository.save(entity);
		throw new ForceErrorException();
	}
	

	public Optional<Customer> findById(Long id) {
		return customerRepository.findById(id);
	}

	public void deleteById(Long id) {
		customerRepository.deleteById(id);
	}

}
