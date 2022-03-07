package com.example.ecommerceapp.model.request;

import lombok.Data;

@Data
public class OrderRequest {

    private Long saleId;
    private Long quantity;
    private Double pricePerItem;
    private String shipmentMethod;
    private String paymentMethod;
}
