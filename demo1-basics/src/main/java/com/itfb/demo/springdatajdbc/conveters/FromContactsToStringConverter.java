package com.itfb.demo.springdatajdbc.conveters;

import org.springframework.core.convert.converter.Converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itfb.demo.springdatajdbc.model.Contacts;

import lombok.SneakyThrows;

public class FromContactsToStringConverter implements Converter<Contacts, String> {

	@Override
	@SneakyThrows
	public String convert(Contacts source) {

		return (source==null)?"{}":new ObjectMapper().writeValueAsString(source);
	}

}
