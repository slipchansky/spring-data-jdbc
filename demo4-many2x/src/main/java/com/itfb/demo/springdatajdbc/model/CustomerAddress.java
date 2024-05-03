package com.itfb.demo.springdatajdbc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("customer_address")
public class CustomerAddress {
	
	@Id
	@Column("customer")
	private Long customerId;
	
	private String city;
	
	private String address;
	
	@Column("customer_key")
	private Integer key;
	
	
	public CustomerAddress(String city, String address) {
		this.city = city;
		this.address = address;
	}
}
