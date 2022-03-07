package com.example.ecommerceapp.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ITEMS")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item extends BaseEntity {

    private String name;
    private String description;
    private String producer;
    private String category;

    @OneToOne(mappedBy = "item")
    @JsonBackReference
    private SaleOffer saleOffer;
}
