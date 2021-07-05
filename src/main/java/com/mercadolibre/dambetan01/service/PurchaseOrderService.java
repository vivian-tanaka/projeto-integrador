package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.dtos.NewPurchaseOrderDTO;
import com.mercadolibre.dambetan01.model.Product;

import java.util.List;

public interface PurchaseOrderService {

    List<Product> getProducts(Long id);

    double insertAndCalculatePurchaseOrder(NewPurchaseOrderDTO purchaseOrderDTO);
}
