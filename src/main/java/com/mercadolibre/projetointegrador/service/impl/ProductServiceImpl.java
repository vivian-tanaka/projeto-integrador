package com.mercadolibre.projetointegrador.service.impl;

import com.mercadolibre.projetointegrador.dtos.BatchDTO;
import com.mercadolibre.projetointegrador.exceptions.NotFoundException;
import com.mercadolibre.projetointegrador.model.Product;
import com.mercadolibre.projetointegrador.repository.ProductRepository;
import com.mercadolibre.projetointegrador.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProduct(BatchDTO item) {
        return productRepository.findById(item.getProductId())
                .orElseThrow(() -> new NotFoundException("Produto de id: "+item.getProductId()+" não encontrado"));
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
