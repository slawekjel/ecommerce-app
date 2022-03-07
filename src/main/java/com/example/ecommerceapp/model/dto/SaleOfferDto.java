package com.example.ecommerceapp.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SaleOfferDto {

    private Long id;
    private Long itemId;
    private String name;
    private String description;
    private boolean isNew;
    private Double price;
    private Long quantity;
    private String status;
    private List<String> shipmentNames;
    private List<String> paymentNames;
}
