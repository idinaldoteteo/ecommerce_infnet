package com.ecommerce.order.controller;

import java.security.SecureRandom;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order.domain.Order;
import com.ecommerce.order.service.IOrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController extends GenericController<Order> {

	private IOrderService orderService;
	
	public OrderController(IOrderService service) {
		super(service);
		orderService = service;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Void> createOrder(@RequestBody Order order) {

		long number =  new SecureRandom().nextLong(10000l);
		order.getOrderItems().forEach(item -> item.setId(number));
		orderService.createOrder(order);

		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/confirmation_payment")
	public ResponseEntity<Void> confirmationPayment(@RequestBody String confirmation) {

		orderService.confirmationPayment(confirmation);

		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/confirmation_delivery")
	public ResponseEntity<Void> confirmationDelivery(@RequestBody String confirmation) {

		orderService.confirmationDelivery(confirmation);

		return ResponseEntity.ok().build();
	}
	
}
