package com.mercadolibre.dambetan01.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    @ManyToMany
    @JoinTable(name="purchase_order_products",
            joinColumns={@JoinColumn(name="product_id")},
            inverseJoinColumns={@JoinColumn(name="purchase_order_id")})
    private List<Product> products = new ArrayList<>();
}
