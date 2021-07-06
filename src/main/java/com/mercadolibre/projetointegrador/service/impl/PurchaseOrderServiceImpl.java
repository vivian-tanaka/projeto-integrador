package com.mercadolibre.projetointegrador.service.impl;

import com.mercadolibre.projetointegrador.dtos.NewPurchaseOrderDTO;
import com.mercadolibre.projetointegrador.exceptions.NotFoundException;
import com.mercadolibre.projetointegrador.model.Buyer;
import com.mercadolibre.projetointegrador.model.Product;
import com.mercadolibre.projetointegrador.model.PurchaseOrder;
import com.mercadolibre.projetointegrador.repository.PurchaseOrderRepository;
import com.mercadolibre.projetointegrador.service.BuyerService;
import com.mercadolibre.projetointegrador.service.ProductService;
import com.mercadolibre.projetointegrador.service.PurchaseOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private ModelMapper mapper;
    private final PurchaseOrderRepository repository;
    private final BuyerService buyerService;
    private final ProductService productService;

    public PurchaseOrderServiceImpl(PurchaseOrderRepository repository, BuyerService buyerService, ProductService productService) {
        this.repository = repository;
        this.buyerService = buyerService;
        this.productService = productService;
    }

    public PurchaseOrder getPurchaseOrder(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Order with id " + id));
    }
    
    public List<Product> getProducts(Long id) {
        PurchaseOrder purchaseOrder = getPurchaseOrder(id);
        return purchaseOrder.getProducts();
    }

    public double insertAndCalculatePurchaseOrder(NewPurchaseOrderDTO purchaseOrderDTO) {
        PurchaseOrder purchaseOrder = buildPurchaseOrder(purchaseOrderDTO);
        repository.save(purchaseOrder);
        return calculateTotalOrderValue(purchaseOrderDTO);
    }

    private PurchaseOrder buildPurchaseOrder(NewPurchaseOrderDTO newPurchaseOrderDTO) {
        Buyer buyer = buyerService.getBuyer(newPurchaseOrderDTO.getPurchaseOrder().getId());
        List<Product> products = newPurchaseOrderDTO.getPurchaseOrder().getProducts().stream()
                .map(product -> productService.getProduct(product.getProductId()))
                .collect(Collectors.toList());

        return PurchaseOrder.builder()
                .id(newPurchaseOrderDTO.getPurchaseOrder().getId())
                .products(products)
                .buyer(buyer)
                .build();
    }

    private double calculateTotalOrderValue(NewPurchaseOrderDTO newPurchaseOrderDTO) {
        return newPurchaseOrderDTO.getPurchaseOrder().getProducts().stream()
                .mapToDouble(product -> productService.getProduct(product.getProductId()).getPrice() * product.getQuantity()).sum();
    }
}
