package com.itfb.demo.springdatajdbc.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import com.itfb.demo.springdatajdbc.model.Customer;
import com.itfb.demo.springdatajdbc.model.CustomerAddress;
import com.itfb.demo.springdatajdbc.model.Order;
import com.itfb.demo.springdatajdbc.model.OrderItem;
import com.itfb.demo.springdatajdbc.model.OrderItemView;
import com.itfb.demo.springdatajdbc.model.Product;

@RunWith(SpringRunner.class)
@DataJdbcTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class OrderTest {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	private Product createProduct(String productName, int productPrice) {
		return productRepository.save(new Product(productName, productPrice));
	}
		
	@Test
	public void testCreateOrder() {
		
		Customer customer = customerRepository.save(new Customer("ITFB", new CustomerAddress("Moskow", "Lenina1")));
		Order order = new Order(customer);
		var computer = createProduct("Computer", 10000);
		var mouse = createProduct("Mouse", 150);
		var keyboard = createProduct("Mouse", 300);
		
		order.addItem(computer, 1);
		order.addItem(keyboard, 2);
		order.addItem(mouse, 3);
		
		var saved = orderRepository.save(order);
		assertNotNull(saved.getId());
		
		var retrieved = orderRepository.findById(order.getId()).get();
		assertEquals(3, retrieved.getItems().size());
	}
	
	@Test
	public void testUpdateItems() {
		Customer customer = customerRepository.save(new Customer("ITFB", new CustomerAddress("Moskow", "Lenina1")));
		Order order = new Order(customer);
		var computer = createProduct("Computer", 10000);
		var mouse = createProduct("Mouse", 150);
		var keyboard = createProduct("Mouse", 300);
		order.addItem(computer, 1);
		order.addItem(keyboard, 2);
		order.addItem(mouse, 3);
		order = orderRepository.save(order);
		Long orderId = order.getId();
		var retrieved = orderRepository.findById(orderId).get();
		retrieved.removeItem(computer);
		orderRepository.save(retrieved);
		retrieved = orderRepository.findById(order.getId()).get();
		assertEquals(2, retrieved.getItems().size());
	}
	

	
	Long prepareOrder() {
		Customer customer = customerRepository.save(new Customer("ITFB", new CustomerAddress("Moskow", "Lenina1")));
		Order order = new Order(customer);
		var computer = productRepository.save(new Product("Computer", 10000));
		var mouse = productRepository.save(new Product("Mouse", 150));
		var keyboard = productRepository.save(new Product("Keyboard", 300));
		
		order.addItem(new OrderItem(computer, 1));
		order.addItem(new OrderItem(keyboard, 2));
		order.addItem(new OrderItem(mouse, 3));
		var saved = orderRepository.save(order);
		return saved.getId();
	}
	
	@Test
	public void testIterateOrderItems() {
		long orderId = prepareOrder();
		var items = orderItemRepository.findByOrderId(orderId);
		items.stream().forEach(item -> {
			var product = productRepository.findById(item.getProductId()).get();
			System.out.println(String.format("%s %d %d", product.getName(), product.getPrice().intValue(), item.getAmount()));
		});
	}
	
	@Test
	public void testViewOrderItems() {
		long orderId = prepareOrder();
		List<OrderItemView> items = orderItemRepository.getItems(orderId);
		items.stream().forEach(item -> {
			System.out.println(String.format("%s %d %d", item.getProductName(), item.getProductPrice().intValue(), item.getAmount()));
		});
	}
	
}

