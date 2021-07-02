package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.dtos.BatchItemDTO;
import com.mercadolibre.dambetan01.dtos.InboundOrderDTO;
import com.mercadolibre.dambetan01.dtos.SectionDTO;
import com.mercadolibre.dambetan01.dtos.response.InboundOrderResponseDTO;
import com.mercadolibre.dambetan01.model.*;
import com.mercadolibre.dambetan01.repository.*;
import com.mercadolibre.dambetan01.service.InboundOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class InboundOrderServiceImpl implements InboundOrderService {

    ModelMapper modelMapper = new ModelMapper();

    final ProductRepository productRepository;

    final InboundOrderRepository repository;

    final SectionRepository sectionRepository;

    final
    SupervisorRepository supervisorRepository;

    final
    BatchItemRepository batchItemRepository;

    public InboundOrderServiceImpl(ProductRepository productRepository, InboundOrderRepository repository, SectionRepository sectionRepository, SupervisorRepository supervisorRepository, BatchItemRepository batchItemRepository) {
        this.productRepository = productRepository;
        this.repository = repository;
        this.sectionRepository = sectionRepository;
        this.supervisorRepository = supervisorRepository;
        this.batchItemRepository = batchItemRepository;
    }

    @Override
    public InboundOrderResponseDTO createInboundOrder(InboundOrderDTO inboundOrderDTO, String username) {
        return createAndSaveOrder(inboundOrderDTO, username);
    }

    //TODO solve updateInboundOrder method
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

    private Supervisor getSupervisor(String username) {
        return supervisorRepository.findSupervisorByUsername(username).orElseThrow(NoSuchElementException::new);
    }

    private Section getSection(SectionDTO sectionDTO) {
        return sectionRepository.findSectionBySectionCode(sectionDTO.getSectionCode()).orElseThrow(NoSuchElementException::new);
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
        Supervisor supervisor = getSupervisor(username);
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
