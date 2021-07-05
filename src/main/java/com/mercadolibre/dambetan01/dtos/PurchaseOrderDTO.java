package com.mercadolibre.dambetan01.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseOrderDTO {

    private LocalDate date;
    private Long buyerId;
    private OrderStatusDTO orderStatus;
    private ProductDTO products;
}
