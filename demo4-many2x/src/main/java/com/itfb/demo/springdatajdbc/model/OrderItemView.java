package com.itfb.demo.springdatajdbc.model;

import java.math.BigDecimal;

import org.springframework.data.relational.core.mapping.Column;

import lombok.Data;

@Data
public class OrderItemView  {
	
	@Column("order")
	private Long orderId;
	
	@Column("product")
	private Long productId;
	
	@Column("product_name")
	private String productName;
	
	@Column("product_price")
	private BigDecimal productPrice;
	
	private int amount;
}
