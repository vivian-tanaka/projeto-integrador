package com.mercadolibre.projetointegrador.service.crud.impl;

import com.mercadolibre.projetointegrador.exceptions.NotFoundException;
import com.mercadolibre.projetointegrador.model.Buyer;
import com.mercadolibre.projetointegrador.repository.BuyerRepository;
import com.mercadolibre.projetointegrador.service.crud.ICRUD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BuyerServiceImpl implements ICRUD<Buyer> {

    private final BuyerRepository repository;

    @Override
    public Buyer create(Buyer buyer) {
        return null;
    }

    @Override
    public Buyer update(Buyer buyer) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Buyer findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Buyer with id " + id + " not found"));
    }

    @Override
    public List<Buyer> findAll() {
        return null;
    }
}
