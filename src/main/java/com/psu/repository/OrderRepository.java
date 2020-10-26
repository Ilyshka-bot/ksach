package com.psu.repository;

import com.psu.entity.Excursion;
import com.psu.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderById(Long id);
    List<Order> findAllByExcursion(Excursion exc);
}
