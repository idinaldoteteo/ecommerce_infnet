package com.ecommerce.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.delivery.domain.Delivery;

public interface IDeliveryRepository extends JpaRepository<Delivery, Long>{

}
