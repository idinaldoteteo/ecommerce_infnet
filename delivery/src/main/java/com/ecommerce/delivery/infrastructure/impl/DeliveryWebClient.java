package com.ecommerce.delivery.infrastructure.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class DeliveryWebClient {

	@Value("${microservice.order.server.port}")
	private String orderServerPort;
	
	@Bean
	@Async
	WebClient webClientOrder(WebClient.Builder builder) {
		StringBuilder uri = new StringBuilder();
		uri.append("http://localhost:").append(orderServerPort).append("/api");
		
		return builder.baseUrl(uri.toString()).build();
	}
}
