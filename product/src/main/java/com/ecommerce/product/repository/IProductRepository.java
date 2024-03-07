package com.ecommerce.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.product.domain.Product;

public interface IProductRepository extends JpaRepository<Product, Long>{

}
