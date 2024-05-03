package com.itfb.demo.springdatajdbc.conveters;

import org.springframework.core.convert.converter.Converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itfb.demo.springdatajdbc.model.Contacts;

import lombok.SneakyThrows;

public class FromStringToContactsConverter implements Converter<String, Contacts> {

	@Override
	@SneakyThrows
	public Contacts convert(String source) {
		return source==null?new Contacts():new ObjectMapper().readValue(source, Contacts.class);
	}

}
