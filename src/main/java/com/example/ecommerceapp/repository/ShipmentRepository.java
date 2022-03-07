package com.example.ecommerceapp.repository;

import com.example.ecommerceapp.model.entities.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}