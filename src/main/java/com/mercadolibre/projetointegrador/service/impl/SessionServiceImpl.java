package com.mercadolibre.projetointegrador.service.impl;

import com.mercadolibre.projetointegrador.dtos.response.EmployeeResponseDTO;
import com.mercadolibre.projetointegrador.exceptions.ApiException;
import com.mercadolibre.projetointegrador.model.Employee;
import com.mercadolibre.projetointegrador.service.ISessionService;
import com.mercadolibre.projetointegrador.service.crud.impl.EmployeeServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SessionServiceImpl implements ISessionService {
    private final EmployeeServiceImpl employeeService;

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

        Employee employee = employeeService.findByUsernameAndPassword(username,password);

        String token = getJWTToken(employee);
        EmployeeResponseDTO user = new EmployeeResponseDTO();
        user.setUsername(username);
        user.setToken(token);
        return user;
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
        if(token != null && token.startsWith("Bearer ")) {
            Claims claims = decodeJWT(token.replace("Bearer ", ""));
            return claims.get("sub", String.class);
        }
        return null;
    }

/*    private String getUsername(String token){
        if(token != null && token.startsWith("Bearer ")) {
            String username = SessionServiceImpl.getUsername(token.replace("Bearer ", ""));
            return username;
        }
        return null;
    }*/

}
