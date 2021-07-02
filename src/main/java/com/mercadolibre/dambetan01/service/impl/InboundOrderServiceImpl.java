package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.dtos.BatchItemDTO;
import com.mercadolibre.dambetan01.dtos.InboundOrderDTO;
import com.mercadolibre.dambetan01.dtos.SectionDTO;
import com.mercadolibre.dambetan01.dtos.response.InboundOrderResponseDTO;
import com.mercadolibre.dambetan01.model.*;
import com.mercadolibre.dambetan01.repository.*;
import com.mercadolibre.dambetan01.service.InboundOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class InboundOrderServiceImpl implements InboundOrderService {

    ModelMapper modelMapper = new ModelMapper();

    private final ProductRepository productRepository;

    private final InboundOrderRepository repository;

    private final SectionRepository sectionRepository;

    private final
    SupervisorRepository supervisorRepository;

    private final
    BatchItemRepository batchItemRepository;

    private final
    EmployeeRepository employeeRepository;

    public InboundOrderServiceImpl(ProductRepository productRepository, InboundOrderRepository repository, SectionRepository sectionRepository, SupervisorRepository supervisorRepository, BatchItemRepository batchItemRepository, EmployeeRepository employeeRepository) {
        this.productRepository = productRepository;
        this.repository = repository;
        this.sectionRepository = sectionRepository;
        this.supervisorRepository = supervisorRepository;
        this.batchItemRepository = batchItemRepository;
        this.employeeRepository = employeeRepository;
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
        InboundOrder inboundOrder = repository.findById(inboundOrderDTO.getOrderNumber()).orElseThrow(NoSuchElementException::new);
        List<BatchItem> batchItems = mapBatchItemsDTO(inboundOrderDTO);
        inboundOrder.setBatchStock(batchItems);

        batchItemRepository.saveAll(batchItems);
        repository.save(inboundOrder);

        return modelMapper.map(inboundOrder, InboundOrderResponseDTO.class);
    }

    private Employee getEmployee(String username){
        return employeeRepository.findByUsername(username);
    }

    private Supervisor getSupervisor(String username) {
        return supervisorRepository.findSupervisorByUsername(username).orElseThrow(NoSuchElementException::new);
    }

    private Supervisor getSupervisor(Long id) {
        return supervisorRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    private Section getSection(SectionDTO sectionDTO) {
        return sectionRepository.findSectionBySectionCodeAndWarehouse_Id(sectionDTO.getSectionCode(), sectionDTO.getWarehouseCode()).orElseThrow(NoSuchElementException::new);
    }

    private Product getProduct(BatchItemDTO item) {
        return productRepository.findById(item.getProductId()).orElseThrow(NoSuchElementException::new);
    }

    private List<BatchItem> mapBatchItemsDTO(InboundOrderDTO inboundOrderDTO) {
        List<BatchItemDTO> batchItemDTOS = inboundOrderDTO.getBatchStock();
        List<BatchItem> batchItems = new ArrayList<>();

        for (BatchItemDTO item: batchItemDTOS) {
            Product product = getProduct(item);
            BatchItem batchItem = BatchItem.builder()
                    .product(product)
                    .currentTemperature(item.getCurrentTemperature())
                    .minTemperature(item.getMinTemperature())
                    .maxTemperature(item.getMaxTemperature())
                    .initialQuantity(item.getInitialQuantity())
                    .currentQuantity(item.getCurrentQuantity())
                    .manufacturingDate(item.getManufacturingDate())
                    .manufacturingTime(item.getManufacturingTime())
                    .dueDate(item.getDueDate())
                    .build();

            batchItems.add(batchItem);
        }
        return batchItems;
    }

    private InboundOrderResponseDTO createAndSaveOrder(InboundOrderDTO inboundOrderDTO, String username) {
        SectionDTO sectionDTO = inboundOrderDTO.getSection();
        Supervisor supervisor = getSupervisor(getEmployee(username).getId());
        Section section = getSection(sectionDTO);

        List<BatchItem> batchItems = mapBatchItemsDTO(inboundOrderDTO);

        InboundOrder inboundOrder = InboundOrder.builder()
                .supervisor(supervisor)
                .id(inboundOrderDTO.getOrderNumber())
                .orderDate(LocalDate.now())
                .section(section)
                .batchStock(batchItems)
                .build();

        batchItemRepository.saveAll(batchItems);
        repository.save(inboundOrder);

        return modelMapper.map(inboundOrder, InboundOrderResponseDTO.class);
    }

}
