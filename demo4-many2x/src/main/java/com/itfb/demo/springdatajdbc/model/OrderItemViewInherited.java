package com.itfb.demo.springdatajdbc.model;

import java.math.BigDecimal;

import org.springframework.data.relational.core.mapping.Column;

import lombok.Data;
import lombok.ToString;

@Data
public class OrderItemViewInherited  extends OrderItem {
	
	@Column("product_name")
	private String productName;
	
	@Column("product_price")
	private BigDecimal productPrice;
	
}
