package com.mercadolibre.dambetan01.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InboundOrderDTO {

    private Long orderNumber;
    private String orderDate;
    private SectionDTO section;
    private List<BatchItemDTO> batchStock = new ArrayList<>();

}
