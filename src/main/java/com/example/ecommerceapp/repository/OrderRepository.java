package com.example.ecommerceapp.repository;

import com.example.ecommerceapp.model.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
