package com.ecommerce.notification.infrastructure;

public interface INotificationConsumer {
	void handleMessage(String notification);
}
