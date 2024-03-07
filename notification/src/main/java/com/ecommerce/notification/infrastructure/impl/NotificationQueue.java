package com.ecommerce.notification.infrastructure.impl;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class NotificationQueue {

	@Value("${queue.name}")
	private String queue;
	
	@Value("${exchange.name}")
	private String exchange;
	
	@Value("${routing.key}")
	private String routingKey;

    @Bean
    Queue queue() {
		return new Queue(queue, true);	
	}
	
	@Bean
	TopicExchange exchange() {
		return new TopicExchange(exchange);
	}
	
	@Bean
	Binding binding() {
		return BindingBuilder
				.bind(queue())
				.to(exchange())
				.with(routingKey);
	}
}
