package com.mercadolibre.projetointegrador.unit.controller;

import com.mercadolibre.projetointegrador.controller.ExpiredBatchController;
import com.mercadolibre.projetointegrador.model.Batch;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExpiredBatchControllerTest {

    @Mock
    private ExpiredBatchController expiredBatchController;

    @Test
    public void test() {
        List<Batch> batches = mockBatchList();
        when(expiredBatchController.checkForDueDate()).thenReturn(batches);
        assertEquals(batches, expiredBatchController.checkForDueDate());
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
