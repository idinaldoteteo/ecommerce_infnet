package com.ecommerce.notification.infrastructure.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ecommerce.notification.domain.Notification;
import com.ecommerce.notification.infrastructure.INotificationConsumer;
import com.ecommerce.notification.service.INotificationService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NotificationConsumer implements INotificationConsumer {

	@Autowired
	private INotificationService notificationService;
	
	@RabbitListener(queues = "queue_notification")
	public void handleMessage(String notification) {
		
		log.info("CONSUMER mensagem recebida");
		
		notificationService.sendEmail(notification);
		
	}

}
