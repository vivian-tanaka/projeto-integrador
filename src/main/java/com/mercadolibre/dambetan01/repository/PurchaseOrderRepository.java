package com.mercadolibre.dambetan01.repository;

import com.mercadolibre.dambetan01.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
}
