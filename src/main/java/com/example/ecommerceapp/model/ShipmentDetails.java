package com.example.ecommerceapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShipmentDetails {

    private String companyName;
    private String methodName;
    private Double price;
    private String trackingUrl;
    private LocalDate deliveryDate;
}
