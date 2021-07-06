package com.mercadolibre.projetointegrador.repository;

import com.mercadolibre.projetointegrador.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
}
