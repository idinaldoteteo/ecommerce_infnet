package com.ecommerce.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.product.domain.Product;
import com.ecommerce.product.service.IProductService;

@RestController	
@RequestMapping("/api/product")
public class ProductController extends GenericController<Product> {

    public ProductController(IProductService service){ super(service); }
}
