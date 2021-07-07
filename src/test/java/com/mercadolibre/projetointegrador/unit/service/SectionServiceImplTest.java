package com.mercadolibre.projetointegrador.unit.service;

import com.mercadolibre.projetointegrador.dtos.SectionDTO;
import com.mercadolibre.projetointegrador.model.Section;
import com.mercadolibre.projetointegrador.model.Warehouse;
import com.mercadolibre.projetointegrador.service.crud.impl.SectionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SectionServiceImplTest {

    @Mock
    private SectionServiceImpl sectionService;

    @Test
    public void findSectionBySectionCodeAndWarehouseIdTest() {
        SectionDTO sectionDTO = mockSectionDTO();
        Section section = mockSection();
        when(sectionService.findSectionBySectionCodeAndWarehouseId(sectionDTO)).thenReturn(section);
        assertEquals(section, sectionService.findSectionBySectionCodeAndWarehouseId(sectionDTO));
    }

    private Section mockSection() {
        return Section.builder()
                .id(1L)
                .sectionCode("FF")
                .inboundOrders(new ArrayList<>())
                .maxTemperature(10)
                .minTemperature(0)
                .warehouse(Warehouse.builder().id(1L).build())
                .build();
    }

    private SectionDTO mockSectionDTO() {
        return SectionDTO.builder()
                .id("1L")
                .warehouseCode(1L)
                .build();
    }
}
