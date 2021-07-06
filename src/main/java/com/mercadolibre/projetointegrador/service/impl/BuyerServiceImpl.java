package com.mercadolibre.projetointegrador.service.impl;

import com.mercadolibre.projetointegrador.exceptions.NotFoundException;
import com.mercadolibre.projetointegrador.model.Buyer;
import com.mercadolibre.projetointegrador.repository.BuyerRepository;
import com.mercadolibre.projetointegrador.service.BuyerService;
import org.springframework.stereotype.Service;

@Service
public class BuyerServiceImpl implements BuyerService {

    private final BuyerRepository repository;

    public BuyerServiceImpl(BuyerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Buyer getBuyer(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Buyer with id " + id + " not found"));
    }
}
