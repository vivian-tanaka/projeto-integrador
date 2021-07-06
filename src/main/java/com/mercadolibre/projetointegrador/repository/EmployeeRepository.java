package com.mercadolibre.projetointegrador.repository;

import com.mercadolibre.projetointegrador.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    Optional<Employee> findByUsername(String username);
}
