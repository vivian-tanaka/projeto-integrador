package com.mercadolibre.projetointegrador.service.crud.impl;

import com.mercadolibre.projetointegrador.dtos.BatchDTO;
import com.mercadolibre.projetointegrador.exceptions.NotFoundException;
import com.mercadolibre.projetointegrador.model.Batch;
import com.mercadolibre.projetointegrador.model.Product;
import com.mercadolibre.projetointegrador.repository.BatchRepository;
import com.mercadolibre.projetointegrador.service.crud.ICRUD;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BatchServiceImpl implements ICRUD<Batch> {

    private final ModelMapper modelMapper;
    private final ProductServiceImpl productService;
    private final BatchRepository batchRepository;

    @Override
    public Batch create(Batch batch) {
        return batchRepository.save(batch);
    }

    @Override
    public Batch update(Batch batch) {
        Batch foundBatch = findById(batch.getId());
        batch.setId(foundBatch.getId());
        return create(batch);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Batch findById(Long id) {
        return batchRepository.findById(id).orElseThrow(() -> new NotFoundException("Batch with id " + id + " not found"));
    }

    @Override
    public List<Batch> findAll() {
        return null;
    }

    public List<Batch> create(List<BatchDTO> batchDTOS) {

        List<Batch> batches = new ArrayList<>();

        for (BatchDTO batch : batchDTOS) {
            Product product = productService.findById(batch.getProductId());
            Batch batchItem = modelMapper.map(batch, Batch.class);
            batchItem.setProduct(product);
            batches.add(batchItem);
        }

        return batches;
    }

    public List<Batch> update(List<BatchDTO> batchDTOS) {

        List<Batch> batches = new ArrayList<>();

        for (BatchDTO batch : batchDTOS) {
            Product product = productService.findById(batch.getProductId());
            Batch foundBatch = findById(batch.getBatchNumber());
            batch.setBatchNumber(foundBatch.getId());
            Batch batchItem = modelMapper.map(batch, Batch.class);
            batchItem.setProduct(product);
            batches.add(batchItem);
        }

        return batches;
    }
}
