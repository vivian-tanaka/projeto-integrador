package com.mercadolibre.projetointegrador.dtos.response;

import com.mercadolibre.projetointegrador.dtos.CountryHouseDTO;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
public class CountryHouseResponseDTO {
    private String message;
    private CountryHouseDTO countryHouseDTO;
}
