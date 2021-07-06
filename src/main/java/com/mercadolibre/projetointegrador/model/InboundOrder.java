package com.mercadolibre.projetointegrador.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InboundOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

//    @OneToMany(mappedBy = "inboundOrder", fetch = FetchType.EAGER)
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Batch> batchStock = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private Supervisor supervisor;
}
