package com.mercadolibre.projetointegrador.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewProductDTO {

    private Long seller_id;
    private String section_code;
    private String name;
    private String description;
    private Double price;
    private Double rating;

}