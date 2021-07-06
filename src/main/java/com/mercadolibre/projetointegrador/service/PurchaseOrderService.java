package com.mercadolibre.projetointegrador.service;

import com.mercadolibre.projetointegrador.dtos.NewPurchaseOrderDTO;
import com.mercadolibre.projetointegrador.model.Product;

import java.util.List;

public interface PurchaseOrderService {

    List<Product> getProducts(Long id);

    double insertAndCalculatePurchaseOrder(NewPurchaseOrderDTO purchaseOrderDTO);
}
