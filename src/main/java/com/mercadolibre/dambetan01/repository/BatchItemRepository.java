package com.mercadolibre.dambetan01.repository;

import com.mercadolibre.dambetan01.model.BatchItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchItemRepository extends JpaRepository<BatchItem, Long> {
}
