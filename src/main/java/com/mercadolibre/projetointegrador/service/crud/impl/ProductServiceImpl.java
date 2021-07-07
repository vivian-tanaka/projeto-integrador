package com.mercadolibre.projetointegrador.service.crud.impl;

import com.mercadolibre.projetointegrador.dtos.NewProductDTO;
import com.mercadolibre.projetointegrador.exceptions.NotFoundException;
import com.mercadolibre.projetointegrador.model.Product;
import com.mercadolibre.projetointegrador.model.Seller;
import com.mercadolibre.projetointegrador.repository.ProductRepository;
import com.mercadolibre.projetointegrador.repository.SectionRepository;
import com.mercadolibre.projetointegrador.repository.SellerRepository;
import com.mercadolibre.projetointegrador.service.crud.ICRUD;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ICRUD<Product> {

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    private final SectionRepository sectionRepository;

    public Product create(NewProductDTO productDTO) {
        Product product = Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .rating(productDTO.getRating())
                .price(productDTO.getPrice())
                .build();

        product.setSeller(sellerRepository.findById(productDTO.getSeller_id())
                .orElseThrow(() -> new NotFoundException("No existing seller with id "+productDTO.getSeller_id())));
        product.setSection(sectionRepository.findSectionsBySectionCode(productDTO.getSection_code()).stream()
                .findFirst().orElseThrow(() -> new NotFoundException("No existing section with code " + productDTO.getSection_code())));

        return productRepository.save(product);
    }

    @Override
    public Product create(Product product) {
        return null;
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
