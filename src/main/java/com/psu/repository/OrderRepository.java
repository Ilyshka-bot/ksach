package com.psu.repository;

import com.psu.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderById(Long id);
}
