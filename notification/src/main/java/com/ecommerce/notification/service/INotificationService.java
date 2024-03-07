package com.ecommerce.notification.service;

import java.util.Map;

import com.ecommerce.notification.domain.Notification;

public interface INotificationService
{
	void sendEmail(String body, String email, String subject);

	Map<String, Object> convertToObject(String jsonS);

	void sendEmail(String notification);

	void sendMessageBroker(Notification notification);

	void sendEmail(Notification notification);
}
