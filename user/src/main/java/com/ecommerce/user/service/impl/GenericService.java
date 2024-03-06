package com.ecommerce.user.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.user.service.IGenericService;

public class GenericService<T, ID, R extends JpaRepository<T, ID>> implements IGenericService<T>{

	protected final R repository;
	
	public GenericService(R repository) {
		this.repository = repository;
	}
	
	@Override
	public List<T> getAll() {
		return repository.findAll();
	}

	@Override
	public T get(Long id) {
		
		Optional<T> result = repository.findById((ID) id);
		
		if(result.isPresent()) {
			return (T) result;
		}
		
		return null;
	}

	@Override
	public void save(T item) {
		repository.save(item);
	}

	@Override
	public void update(T item) {
		repository.save(item);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById((ID) id);
	}

}
