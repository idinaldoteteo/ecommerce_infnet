package com.ecommerce.notification.infrastructure.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class NotificationWebClient {

	@Value("${service.user.server.port}")
	private String userServerPort;
	
	@Bean
	@Async
	WebClient webClient(WebClient.Builder builder) {
		StringBuilder uri = new StringBuilder();
		uri.append("http://localhost:").append(userServerPort).append("/api");
		
		return builder.baseUrl(uri.toString()).build();
	}
}
