package com.itfb.demo.springdatajdbc.repository;

import java.math.BigDecimal;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itfb.demo.springdatajdbc.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
	
	
	@Query("select sum(i.amount*p.price) from order_item i, product p where i.product = p.id and i.order = :orderId")
	BigDecimal getOrderSum(@Param("orderId") Long orderId);
	
}
