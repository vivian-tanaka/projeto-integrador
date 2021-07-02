package com.mercadolibre.dambetan01.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.index.qual.Positive;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchItemDTO {

    @NotNull(message = "batchNumber is required")
    @Positive
    private Long batchNumber;

    @NotNull(message = "productId is required")
    @Positive
    private Long productId;

    @NotNull( message = "currentTemperature is required")
    private double currentTemperature;

    @NotNull(message = "minTemperature is required")
    private double minTemperature;

    @NotNull(message = "maxTemperature is required")
    private double maxTemperature;

    @NotNull(message = "initialQuantity is required")
    private int initialQuantity;

    @NotNull(message = "currentQuantity is required")
    private int currentQuantity;

    @NotBlank(message = "manufacturingDate is required")
    private LocalDate manufacturingDate;

    @NotBlank(message = "manufacturingTime is required")
    private LocalDateTime manufacturingTime;

    @NotBlank(message = "dueDate is required")
    private LocalDate dueDate;
}
