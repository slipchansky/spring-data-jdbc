package com.itfb.demo.springdatajdbc.model;


import org.springframework.data.annotation.Id;

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
	
	private CustomerAddress address;
	
	public Customer(String name, CustomerAddress address) {
		super();
		this.name = name;
		this.address = address;
	}
}
