package com.itfb.demo.springdatajdbc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itfb.demo.springdatajdbc.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
