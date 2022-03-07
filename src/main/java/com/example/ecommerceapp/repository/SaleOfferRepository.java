package com.example.ecommerceapp.repository;

import com.example.ecommerceapp.model.entities.SaleOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleOfferRepository extends JpaRepository<SaleOffer, Long> {
}