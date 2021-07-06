package com.mercadolibre.projetointegrador.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolibre.projetointegrador.dtos.BatchDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InboundOrderResponseDTO {

    @JsonProperty("orderNumber")
    private Long id;

    @Valid
    private List<BatchDTO> batchStock = new ArrayList<>();

}
