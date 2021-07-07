package com.mercadolibre.projetointegrador.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="section_code")
    private String sectionCode;

    @OneToMany(mappedBy = "section")
    private List<InboundOrder> inboundOrders;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @OneToMany(mappedBy = "section")
    private List<Product> products;

    private double minTemperature;
    private double maxTemperature;

}
