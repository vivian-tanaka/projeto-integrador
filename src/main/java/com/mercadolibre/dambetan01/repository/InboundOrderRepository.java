package com.mercadolibre.dambetan01.repository;

import com.mercadolibre.dambetan01.model.BatchItem;
import com.mercadolibre.dambetan01.model.InboundOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InboundOrderRepository extends JpaRepository<InboundOrder, Long> {
}
