package com.mercadolibre.projetointegrador.unit.controller;

import com.mercadolibre.projetointegrador.controller.SessionController;
import com.mercadolibre.projetointegrador.dtos.response.EmployeeResponseDTO;
import com.mercadolibre.projetointegrador.exceptions.ApiException;
import com.mercadolibre.projetointegrador.service.ISessionService;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class SessionControllerTest {

    SessionController controller;
    ISessionService service = Mockito.mock(ISessionService.class);

    @BeforeEach
    void setUp() throws NotFoundException {
        when(service.login("user_one", "contra12"))
                .thenThrow(new ApiException("404", "Usuario y/o contraseña incorrecto", 404));
        when(service.login("user_one", "contra123"))
                .thenReturn(new EmployeeResponseDTO("user_one", "contra123", "TOKEN"));
        controller = new SessionController(service);
    }

    @Test
    void loginFail() throws Exception {
        assertThrows(ApiException.class, () -> controller.login("user_one","contra12"),
                "Usuario y/o contraseña incorrecto");
    }

    @Test
    void loginOk() throws Exception {
        EmployeeResponseDTO accountDTO = controller.login("user_one","contra123");
        assertEquals("user_one", accountDTO.getUsername());
        assertEquals("contra123", accountDTO.getPassword());
        assertEquals("TOKEN", accountDTO.getToken());
    }
}
