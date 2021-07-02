package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.dtos.response.EmployeeResponseDTO;
import com.mercadolibre.dambetan01.exceptions.ApiException;
import com.mercadolibre.dambetan01.model.Employee;
import com.mercadolibre.dambetan01.repository.EmployeeRepository;
import com.mercadolibre.dambetan01.service.ISessionService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javassist.NotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class SessionServiceImpl implements ISessionService {
    private final EmployeeRepository employeeRepository;

    public SessionServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
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
    @Override
    public EmployeeResponseDTO login(String username, String password) throws ApiException {
        //Voy a la base de datos y reviso que el usuario y contraseña existan.
        Employee employee = employeeRepository.findByUsernameAndPassword(username, password);

        if (employee != null) {
            String token = getJWTToken(employee);
            EmployeeResponseDTO user = new EmployeeResponseDTO();
            user.setUsername(username);
            user.setToken(token);
            return user;
        } else {
            throw new ApiException("404", "Usuario y/o contraseña incorrecto", 404);
        }

    }

    /**
     * Genera un token para un usuario específico, válido por 10'
     * @param employee
     * @return
     */
    private String getJWTToken(Employee employee) {
        String secretKey = "mySecretKey";

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(employee.getUsername())
                .claim("authorities",
                        employee.getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

    /**
     * Decodifica un token para poder obtener los componentes que contiene el mismo
     * @param token
     * @return
     */
    private static Claims decodeJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey("mySecretKey".getBytes())
                .parseClaimsJws(token).getBody();
        return claims;
    }

    /**
     * Permite obtener el username según el token indicado
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        Claims claims = decodeJWT(token);
        return claims.get("sub", String.class);
    }

}
