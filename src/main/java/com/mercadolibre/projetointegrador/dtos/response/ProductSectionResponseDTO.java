package com.mercadolibre.projetointegrador.dtos.response;

import com.mercadolibre.projetointegrador.dtos.SectionDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSectionResponseDTO {

    private SectionDTO section;

    private Long productId;

    private List<SimpleBatchResponseDTO> batchStock = new ArrayList<>();

}
