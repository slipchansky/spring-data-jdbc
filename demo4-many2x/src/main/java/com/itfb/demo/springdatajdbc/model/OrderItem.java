package com.itfb.demo.springdatajdbc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("order_item")
public class OrderItem {
	
	@Column("order")
	private Long orderId;
	
	@Column("product")
	private Long productId;
	
	private int amount;
	
	public OrderItem(Product product, int amount) {
		this.productId = product.getId();
		this.amount = amount;
		
	}
}
