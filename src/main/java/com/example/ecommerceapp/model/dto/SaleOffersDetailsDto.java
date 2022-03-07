package com.example.ecommerceapp.model.dto;

import com.example.ecommerceapp.model.entities.Item;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SaleOffersDetailsDto {

    private String name;
    private String description;
    private boolean isNew;
    private Double price;
    private Long quantity;
    private String status;
    private List<String> shipmentNames;
    private List<String> paymentNames;
    private Item item;
}
