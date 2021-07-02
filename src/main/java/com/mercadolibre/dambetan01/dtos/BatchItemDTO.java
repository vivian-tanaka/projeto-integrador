package com.mercadolibre.dambetan01.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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

    @NotNull(message = "manufacturingDate is required")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate manufacturingDate;

    @NotNull(message = "manufacturingTime is required")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime manufacturingTime;

    @NotNull(message = "dueDate is required")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dueDate;
}
