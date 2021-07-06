package com.mercadolibre.projetointegrador.unit.service;

import com.mercadolibre.projetointegrador.dtos.response.EmployeeResponseDTO;
import com.mercadolibre.projetointegrador.exceptions.ApiException;
import com.mercadolibre.projetointegrador.model.Employee;
import com.mercadolibre.projetointegrador.service.crud.impl.EmployeeServiceImpl;
import com.mercadolibre.projetointegrador.service.impl.SessionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SessionServiceImplTest {

    EmployeeServiceImpl employeeService = Mockito.mock(EmployeeServiceImpl.class);
    SessionServiceImpl service;

    @BeforeEach
    void setUp(){
        this.service = new SessionServiceImpl(employeeService);
    }


    @Test
    void loginFail() {
        when(employeeService.findByUsernameAndPassword("user", "invalid")).thenReturn(null);
        assertThrows(ApiException.class, () -> service.login("user", "invalid"),
                "Usuario y/o contraseña incorrecto");
    }

    @Test
    void loginOk(){
        Employee employee = new Employee(1L, "User", "Pass", null, null, null, null);
        when(employeeService.findByUsernameAndPassword("User", "Pass")).thenReturn(employee);
        EmployeeResponseDTO accountDTO = service.login("User","Pass");
        assertEquals("User", accountDTO.getUsername());
        assertTrue(accountDTO.getToken().startsWith("Bearer"));
    }
}
