package com.example.ecommerceapp.model.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ORDERS")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order extends BaseEntity {

    private Long quantity;
    private Double pricePerItem;
    private Double finalPrice;
    private String shipmentMethod;
    private String paymentMethod;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;

    @ManyToOne
    @JoinColumn(name = "saleOffer_id")
    private SaleOffer saleOffer;
}
