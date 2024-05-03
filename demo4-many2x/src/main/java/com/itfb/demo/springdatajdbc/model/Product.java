package com.itfb.demo.springdatajdbc.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	@Id
	private Long id;
	
	private String name;
	
	private BigDecimal price;
	
	public Product(String name, int price) {
		this.name = name;
		this.price = BigDecimal.valueOf(price);
	}
}
