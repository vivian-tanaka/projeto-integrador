package com.mercadolibre.projetointegrador.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Builder
public class SimpleBatchResponseDTO {

    @JsonProperty("batchNumber")
    private Long id;

    private int currentQuantity;

    private LocalDate dueDate;
}
