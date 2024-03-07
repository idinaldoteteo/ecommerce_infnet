package com.ecommerce.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.notification.domain.Notification;

public interface INotificationRepository extends JpaRepository<Notification, Long> {

}
