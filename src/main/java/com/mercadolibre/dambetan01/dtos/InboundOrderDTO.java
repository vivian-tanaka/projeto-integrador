package com.mercadolibre.dambetan01.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InboundOrderDTO {

    @NotNull(message = "OrderNumber is required")
    private Long orderNumber;

    @NotBlank(message = "OrderDate is required")
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate orderDate;

    @Valid
    private SectionDTO section;

    @Valid
    private List<BatchItemDTO> batchStock = new ArrayList<>();

}
