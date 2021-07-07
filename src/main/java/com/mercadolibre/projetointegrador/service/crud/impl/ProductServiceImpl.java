package com.mercadolibre.projetointegrador.service.crud.impl;

import com.mercadolibre.projetointegrador.exceptions.NotFoundException;
import com.mercadolibre.projetointegrador.model.Product;
import com.mercadolibre.projetointegrador.model.Seller;
import com.mercadolibre.projetointegrador.repository.ProductRepository;
import com.mercadolibre.projetointegrador.repository.SellerRepository;
import com.mercadolibre.projetointegrador.service.crud.ICRUD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ICRUD<Product> {

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        productRepository.findById(product.getId());
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.findById(id);
        productRepository.deleteById(id);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto de id: " + id + " não encontrado"));
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findAllBySellerName(String name){
        return productRepository.findAllBySeller(sellerRepository.findSellerByName(name));
    }

    public Product findByName(String name){
        return productRepository.findByName(name);
    }

}
