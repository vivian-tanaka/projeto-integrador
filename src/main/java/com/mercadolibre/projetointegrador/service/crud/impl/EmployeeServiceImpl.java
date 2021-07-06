package com.mercadolibre.projetointegrador.service.crud.impl;

import com.mercadolibre.projetointegrador.exceptions.ApiException;
import com.mercadolibre.projetointegrador.exceptions.NotFoundException;
import com.mercadolibre.projetointegrador.model.Employee;
import com.mercadolibre.projetointegrador.repository.EmployeeRepository;
import com.mercadolibre.projetointegrador.service.crud.ICRUD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements ICRUD<Employee> {

    private final EmployeeRepository employeeRepository;

    public Employee findByUsername(String username) {
        return employeeRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Usuário "+username+" não encontrado"));
    }

    public Employee findByUsernameAndPassword(String username, String password) {
        return employeeRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new ApiException("404", "Usuario y/o contraseña incorrecto", 404));
    }

    @Override
    public Employee create(Employee employee) {
        return null;
    }

    @Override
    public Employee update(Employee employee) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Employee findById(Long id) {
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }
}
