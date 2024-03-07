package com.ecommerce.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.notification.domain.Notification;
import com.ecommerce.notification.service.INotificationService;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

	@Autowired
	private INotificationService notificationService;
    
	@PostMapping()
	public ResponseEntity<Void> create(@RequestBody Notification notification) {

		notificationService.sendMessageBroker(notification);

		return ResponseEntity.ok().build();
	}

	@PostMapping("/email")
	public ResponseEntity<Void> email(@RequestBody Notification notification) {

		notificationService.sendEmail(notification);

		return ResponseEntity.ok().build();
	}
}
