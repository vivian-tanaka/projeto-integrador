package com.mercadolibre.dambetan01.dtos;

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

    @NotBlank(message = "sectionCode is required")
    private String sectionCode;

    @NotNull(message = "warehouseCode is required")
    private Long warehouseCode;
}
