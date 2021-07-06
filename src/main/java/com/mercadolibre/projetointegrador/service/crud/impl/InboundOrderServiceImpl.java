package com.mercadolibre.projetointegrador.service.crud.impl;

import com.mercadolibre.projetointegrador.dtos.BatchDTO;
import com.mercadolibre.projetointegrador.dtos.InboundOrderDTO;
import com.mercadolibre.projetointegrador.exceptions.NotFoundException;
import com.mercadolibre.projetointegrador.mapper.BatchMapper;
import com.mercadolibre.projetointegrador.model.*;
import com.mercadolibre.projetointegrador.repository.*;
import com.mercadolibre.projetointegrador.service.crud.ICRUD;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InboundOrderServiceImpl implements ICRUD<InboundOrder> {

    private final BatchMapper batchMapper;
    private final InboundOrderRepository inboundOrderRepository;
    private final SectionServiceImpl sectionService;
    private final EmployeeServiceImpl employeeService;
    private final SupervisorServiceImpl supervisorService;
    private final BatchServiceImpl batchService;

    public List<BatchDTO> create(InboundOrderDTO inboundOrderDTO, String username) {
        InboundOrder inboundOrder = validateInboundOrder(inboundOrderDTO, username);
        create(inboundOrder);

        return batchMapper.mapListDtoToEntity(
                findById(inboundOrderDTO.getId())
                        .getBatchStock()
        );
    }

    public List<BatchDTO> update(InboundOrderDTO inboundOrderDTO, String username) {
        InboundOrder inboundOrder = validateInboundOrder(inboundOrderDTO, username);
        return batchMapper.mapListDtoToEntity(
                update(inboundOrder)
                        .getBatchStock()
        );
    }

    @Override
    public InboundOrder create(InboundOrder inboundOrder) {
        return inboundOrderRepository.save(inboundOrder);
    }

    @Override
    public InboundOrder update(InboundOrder inboundOrder) {
        findById(inboundOrder.getId());
        return create(inboundOrder);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public InboundOrder findById(Long id) {
        return inboundOrderRepository.findById(id).orElseThrow(() -> new NotFoundException("InboundOrder de id: " + id + " não encontrado"));
    }

    @Override
    public List<InboundOrder> findAll() {
        return null;
    }

    private InboundOrder validateInboundOrder(InboundOrderDTO inboundOrderDTO, String username) {

        Supervisor supervisor = supervisorService.findById(employeeService.findByUsername(username).getId());

        Section section = sectionService.findSectionBySectionCodeAndWarehouseId(inboundOrderDTO.getSection());

        List<Batch> batches = batchService.buildBatch(inboundOrderDTO.getBatchStock());

        InboundOrder inboundOrder = InboundOrder.builder()
                .supervisor(supervisor)
                .id(inboundOrderDTO.getId())
                .orderDate(inboundOrderDTO.getOrderDate())
                .section(section)
                .batchStock(batches)
                .build();

        return inboundOrder;
    }
}
