package com.mercadolibre.projetointegrador.repository;

import com.mercadolibre.projetointegrador.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {
}
