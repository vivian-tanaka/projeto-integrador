package com.mercadolibre.projetointegrador.model;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name="id")
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee extends User{

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;
}
