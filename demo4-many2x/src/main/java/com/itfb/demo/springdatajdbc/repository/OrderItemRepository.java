package com.itfb.demo.springdatajdbc.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itfb.demo.springdatajdbc.model.OrderItem;
import com.itfb.demo.springdatajdbc.model.OrderItemView;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
	
	public List<OrderItem> findByOrderId(Long orderId);
	
	@Query("select i.product, i.\"order\", i.amount, p.name as product_name, p.price as product_price "
			+ " from order_item i, product p"
			+ " where i.product = p.id and i.\"order\" = :orderId")
	List<OrderItemView> getItems(@Param("orderId") Long orderId);

}
