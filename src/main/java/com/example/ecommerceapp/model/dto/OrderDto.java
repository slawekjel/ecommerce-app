package com.example.ecommerceapp.model.dto;

import com.example.ecommerceapp.model.PaymentDetails;
import com.example.ecommerceapp.model.ShipmentDetails;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDto {

    private Long orderId;
    private Long saleId;
    private Long quantity;
    private Double pricePerItem;
    private Double finalPrice;
    private PaymentDetails paymentDetails;
    private ShipmentDetails shipmentDetails;
}
