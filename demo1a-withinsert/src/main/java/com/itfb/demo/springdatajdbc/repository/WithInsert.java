package com.itfb.demo.springdatajdbc.repository;

public interface WithInsert<T> {
	
	T insert(T t);
	
}