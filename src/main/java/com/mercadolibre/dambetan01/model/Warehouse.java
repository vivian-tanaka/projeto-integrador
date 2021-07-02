package com.mercadolibre.dambetan01.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "warehouse")
    List<Section> sections = new ArrayList<>();

    @OneToMany(mappedBy = "warehouse")
    List<Employee> employees;
}
