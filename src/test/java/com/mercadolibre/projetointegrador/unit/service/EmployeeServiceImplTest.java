package com.mercadolibre.projetointegrador.unit.service;

import com.mercadolibre.projetointegrador.model.Employee;
import com.mercadolibre.projetointegrador.service.crud.impl.EmployeeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @Test
    public void findByUsernameAndPasswordTest() {
        Employee employee = mockEmployee();
        when(employeeService.findByUsernameAndPassword("randomemployee", "123321")).thenReturn(employee);
        assertEquals(employee, employeeService.findByUsernameAndPassword("randomemployee", "123321"));
    }

    @Test
    public void findByUsernameTest() {
        Employee employee = mockEmployee();
        when(employeeService.findByUsername("randomemployee")).thenReturn(employee);
        assertEquals(employee, employeeService.findByUsername("randomemployee"));
    }

    private Employee mockEmployee() {
        Employee employee = new Employee();
        employee.setName("Random Employee");
        employee.setUsername("randomemployee");
        employee.setPassword("123321");
        return employee;
    }
}
