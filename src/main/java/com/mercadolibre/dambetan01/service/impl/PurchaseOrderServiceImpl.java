package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.dtos.NewPurchaseOrderDTO;
import com.mercadolibre.dambetan01.exceptions.NotFoundException;
import com.mercadolibre.dambetan01.model.Buyer;
import com.mercadolibre.dambetan01.model.Product;
import com.mercadolibre.dambetan01.model.PurchaseOrder;
import com.mercadolibre.dambetan01.repository.PurchaseOrderRepository;
import com.mercadolibre.dambetan01.service.BuyerService;
import com.mercadolibre.dambetan01.service.ProductService;
import com.mercadolibre.dambetan01.service.PurchaseOrderService;
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
