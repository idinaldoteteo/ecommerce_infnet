package com.ecommerce.delivery.infrastructure;

public interface IDeliveryConsumer {
	void handleMessage(String notification);
}
