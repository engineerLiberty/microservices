package com.engineerLiberty.orderservice.repository;

import com.engineerLiberty.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
