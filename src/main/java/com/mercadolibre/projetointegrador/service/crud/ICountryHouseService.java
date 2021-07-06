package com.mercadolibre.projetointegrador.service.crud;

import com.mercadolibre.projetointegrador.dtos.CountryHouseDTO;

public interface ICountryHouseService extends ICRUD<CountryHouseDTO>{
    CountryHouseDTO findByCountry(String country);
}
