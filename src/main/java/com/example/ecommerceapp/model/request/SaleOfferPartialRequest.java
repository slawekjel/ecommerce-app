package com.example.ecommerceapp.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleOfferPartialRequest {

    private String name;
    private String description;
    private boolean isNew;
    private Double price;
    private Long quantity;
    private String status;
    private List<String> shipmentNames;
    private List<String> paymentNames;
}