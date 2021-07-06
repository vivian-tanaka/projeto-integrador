package com.mercadolibre.projetointegrador.dtos.response;

import com.mercadolibre.projetointegrador.dtos.BatchDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InboundOrderResponseDTO {

    private List<BatchDTO> batchStock = new ArrayList<>();
}
