package com.ecommerce.delivery.infrastructure.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecommerce.delivery.infrastructure.IDeliveryConsumer;
import com.ecommerce.delivery.service.IDeliveryService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DeliveryConsumer implements IDeliveryConsumer
{

	@Autowired
	private IDeliveryService deliveryService;
	
	@RabbitListener(queues = "queue_delivery")
	public void handleMessage(String order) {
		
		log.info("CONSUMER mensagem recebida");
		
		deliveryService.sendOrderToTransport(order);
		
	}

}
