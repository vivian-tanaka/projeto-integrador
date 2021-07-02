package com.mercadolibre.dambetan01.repository;

import com.mercadolibre.dambetan01.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
