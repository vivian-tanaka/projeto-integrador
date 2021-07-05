package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.exceptions.NotFoundException;
import com.mercadolibre.dambetan01.model.Employee;
import com.mercadolibre.dambetan01.repository.EmployeeRepository;
import com.mercadolibre.dambetan01.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee getEmployee(String username) {
        Employee employee = employeeRepository.findByUsername(username);
        if(employee == null) throw new NotFoundException("Usuário "+username+" não encontrado");
        return  employee;
    }
}
