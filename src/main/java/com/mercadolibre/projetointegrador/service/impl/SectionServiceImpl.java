package com.mercadolibre.projetointegrador.service.impl;

import com.mercadolibre.projetointegrador.dtos.SectionDTO;
import com.mercadolibre.projetointegrador.exceptions.NotFoundException;
import com.mercadolibre.projetointegrador.model.Section;
import com.mercadolibre.projetointegrador.repository.SectionRepository;
import com.mercadolibre.projetointegrador.service.SectionService;
import org.springframework.stereotype.Service;

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
