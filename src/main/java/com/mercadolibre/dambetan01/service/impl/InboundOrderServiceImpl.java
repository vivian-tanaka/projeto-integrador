package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.dtos.BatchDTO;
import com.mercadolibre.dambetan01.dtos.InboundOrderDTO;
import com.mercadolibre.dambetan01.dtos.SectionDTO;
import com.mercadolibre.dambetan01.dtos.response.InboundOrderResponseDTO;
import com.mercadolibre.dambetan01.model.*;
import com.mercadolibre.dambetan01.repository.*;
import com.mercadolibre.dambetan01.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InboundOrderServiceImpl implements InboundOrderService {

    ModelMapper modelMapper = new ModelMapper();

    private final InboundOrderRepository repository;

    private final ProductService productService;

    private final SectionService sectionService;

    private final EmployeeService employeeService;

    private final SupervisorService supervisorService;

    public InboundOrderServiceImpl(InboundOrderRepository repository, SectionService sectionService, EmployeeService employeeService, SupervisorService supervisorService, ProductService productService) {
        this.repository = repository;
        this.sectionService = sectionService;
        this.employeeService = employeeService;
        this.supervisorService = supervisorService;
        this.productService = productService;
    }

    @Override
    public InboundOrderResponseDTO createInboundOrder(InboundOrderDTO inboundOrderDTO, String username) {
        return createAndSaveOrder(inboundOrderDTO, username);
    }

    @Override
    public InboundOrderResponseDTO updateInboundOrder(InboundOrderDTO inboundOrderDTO, String username) {
        if(!repository.existsById(inboundOrderDTO.getOrderNumber())){
            return createAndSaveOrder(inboundOrderDTO, username);
        }

        SectionDTO sectionDTO = inboundOrderDTO.getSection();
        Supervisor supervisor = supervisorService.getSupervisor(employeeService.getEmployee(username).getId());
        Section section = sectionService.getSection(sectionDTO);

        List<Batch> batches = mapBatchItemsDTO(inboundOrderDTO);
        InboundOrder inboundOrder = InboundOrder.builder()
                .supervisor(supervisor)
                .id(inboundOrderDTO.getOrderNumber())
                .orderDate(inboundOrderDTO.getOrderDate())
                .section(section)
                .batchStock(batches)
                .build();

        repository.save(inboundOrder);

        return modelMapper.map(inboundOrder, InboundOrderResponseDTO.class);
    }

    private List<Batch> mapBatchItemsDTO(InboundOrderDTO inboundOrderDTO) {
        List<BatchDTO> batchDTOS = inboundOrderDTO.getBatchStock();
        List<Batch> batches = new ArrayList<>();

        for (BatchDTO batch : batchDTOS) {
            Product product = productService.getProduct(batch);
            Batch batchItem = Batch.builder()
                    .product(product)
                    .id(batch.getBatchNumber())
                    .currentTemperature(batch.getCurrentTemperature())
                    .minTemperature(batch.getMinTemperature())
                    .maxTemperature(batch.getMaxTemperature())
                    .initialQuantity(batch.getInitialQuantity())
                    .currentQuantity(batch.getCurrentQuantity())
                    .manufacturingDate(batch.getManufacturingDate())
                    .manufacturingTime(batch.getManufacturingTime())
                    .dueDate(batch.getDueDate())
                    .build();

            batches.add(batchItem);
        }
        return batches;
    }

    private InboundOrderResponseDTO createAndSaveOrder(InboundOrderDTO inboundOrderDTO, String username) {
        SectionDTO sectionDTO = inboundOrderDTO.getSection();
        Supervisor supervisor = supervisorService.getSupervisor(employeeService.getEmployee(username).getId());
        Section section = sectionService.getSection(sectionDTO);

        List<Batch> batches = mapBatchItemsDTO(inboundOrderDTO);
        InboundOrder inboundOrder = InboundOrder.builder()
                .supervisor(supervisor)
                .id(inboundOrderDTO.getOrderNumber())
                .orderDate(inboundOrderDTO.getOrderDate())
                .section(section)
                .batchStock(batches)
                .build();

        repository.save(inboundOrder);

        InboundOrder savedInboundOrder = repository.findById(inboundOrderDTO.getOrderNumber()).get();

        return modelMapper.map(savedInboundOrder, InboundOrderResponseDTO.class);
    }
}
