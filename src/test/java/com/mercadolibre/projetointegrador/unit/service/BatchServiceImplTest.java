package com.mercadolibre.projetointegrador.unit.service;

import com.mercadolibre.projetointegrador.dtos.BatchDTO;
import com.mercadolibre.projetointegrador.model.Batch;
import com.mercadolibre.projetointegrador.service.crud.impl.BatchServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BatchServiceImplTest {

    @Mock
    private BatchServiceImpl batchService;

    @Test
    public void createTest() {
        Batch batch = mockBatch();
        when(batchService.create(batch)).thenReturn(batch);
        assertEquals(batch, batchService.create(batch));
    }

    @Test
    public void createListTest() {
        List<BatchDTO> batchDTOList = mockBatchDTOList();
        List<Batch> batchList = mockBatchList();
        when(batchService.create(batchDTOList)).thenReturn(batchList);
        assertEquals(batchList, batchService.create(batchDTOList));
    }

    @Test
    public void updateTest() {
        Batch batch = mockBatch();
        when(batchService.update(batch)).thenReturn(batch);
        assertEquals(batch, batchService.update(batch));
    }

    @Test
    public void updateListTest() {
        List<BatchDTO> batchDTOList = mockBatchDTOList();
        List<Batch> batchList = mockBatchList();
        when(batchService.update(batchDTOList)).thenReturn(batchList);
        assertEquals(batchList, batchService.update(batchDTOList));
    }

    @Test
    public void findByIdTest() {
        Batch batch = mockBatch();
        when(batchService.findById(1L)).thenReturn(batch);
        assertEquals(batch, batchService.findById(1L));
    }

    private Batch mockBatch(){
        return Batch.builder()
                .id(1L)
                .currentQuantity(10)
                .currentTemperature(10)
                .initialQuantity(10)
                .dueDate(LocalDate.now())
                .manufacturingDate(LocalDate.now())
                .manufacturingTime(LocalDateTime.now())
                .minTemperature(-10)
                .maxTemperature(10)
                .build();
    }

    private List<Batch> mockBatchList() {
        List<Batch> batchList = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            batchList.add(mockBatch());
        }
        return batchList;
    }

    private List<BatchDTO> mockBatchDTOList() {
        List<BatchDTO> batchDTOList = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            batchDTOList.add(
                BatchDTO.builder()
                    .batchNumber(1L)
                    .currentQuantity(10)
                    .currentTemperature(10)
                    .dueDate(LocalDate.now())
                    .initialQuantity(10)
                    .manufacturingDate(LocalDate.now())
                    .manufacturingTime(LocalDateTime.now())
                    .maxTemperature(20)
                    .minTemperature(10)
                    .productId(1L)
                    .build());
        }
        return batchDTOList;
    }
}
