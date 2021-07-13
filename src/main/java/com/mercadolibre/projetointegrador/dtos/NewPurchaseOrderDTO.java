package com.mercadolibre.projetointegrador.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewPurchaseOrderDTO {

    private PurchaseOrderDTO purchaseOrder;
}
