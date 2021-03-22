package com.ctsousa.algamoney.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class AbstractResource<T> {
	
	@Autowired
	protected ApplicationEventPublisher publisher;
	
	@Autowired
	protected MessageSource messageSource;
	
}
