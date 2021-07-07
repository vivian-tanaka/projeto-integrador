package com.mercadolibre.projetointegrador.service.crud.impl;

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
        return purchaseOrder.getProducts().stream().map(PurchaseProduct::getProduct).collect(Collectors.toList());
    }

    public double insertAndCalculatePurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) {
        PurchaseOrder purchaseOrder = buildPurchaseOrder(purchaseOrderDTO);
        List<Batch> batches = new ArrayList<>();
        for (ProductDTO p : purchaseOrderDTO.getProducts()) {
            Batch batch = batchService.findBatchContainingValidProduct(p.getProductId(), p.getQuantity());
            batchService.updateCurrentQuantity(batch, p.getQuantity());
            batches.add(batch);
        }
        batchService.saveAll(batches);
        repository.save(purchaseOrder);
        return calculateTotalOrderValue(purchaseOrderDTO);
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

    public double updatePurchaseOrder(PurchaseOrderDTO purchaseOrderDTO, Long id) {
        PurchaseOrder purchaseOrder = repository.findById(id).orElseThrow(() -> new NotFoundException("No Purchase Order with id "+id));
        List<PurchaseProduct> purchaseProducts = purchaseOrder.getProducts();

        for(PurchaseProduct p : purchaseProducts){
            Batch matchingBatch = batchService.findMatchingBatch(p.getProduct());
            batchService.returnProducts(matchingBatch, p.getQuantity());
            batchService.save(matchingBatch);
        }

        purchaseOrderDTO.setId(id);

        return insertAndCalculatePurchaseOrder(purchaseOrderDTO);
    }

    private PurchaseOrder buildPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) {
        Buyer buyer = buyerService.findById(purchaseOrderDTO.getBuyerId());

        PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                .id(purchaseOrderDTO.getId())
                .buyer(buyer)
                .build();

        List<PurchaseProduct> products = purchaseOrderDTO.getProducts().stream()
                .map(
                        product -> new PurchaseProduct(null, purchaseOrder, productService.findById(product.getProductId()), product.getQuantity())
                ).collect(Collectors.toList());

        purchaseOrder.setProducts(products);

        return purchaseOrder;
    }
    private double calculateTotalOrderValue(PurchaseOrderDTO purchaseOrderDTO) {
        return purchaseOrderDTO.getProducts().stream()
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
