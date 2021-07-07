package com.mercadolibre.projetointegrador.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    @JsonIgnore
    private Seller seller;

    @ManyToOne
    @JoinColumn(name = "section_code")
    @JsonIgnore
    private Section section;

    private String name;
    private String description;
    private double price;
    private double rating;
}
