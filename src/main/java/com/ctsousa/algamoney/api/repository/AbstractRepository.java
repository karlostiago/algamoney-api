package com.ctsousa.algamoney.api.repository;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractRepository<T> {
	
	@PersistenceContext
	protected EntityManager manager;
	
	private T entity;
	
	@SuppressWarnings("unchecked")
	public AbstractRepository() {
		try {
			Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			entity = (T) clazz.newInstance();			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public T getEntity() {
		return entity;
	}
}
