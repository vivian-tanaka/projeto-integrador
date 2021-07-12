package com.mercadolibre.projetointegrador.repository;

import com.mercadolibre.projetointegrador.model.ExpiredBatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpiredBatchRepository extends JpaRepository<ExpiredBatch, Long> {
}
