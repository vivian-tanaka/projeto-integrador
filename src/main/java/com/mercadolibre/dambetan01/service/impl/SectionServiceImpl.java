package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.dtos.SectionDTO;
import com.mercadolibre.dambetan01.exceptions.NotFoundException;
import com.mercadolibre.dambetan01.model.Section;
import com.mercadolibre.dambetan01.repository.SectionRepository;
import com.mercadolibre.dambetan01.service.SectionService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;

    public SectionServiceImpl(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @Override
    public Section getSection(SectionDTO sectionDTO) {
        return sectionRepository.findSectionBySectionCodeAndWarehouse_Id(
                sectionDTO.getSectionCode(),
                sectionDTO.getWarehouseCode())
                .orElseThrow(() -> new NotFoundException(
                        "Seção de id: "+ sectionDTO.getSectionCode()+" e/ou Warehouse "+sectionDTO.getWarehouseCode()+" não encontrado"));
    }
}
