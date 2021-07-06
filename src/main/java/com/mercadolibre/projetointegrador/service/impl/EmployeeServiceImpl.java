package com.mercadolibre.projetointegrador.service.impl;

import com.mercadolibre.projetointegrador.exceptions.NotFoundException;
import com.mercadolibre.projetointegrador.model.Employee;
import com.mercadolibre.projetointegrador.repository.EmployeeRepository;
import com.mercadolibre.projetointegrador.service.EmployeeService;
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
