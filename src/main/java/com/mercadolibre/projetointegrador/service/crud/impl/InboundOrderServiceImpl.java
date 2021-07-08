package com.mercadolibre.projetointegrador.service.crud.impl;

import com.mercadolibre.projetointegrador.dtos.BatchDTO;
import com.mercadolibre.projetointegrador.dtos.InboundOrderDTO;
import com.mercadolibre.projetointegrador.dtos.response.InboundOrderResponseDTO;
import com.mercadolibre.projetointegrador.exceptions.NotFoundException;
import com.mercadolibre.projetointegrador.mapper.BatchMapper;
import com.mercadolibre.projetointegrador.model.InboundOrder;
import com.mercadolibre.projetointegrador.model.Section;
import com.mercadolibre.projetointegrador.model.Supervisor;
import com.mercadolibre.projetointegrador.model.Warehouse;
import com.mercadolibre.projetointegrador.repository.InboundOrderRepository;
import com.mercadolibre.projetointegrador.service.crud.ICRUD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InboundOrderServiceImpl implements ICRUD<InboundOrder> {

    private final BatchMapper batchMapper;
    private final InboundOrderRepository inboundOrderRepository;
    private final SectionServiceImpl sectionService;
    private final EmployeeServiceImpl employeeService;
    private final SupervisorServiceImpl supervisorService;
    private final BatchServiceImpl batchService;

    public InboundOrderResponseDTO create(InboundOrderDTO inboundOrderDTO, String username) {
        InboundOrder inboundOrder = validateInboundOrder(inboundOrderDTO, username);
        inboundOrder.setBatchStock(batchService.create(inboundOrderDTO.getBatchStock()));
        InboundOrder savedInboundOrder = create(inboundOrder);
        List<BatchDTO> batchDTOS = batchMapper.mapListDtoToEntity(
                findById(savedInboundOrder.getId())
                        .getBatchStock());

        return new InboundOrderResponseDTO().builder()
                .id(inboundOrder.getId())
                .batchStock(batchDTOS)
                .build();
    }

    public List<BatchDTO> update(InboundOrderDTO inboundOrderDTO, Long idInboundOrder, String username) {
        InboundOrder inboundOrder = validateInboundOrder(inboundOrderDTO, username);
        inboundOrder.setId(idInboundOrder);

        inboundOrder.setBatchStock(
                batchService.update(inboundOrderDTO.getBatchStock())
        );

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
        InboundOrder foundInboundOrder = findById(inboundOrder.getId());
        inboundOrder.setId(foundInboundOrder.getId());
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
        return inboundOrderRepository.findAll();
    }

    private InboundOrder validateInboundOrder(InboundOrderDTO inboundOrderDTO, String username) {

        Supervisor supervisor = supervisorService.findById(employeeService.findByUsername(username).getId());

        Section section = sectionService.findSectionBySectionCodeAndWarehouseId(inboundOrderDTO.getSection());

        InboundOrder inboundOrder = InboundOrder.builder()
                .supervisor(supervisor)
                .orderDate(inboundOrderDTO.getOrderDate())
                .section(section)
                .build();

        return inboundOrder;
    }

    public List<InboundOrder> findAllByWarehouse(Warehouse warehouse){
        return inboundOrderRepository.findAllBySection_Warehouse_Id(warehouse.getId());
    }
}
