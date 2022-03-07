package com.example.ecommerceapp.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SALE_OFFERS")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleOffer extends BaseEntity {

    private String name;
    private String description;
    private boolean isNew;
    private Double price;
    private Long quantity;
    private String status;

    @ElementCollection
    private List<String> shipmentNames;
    @ElementCollection
    private List<String> paymentNames;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "item_id")
    @JsonManagedReference
    private Item item;

    @OneToMany(mappedBy = "saleOffer")
    private List<Order> orders;
}
