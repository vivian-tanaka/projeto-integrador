package com.mercadolibre.projetointegrador.controller;

import com.mercadolibre.projetointegrador.dtos.response.UserResponseDTO;
import com.mercadolibre.projetointegrador.service.ISessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Session", value = "/api/v1/sign-in")
@RequestMapping(path = "/api/v1")
@RestController
public class SessionController {
    private final ISessionService service;

    public SessionController(ISessionService sessionService) {
        this.service = sessionService;
    }

    /**
     * Realiza la validación del usuario y contraseña ingresado.
     * En caso de ser correcto, devuelve la cuenta con el token necesario para realizar las demás consultas.
     *
     * @param username
     * @param password
     * @return
     * @throws NotFoundException
     */
    @PostMapping("/sign-in")
    public UserResponseDTO login(@ApiParam(value = "Default value for tests", defaultValue = "sandro") @RequestParam("username") String username,
                                 @ApiParam(value = "Default value for tests", defaultValue = "954ght4h6") @RequestParam("password") String password)
            throws NotFoundException {
        return service.login(username, password);
    }

}
