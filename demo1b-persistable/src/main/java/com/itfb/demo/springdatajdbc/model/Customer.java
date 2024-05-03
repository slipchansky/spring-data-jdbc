package com.itfb.demo.springdatajdbc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Customer implements Persistable<Long> {
	@Id
	private Long id;
	private String name;
	
    @Transient
	private boolean isNew = false;
    
	public Customer(String name) {
		super();
		this.name = name;
		isNew = true;
	}
	
	public Customer(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
		isNew = true;
	}

	@Override
	public boolean isNew() {
		return isNew;
	}
}
