package com.ecommerce.product.service.impl;

import org.springframework.stereotype.Service;

import com.ecommerce.product.domain.Product;
import com.ecommerce.product.repository.IProductRepository;
import com.ecommerce.product.service.IProductService;

@Service
public class ProductService extends GenericService<Product, Long, IProductRepository> implements IProductService{

	public ProductService(IProductRepository repository) {
		super(repository);
	}

}