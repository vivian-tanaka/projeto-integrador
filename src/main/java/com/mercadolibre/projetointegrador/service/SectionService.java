package com.mercadolibre.projetointegrador.service;

import com.mercadolibre.projetointegrador.dtos.SectionDTO;
import com.mercadolibre.projetointegrador.model.Section;

public interface SectionService {
    Section getSection(SectionDTO sectionDTO);
}
