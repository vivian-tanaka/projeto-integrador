package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.exceptions.NotFoundException;
import com.mercadolibre.dambetan01.model.Buyer;
import com.mercadolibre.dambetan01.repository.BuyerRepository;
import com.mercadolibre.dambetan01.service.BuyerService;
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
