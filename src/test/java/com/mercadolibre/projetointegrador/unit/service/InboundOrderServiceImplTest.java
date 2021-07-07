package com.mercadolibre.projetointegrador.unit.service;

import com.mercadolibre.projetointegrador.dtos.BatchDTO;
import com.mercadolibre.projetointegrador.dtos.InboundOrderDTO;
import com.mercadolibre.projetointegrador.dtos.SectionDTO;
import com.mercadolibre.projetointegrador.dtos.response.InboundOrderResponseDTO;
import com.mercadolibre.projetointegrador.model.Batch;
import com.mercadolibre.projetointegrador.model.InboundOrder;
import com.mercadolibre.projetointegrador.model.Section;
import com.mercadolibre.projetointegrador.model.Supervisor;
import com.mercadolibre.projetointegrador.service.crud.impl.InboundOrderServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InboundOrderServiceImplTest {

    @Mock
    private InboundOrderServiceImpl inboundOrderService;

    @Test
    public void createTest() {
        InboundOrderDTO inboundOrderDTO = mockInboundOrderDTO();
        InboundOrderResponseDTO inboundOrderResponseDTO = mockInboundOrderResponseDTO();
        when(inboundOrderService.create(inboundOrderDTO, "randomuser")).thenReturn(inboundOrderResponseDTO);
        assertEquals(inboundOrderResponseDTO, inboundOrderService.create(inboundOrderDTO, "randomuser"));
    }

    @Test
    public void createEntityTest() {
        InboundOrder inboundOrder = mockInboundOrder();
        when(inboundOrderService.create(inboundOrder)).thenReturn(inboundOrder);
        assertEquals(inboundOrder, inboundOrderService.create(inboundOrder));
    }

    @Test
    public void updateTest() {
        InboundOrderDTO inboundOrderDTO = mockInboundOrderDTO();
        List<BatchDTO> batchDTOList = mockBatchDTOList();
        when(inboundOrderService.update(inboundOrderDTO, 1L, "randomuser")).thenReturn(batchDTOList);
        assertEquals(batchDTOList, inboundOrderService.update(inboundOrderDTO, 1L, "randomuser"));
    }

    @Test
    public void updateEntityTest() {
        InboundOrder inboundOrder = mockInboundOrder();
        when(inboundOrderService.update(inboundOrder)).thenReturn(inboundOrder);
        assertEquals(inboundOrder, inboundOrderService.update(inboundOrder));
    }

    @Test
    public void findByIdTest() {
        InboundOrder inboundOrder = mockInboundOrder();
        when(inboundOrderService.findById(1L)).thenReturn(inboundOrder);
        assertEquals(inboundOrder, inboundOrderService.findById(1L));
    }

    private InboundOrderDTO mockInboundOrderDTO() {
        return InboundOrderDTO.builder()
                .id(1L)
                .orderDate(LocalDate.now())
                .batchStock(mockBatchDTOList())
                .section(SectionDTO.builder().build())
                .build();
    }

    private InboundOrder mockInboundOrder() {
        return InboundOrder.builder()
                .id(1L)
                .batchStock(mockBatchList())
                .orderDate(LocalDate.now())
                .section(Section.builder().build())
                .supervisor(mockSupervisor())
                .build();
    }

    private InboundOrderResponseDTO mockInboundOrderResponseDTO() {
        return InboundOrderResponseDTO.builder()
                .id(1L)
                .batchStock(mockBatchDTOList())
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

    private Batch mockBatch(){
        return Batch.builder()
                .id(1L)
                .currentQuantity(10)
                .currentTemperature(10)
                .initialQuantity(10)
                .dueDate(LocalDate.of(2021, 6, 6))
                .manufacturingDate(LocalDate.of(2021, 6, 6))
                .manufacturingTime(LocalDateTime.of(2021, 6, 6, 6, 6))
                .minTemperature(-10)
                .maxTemperature(10)
                .build();
    }

    private Supervisor mockSupervisor() {
        Supervisor supervisor = new Supervisor();
        supervisor.setId(1L);
        supervisor.setInboundOrders(Arrays.asList(new InboundOrder(), new InboundOrder()));
        supervisor.setName("Random Supervisor");
        supervisor.setEmail("randomsupervisor@meli.com");
        supervisor.setUsername("randomsupervisor");
        supervisor.setPassword("123321");
        return supervisor;
    }
}
