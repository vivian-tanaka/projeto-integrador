package com.mercadolibre.projetointegrador.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mercadolibre.projetointegrador.model.Batch;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WarehouseStockDTO {

    private Long warehouseCode;

    @JsonIgnore
    private List<Batch> batchstock;

    public Integer getTotalQuantity(){
        return batchstock
                .stream()
                .mapToInt(batch -> batch.getCurrentQuantity())
                .sum();
    }
}
