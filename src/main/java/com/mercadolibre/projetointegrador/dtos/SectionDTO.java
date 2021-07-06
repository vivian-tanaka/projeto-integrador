package com.mercadolibre.projetointegrador.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("sectionCode")
    private String id;

    @NotNull(message = "warehouseCode é obrigatório")
    private Long warehouseCode;
}
