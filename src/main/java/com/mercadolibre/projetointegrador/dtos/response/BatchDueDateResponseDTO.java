package com.mercadolibre.projetointegrador.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchDueDateResponseDTO {
    private Long batchNumber;
    private Long productId;
    private String dueDate;
    private int quantity;
}
