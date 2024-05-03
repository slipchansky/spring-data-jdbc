package com.itfb.demo.springdatajdbc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Embedded.OnEmpty;

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
	
	// Demonstrates converters
    private Contacts contacts;
    
    @Embedded(onEmpty = OnEmpty.USE_NULL)
    private Requisites requisites;
	
	public Customer(String name) {
		super();
		this.name = name;
	}
	
}
