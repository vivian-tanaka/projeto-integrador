package com.mercadolibre.projetointegrador.repository;

import com.mercadolibre.projetointegrador.model.InboundOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InboundOrderRepository extends JpaRepository<InboundOrder, Long> {
}
