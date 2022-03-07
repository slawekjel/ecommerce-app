package com.example.ecommerceapp.model.request;

import lombok.Data;

import java.util.List;

@Data
public class SaleOfferRequest {

    private String name;
    private String description;
    private boolean isNew;
    private Double price;
    private Long quantity;
    private String status;
    private List<String> shipmentNames;
    private List<String> paymentNames;

    private String itemName;
    private String itemDescription;
    private String itemProducer;
    private String itemCategory;
}
