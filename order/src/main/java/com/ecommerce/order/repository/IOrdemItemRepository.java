package com.ecommerce.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.order.domain.OrderItem;

public interface IOrdemItemRepository extends JpaRepository<OrderItem, Long> {

}
