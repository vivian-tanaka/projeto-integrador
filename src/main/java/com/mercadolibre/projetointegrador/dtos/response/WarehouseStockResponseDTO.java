package com.mercadolibre.projetointegrador.dtos.response;

import com.mercadolibre.projetointegrador.dtos.WarehouseStockDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WarehouseStockResponseDTO {

    private Long productId;

    private List<WarehouseStockDTO> warehouses;
}
