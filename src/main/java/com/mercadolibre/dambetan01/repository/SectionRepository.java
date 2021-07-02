package com.mercadolibre.dambetan01.repository;

import com.mercadolibre.dambetan01.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {

    Optional<Section> findSectionBySectionCodeAndWarehouse_Id(String sectionCode, Long id);

}
