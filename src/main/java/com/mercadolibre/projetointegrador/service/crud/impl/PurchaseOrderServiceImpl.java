package com.mercadolibre.projetointegrador.service.crud.impl;

import com.mercadolibre.projetointegrador.dtos.NewPurchaseOrderDTO;
import com.mercadolibre.projetointegrador.exceptions.NotFoundException;
import com.mercadolibre.projetointegrador.model.Buyer;
import com.mercadolibre.projetointegrador.model.Product;
import com.mercadolibre.projetointegrador.model.PurchaseOrder;
import com.mercadolibre.projetointegrador.repository.PurchaseOrderRepository;
import com.mercadolibre.projetointegrador.service.crud.ICRUD;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PurchaseOrderServiceImpl implements ICRUD<PurchaseOrder> {

    private final ModelMapper mapper;
    private final PurchaseOrderRepository repository;
    private final BuyerServiceImpl buyerService;
    private final ProductServiceImpl productService;
    
    public List<Product> getProducts(Long id) {
        PurchaseOrder purchaseOrder = findById(id);
        return purchaseOrder.getProducts();
    }

    public double insertAndCalculatePurchaseOrder(NewPurchaseOrderDTO purchaseOrderDTO) {
        PurchaseOrder purchaseOrder = buildPurchaseOrder(purchaseOrderDTO);
        repository.save(purchaseOrder);
        return calculateTotalOrderValue(purchaseOrderDTO);
    }

    private PurchaseOrder buildPurchaseOrder(NewPurchaseOrderDTO newPurchaseOrderDTO) {
        Buyer buyer = buyerService.findById(newPurchaseOrderDTO.getPurchaseOrder().getId());
        List<Product> products = newPurchaseOrderDTO.getPurchaseOrder().getProducts().stream()
                .map(product -> productService.findById(product.getProductId()))
                .collect(Collectors.toList());

        return PurchaseOrder.builder()
                .id(newPurchaseOrderDTO.getPurchaseOrder().getId())
                .products(products)
                .buyer(buyer)
                .build();
    }
    private double calculateTotalOrderValue(NewPurchaseOrderDTO newPurchaseOrderDTO) {
        return newPurchaseOrderDTO.getPurchaseOrder().getProducts().stream()
                .mapToDouble(product -> productService.findById(product.getProductId()).getPrice() * product.getQuantity()).sum();
    }

    @Override
    public PurchaseOrder create(PurchaseOrder purchaseOrder) {
        return repository.save(purchaseOrder);
    }

    @Override
    public PurchaseOrder update(PurchaseOrder purchaseOrder) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public PurchaseOrder findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Order with id " + id));
    }

    @Override
    public List<PurchaseOrder> findAll() {
        return null;
    }
}
