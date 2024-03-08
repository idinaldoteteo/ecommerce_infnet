package com.ecommerce.order.infrastructure.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ecommerce.order.util.Util;

@Service
public class OrderWebClient {
	
	@Async
	public Map<String, Object> getUserMap(long userId) {
		WebClient.ResponseSpec responseUser = WebClient.create().get().uri("http://localhost:8082/api/user/" + userId).retrieve();

		String responseBodyUser = responseUser.bodyToMono(String.class).block();

		Map<String, Object> userMap = Util.convertToObject(responseBodyUser);

		return userMap;
	}

	@Async
	public Map<String, Object> getProductMap(long productId) {
		WebClient.ResponseSpec responseProduct = WebClient.create().get().uri("http://localhost:8083/api/product/" + productId).retrieve();

		String responseBodyProduct = responseProduct.bodyToMono(String.class).block();

		Map<String, Object> productMap = Util.convertToObject(responseBodyProduct);

		return productMap;
	}
}
