package com.mercadolibre.projetointegrador.repository;

import com.mercadolibre.projetointegrador.model.InboundOrder;
import com.mercadolibre.projetointegrador.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InboundOrderRepository extends JpaRepository<InboundOrder, Long> {

    List<InboundOrder> findAllBySection_Warehouse_Id(Long warehouseId);
}
