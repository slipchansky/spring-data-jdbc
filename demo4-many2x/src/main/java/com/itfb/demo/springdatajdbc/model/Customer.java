package com.itfb.demo.springdatajdbc.model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	
	@Id
	private Long id;
	
	private String name;
	
	@MappedCollection
	private List<CustomerAddress> addresses = new ArrayList<>();
	
	public Customer(String name, CustomerAddress ... addresses) {
		super();
		this.name = name;
		this.addAddresses(addresses);
	}
	
	public void addAddress(CustomerAddress address) {
		addresses.add(address);
	}
	
	public void addAddresses(CustomerAddress ... addresses) {
		Stream.of(addresses).forEach(this::addAddress);
	}
	
	public void removeAddress(CustomerAddress address) {
		this.addresses.remove(address);
	}
}
