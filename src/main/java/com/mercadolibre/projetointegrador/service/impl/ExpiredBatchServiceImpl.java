package com.mercadolibre.projetointegrador.service.impl;

import com.mercadolibre.projetointegrador.model.Batch;
import com.mercadolibre.projetointegrador.model.ExpiredBatch;
import com.mercadolibre.projetointegrador.repository.BatchRepository;
import com.mercadolibre.projetointegrador.repository.ExpiredBatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ExpiredBatchServiceImpl {

    private final ExpiredBatchRepository expiredBatchRepository;
    private final BatchRepository batchRepository;

    public List<Batch> removeExpiredBatches() {
        List<Batch> expiredBatches = getExpiredBatches();
        batchRepository.saveAll(expiredBatches);
        saveExpiredBatches(expiredBatches);
        return expiredBatches;
    }

    private List<Batch> getExpiredBatches() {
        List<Batch> expiredBatches = batchRepository.findAll().stream()
                .filter(batch -> batch.getDueDate().equals(LocalDate.now())).collect(Collectors.toList());
        expiredBatches.forEach(batch -> batch.setBatchExpired(true));
        return expiredBatches;
    }

    private void saveExpiredBatches(List<Batch> batches) {
        List<ExpiredBatch> expiredBatches = batches.stream().map(ExpiredBatch::new).collect(Collectors.toList());
        expiredBatchRepository.saveAll(expiredBatches);
    }
}
