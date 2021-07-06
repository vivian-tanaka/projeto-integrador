package com.mercadolibre.projetointegrador.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchDTO {

    @NotNull(message = "batchNumber é obrigatório")
    @Positive
    private Long batchNumber;

    @NotNull(message = "productId é obrigatório")
    @Positive
    private Long productId;

    @NotNull( message = "currentTemperature é obrigatório")
    private double currentTemperature;

    @NotNull(message = "minTemperature é obrigatório")
    private double minTemperature;

    @NotNull(message = "maxTemperature é obrigatório")
    private double maxTemperature;

    @NotNull(message = "initialQuantity é obrigatório")
    @PositiveOrZero
    private int initialQuantity;

    @NotNull(message = "currentQuantity é obrigatório")
    @PositiveOrZero
    private int currentQuantity;

    @NotNull(message = "manufacturingDate é obrigatório")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate manufacturingDate;

    @NotNull(message = "manufacturingTime é obrigatório")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime manufacturingTime;

    @NotNull(message = "dueDate é obrigatório")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dueDate;
}
