package com.mercadolibre.projetointegrador.controller;

import com.mercadolibre.projetointegrador.dtos.NewPurchaseOrderDTO;
import com.mercadolibre.projetointegrador.model.Product;
import com.mercadolibre.projetointegrador.service.crud.impl.PurchaseOrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/fresh-products/orders")
public class PurchaseOrderController {

    private final PurchaseOrderServiceImpl purchaseOrderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProducts(@PathVariable Long id){
        return purchaseOrderService.getProducts(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public double insertPurchaseOder(@Valid @RequestBody NewPurchaseOrderDTO purchaseOrderDTO){
        return purchaseOrderService.insertAndCalculatePurchaseOrder(purchaseOrderDTO);
    }
}