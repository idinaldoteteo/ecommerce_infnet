package com.ecommerce.order.service;

import java.util.Map;

import com.ecommerce.order.domain.Order;

public interface IOrderService extends IGenericService<Order>{
	
	void createOrder(Order order);

	void confirmationPayment(String confirmation);

	void confirmationDelivery(String confirmation);

}
