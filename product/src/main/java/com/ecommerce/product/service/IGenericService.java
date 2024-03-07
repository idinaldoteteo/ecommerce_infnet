package com.ecommerce.product.service;

import java.util.List;

public interface IGenericService<T> {

	List<T> getAll();
	
	T get(Long id);
	
	void save(T item);
	
	void update(T item);
	
	void delete(Long id);
}