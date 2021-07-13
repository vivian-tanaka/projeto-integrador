package com.mercadolibre.projetointegrador.repository;

import com.mercadolibre.projetointegrador.model.CountryHouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryHouseRepository extends JpaRepository<CountryHouse, Long> {
    CountryHouse findByCountry(String country);
}
