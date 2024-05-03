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
	
	public Customer(String name) {
		super();
		this.name = name;
	}
	
}
