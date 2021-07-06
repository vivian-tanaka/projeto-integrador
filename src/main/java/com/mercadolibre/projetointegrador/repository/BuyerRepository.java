package com.mercadolibre.projetointegrador.repository;

import com.mercadolibre.projetointegrador.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}
