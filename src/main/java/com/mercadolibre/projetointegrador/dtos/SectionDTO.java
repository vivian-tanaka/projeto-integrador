package com.mercadolibre.projetointegrador.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionDTO {

    @NotBlank(message = "sectionCode é obrigatório")
    private String sectionCode;

    @NotNull(message = "warehouseCode é obrigatório")
    private Long warehouseCode;
}
