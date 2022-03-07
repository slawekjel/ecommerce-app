package com.example.ecommerceapp.model.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PAYMENTS")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment extends BaseEntity {

    private String method;
    private String provider;
    private Double sum;
    private String url;

    @OneToOne(mappedBy = "payment")
    private Order order;
}
