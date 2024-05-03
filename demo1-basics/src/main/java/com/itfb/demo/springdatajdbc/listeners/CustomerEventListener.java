package com.itfb.demo.springdatajdbc.listeners;

import org.springframework.data.relational.core.mapping.event.AbstractRelationalEventListener;
import org.springframework.data.relational.core.mapping.event.AfterSaveEvent;
import org.springframework.data.relational.core.mapping.event.BeforeSaveEvent;

import com.itfb.demo.springdatajdbc.model.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerEventListener extends AbstractRelationalEventListener<Customer>{
	

	@Override
	protected void onBeforeSave(BeforeSaveEvent<Customer> event) {
		log.info("Saving {}", event.getEntity());
		super.onBeforeSave(event);
	}

	@Override
	protected void onAfterSave(AfterSaveEvent<Customer> event) {
		log.info("Saved {}", event.getEntity());
		super.onAfterSave(event);
	}
	
	

}
