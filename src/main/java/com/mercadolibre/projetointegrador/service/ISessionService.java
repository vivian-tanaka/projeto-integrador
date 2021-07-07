package com.mercadolibre.projetointegrador.service;


import com.mercadolibre.projetointegrador.dtos.response.UserResponseDTO;
import javassist.NotFoundException;

public interface ISessionService {

    /**
     * Realiza la validación del usuario y contraseña ingresado.
     * En caso de ser correcto, devuelve la cuenta con el token necesario para realizar las demás consultas.
     *
     * @param username
     * @param password
     * @return
     * @throws NotFoundException
     */
    UserResponseDTO login(String username, String password) throws NotFoundException;
}
