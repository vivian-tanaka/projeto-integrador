package com.mercadolibre.dambetan01.unit.service;

import com.mercadolibre.dambetan01.dtos.response.EmployeeResponseDTO;
import com.mercadolibre.dambetan01.exceptions.ApiException;
import com.mercadolibre.dambetan01.model.Account;
import com.mercadolibre.dambetan01.model.Employee;
import com.mercadolibre.dambetan01.repository.EmployeeRepository;
import com.mercadolibre.dambetan01.service.impl.SessionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SessionServiceImplTest {

    EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);
    SessionServiceImpl service;

    @BeforeEach
    void setUp(){
        this.service = new SessionServiceImpl(repository);
    }


    @Test
    void loginFail() {
        when(repository.findByUsernameAndPassword("user", "invalid")).thenReturn(null);
        assertThrows(ApiException.class, () -> service.login("user", "invalid"),
                "Usuario y/o contraseña incorrecto");
    }

    @Test
    void loginOk(){
        Employee employee = new Employee(1L, "User", "Pass", null, null, null);
        when(repository.findByUsernameAndPassword("User", "Pass")).thenReturn(employee);
        EmployeeResponseDTO accountDTO = service.login("User","Pass");
        assertEquals("User", accountDTO.getUsername());
        assertTrue(accountDTO.getToken().startsWith("Bearer"));
    }
}
