package com.itfb.demo.springdatajdbc.repository;

import org.springframework.data.jdbc.core.JdbcAggregateTemplate;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WithInsertImpl <T> implements WithInsert<T> {

	private final JdbcAggregateTemplate template;


	@Override
	public T insert(T t) {
		return template.insert(t);
	}
}