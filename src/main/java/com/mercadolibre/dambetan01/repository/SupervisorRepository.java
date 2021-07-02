package com.mercadolibre.dambetan01.repository;

import com.mercadolibre.dambetan01.model.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {

    Optional<Supervisor> findSupervisorByUsername(String username);

}
