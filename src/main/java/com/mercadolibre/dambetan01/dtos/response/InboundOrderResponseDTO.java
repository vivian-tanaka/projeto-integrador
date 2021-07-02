package com.mercadolibre.dambetan01.dtos.response;

import com.mercadolibre.dambetan01.dtos.BatchItemDTO;
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

    private List<BatchItemDTO> batchStock = new ArrayList<>();
}
