package com.ecommerce.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.order.domain.Order;

public interface IOrderRepository  extends JpaRepository<Order, Long>{

}
