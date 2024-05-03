package com.itfb.demo.springdatajdbc.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	
	@Id
	private Long id;
	
	private Long customer;
	
	private Set<OrderItem> items = new HashSet<>();
	
	public Order(Customer customer) {
		this.customer = customer.getId();
	}
	
	public Order addItem(Product product, int amount) {
		return this.addItem(new OrderItem (product, amount));
	}
	
	public Order addItem(OrderItem item) {
		this.items.add(item);
		return this;
	}
	
	public Order removeItem(Product product) {
		var oItem = this.items.stream().filter(item -> item.getProductId().equals(product.getId())).findFirst();
		oItem.ifPresent(item -> removeItem (item));
		return this;
	}

	public Order removeItem(OrderItem item) {
		this.items.remove(item);
		return this;
	}
	
}
