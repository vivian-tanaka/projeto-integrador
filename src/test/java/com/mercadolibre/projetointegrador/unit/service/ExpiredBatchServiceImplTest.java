package com.mercadolibre.projetointegrador.unit.service;

import com.mercadolibre.projetointegrador.model.Batch;
import com.mercadolibre.projetointegrador.service.impl.ExpiredBatchServiceImpl;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExpiredBatchServiceImplTest {

    @Mock
    private ExpiredBatchServiceImpl expiredBatchService;

    @Test
    public void test() {
        List<Batch> batches = mockBatchList();
        when(expiredBatchService.removeExpiredBatches()).thenReturn(batches);
        assertEquals(batches, expiredBatchService.removeExpiredBatches());
    }

    private List<Batch> mockBatchList(){
        List<Batch> batchList = Lists.newArrayList();
        for(int i = 0; i < 3; i++) {
            batchList.add(Batch.builder()
                    .id(1L)
                    .currentQuantity(10)
                    .currentTemperature(10)
                    .initialQuantity(10)
                    .dueDate(LocalDate.now())
                    .manufacturingDate(LocalDate.of(2021, 6, 6))
                    .manufacturingTime(LocalDateTime.of(2021, 6, 6, 6, 6))
                    .minTemperature(-10)
                    .maxTemperature(10)
                    .build());
        }
        return batchList;
    }
}
