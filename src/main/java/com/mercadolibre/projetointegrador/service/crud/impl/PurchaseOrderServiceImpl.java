package com.mercadolibre.projetointegrador.service.crud.impl;

import com.mercadolibre.projetointegrador.dtos.NewPurchaseOrderDTO;
import com.mercadolibre.projetointegrador.dtos.ProductDTO;
import com.mercadolibre.projetointegrador.dtos.PurchaseOrderDTO;
import com.mercadolibre.projetointegrador.exceptions.NotFoundException;
import com.mercadolibre.projetointegrador.model.*;
import com.mercadolibre.projetointegrador.repository.PurchaseOrderRepository;
import com.mercadolibre.projetointegrador.service.crud.ICRUD;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PurchaseOrderServiceImpl implements ICRUD<PurchaseOrder> {

    private final ModelMapper mapper;
    private final PurchaseOrderRepository repository;
    private final BuyerServiceImpl buyerService;
    private final ProductServiceImpl productService;
    private final BatchServiceImpl batchService;
    private final InboundOrderServiceImpl inboundOrderService;
    
    public List<Product> getProducts(Long id) {
        PurchaseOrder purchaseOrder = findById(id);
        return purchaseOrder.getProducts();
    }

    public double insertAndCalculatePurchaseOrder(NewPurchaseOrderDTO purchaseOrderDTO) {
        PurchaseOrder purchaseOrder = buildPurchaseOrder(purchaseOrderDTO);
        List<Batch> batches = new ArrayList<>();
        for (ProductDTO p : purchaseOrderDTO.getPurchaseOrder().getProducts()) {
            Batch batch = batchService.findBatchContainingValidProduct(p.getProductId(), p.getQuantity());
            batchService.updateCurrentQuantity(batch, p.getQuantity());
            batches.add(batch);
        }
        batchService.saveAll(batches);
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

    public Set<Product> getSectorProducts(String category) {
        List<InboundOrder> filteredInboundOrders = inboundOrderService.findAll()
                .stream().filter(inboundOrder -> inboundOrder.getSection()
                        .getSectionCode().equals(category)).collect(Collectors.toList());

        Set<Product> productSet = new LinkedHashSet<>();

        for(InboundOrder io : filteredInboundOrders){
            for(Batch batch : io.getBatchStock()){
                productSet.add(batch.getProduct());
            }
        }
        if(productSet.isEmpty()){
            throw new NotFoundException("No products found in the section with cod " +category);
        }
        return productSet;
    }

    public PurchaseOrder updatePurchaseOrder(PurchaseOrderDTO purchaseOrderDTO, Long id) {
        PurchaseOrder purchaseOrder = repository.findById(id).orElseThrow(() -> new NotFoundException("No Purchase Order with id "+id));
        return null;
    }
}
