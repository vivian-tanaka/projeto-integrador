package com.mercadolibre.projetointegrador.service.crud.impl;

import com.mercadolibre.projetointegrador.exceptions.NotFoundException;
import com.mercadolibre.projetointegrador.model.Seller;
import com.mercadolibre.projetointegrador.repository.SellerRepository;
import com.mercadolibre.projetointegrador.service.crud.ICRUD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SellerServiceImpl implements ICRUD<Seller> {

    private final SellerRepository sellerRepository;

    @Override
    public Seller create(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public Seller update(Seller seller) {
        sellerRepository.findById(seller.getId());
        return sellerRepository.save(seller);
    }

    @Override
    public void delete(Long id) {
        //TODO Validate supervisor or seller itself
        sellerRepository.findById(id);
        sellerRepository.deleteById(id);
    }

    @Override
    public Seller findById(Long id) {
        //TODO Validate supervisor
        return sellerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto de id: " + id + " não encontrado"));
    }

    @Override
    public List<Seller> findAll() {
        //TODO Validate supervisor
        return sellerRepository.findAll();
    }

    public Seller findByName(String name) {
        //TODO Validate supervisor
        return sellerRepository.findSellerByName(name);
    }
}
