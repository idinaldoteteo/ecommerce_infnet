package com.ecommerce.delivery.service;

import java.util.Map;

public interface IDeliveryService {

	Map<String, Object> convertToObject(String jsonS);
	
	void sendOrderToTransport(String object);
	
	void ConfirmationOrder(String confirmation);

	void sendMessageBroker(String order);

}
