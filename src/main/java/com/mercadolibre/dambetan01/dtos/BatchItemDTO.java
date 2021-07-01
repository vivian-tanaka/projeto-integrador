package com.mercadolibre.dambetan01.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchItemDTO {

    private Long batchNumber;
    private Long productId;
    private double currentTemperature;
    private double minTemperature;
    private double maxTemperature;
    private int initialQuantity;
    private int currentQuantity;
    private String manufacturingDate;
    private String manufacturingTime;
    private String dueDate;
}
