package com.ecommerce.delivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.delivery.service.IDeliveryService;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {
	
	@Autowired
	private IDeliveryService deliveryService;
	
	@PostMapping("/notification")
	public ResponseEntity<Void> notificationTransport(@RequestBody String order) {

		//Order é responsável por enviar enviar essa notifcação
		deliveryService.sendMessageBroker(order);

		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/confirmation")
	public ResponseEntity<Void> confirmationTransport(@RequestBody String confirmation) {

		deliveryService.ConfirmationOrder(confirmation);	

		return ResponseEntity.ok().build();
	}
	
}
