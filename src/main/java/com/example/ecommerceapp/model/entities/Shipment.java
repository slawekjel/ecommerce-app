package com.example.ecommerceapp.model.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "SHIPMENTS")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Shipment extends BaseEntity {

    private String companyName;
    private String methodName;
    private Double price;
    private String trackingUrl;
    private LocalDate deliveryDate;

    @OneToOne(mappedBy = "shipment")
    private Order order;
}
