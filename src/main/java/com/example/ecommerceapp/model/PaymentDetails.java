package com.example.ecommerceapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDetails {

    private String method;
    private String provider;
    private Double sum;
    private String url;
}
