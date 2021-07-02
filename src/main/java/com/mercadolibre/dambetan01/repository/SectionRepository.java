package com.mercadolibre.dambetan01.repository;

import com.mercadolibre.dambetan01.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SectionRepository extends JpaRepository<Section, Long> {

    Optional<Section> findSectionBySectionCode(String sectionCode);
}
