package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.dtos.SectionDTO;
import com.mercadolibre.dambetan01.model.Section;

public interface SectionService {
    Section getSection(SectionDTO sectionDTO);
}
