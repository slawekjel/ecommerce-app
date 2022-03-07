package com.example.ecommerceapp.repository;

import com.example.ecommerceapp.model.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}