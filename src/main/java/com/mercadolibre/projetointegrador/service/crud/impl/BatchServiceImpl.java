package com.mercadolibre.projetointegrador.service.crud.impl;

import com.mercadolibre.projetointegrador.dtos.BatchDTO;
import com.mercadolibre.projetointegrador.model.Batch;
import com.mercadolibre.projetointegrador.model.Product;
import com.mercadolibre.projetointegrador.service.crud.ICRUD;
import com.newrelic.api.agent.DatastoreParameters;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BatchServiceImpl implements ICRUD<Batch> {

    private final ProductServiceImpl productService;
    private final ModelMapper modelMapper;

    @Override
    public Batch create(Batch batch) {
        return null;
    }

    @Override
    public Batch update(Batch batch) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Batch findById(Long id) {
        return null;
    }

    @Override
    public List<Batch> findAll() {
        return null;
    }

    public List<Batch> buildBatch(List<BatchDTO> batchDTOS) {
        List<Batch> batches = new ArrayList<>();

        for (BatchDTO batch : batchDTOS) {
            Product product = productService.findById(batch.getProductId());
            Batch batchItem = modelMapper.map(batch, Batch.class);
            batchItem.setProduct(product);
            batches.add(batchItem);
        }

        return batches;
    }
}
