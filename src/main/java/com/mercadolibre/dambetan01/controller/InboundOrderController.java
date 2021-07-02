package com.mercadolibre.dambetan01.controller;

import com.mercadolibre.dambetan01.dtos.InboundOrderDTO;
import com.mercadolibre.dambetan01.dtos.response.InboundOrderResponseDTO;
import com.mercadolibre.dambetan01.service.ISessionService;
import com.mercadolibre.dambetan01.service.impl.SessionServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/fresh-products/inboundorder")
public class InboundOrderController {

    private InboundOrderService inboundOrderService;

    public InboundOrderController(InboundOrderService inboundOrderService){
        this.inboundOrderService = inboundOrderService;
    }

    @PostMapping
    public ResponseEntity<InboundOrderResponseDTO> create(@RequestBody InboundOrderDTO inboundOrderDTO, HttpServletRequest request){
        String jwtToken = getToken(request);
        InboundOrderResponseDTO response = inboundOrderService
                .createInboundOrder(inboundOrderDTO, SessionServiceImpl.getUsername(jwtToken));
        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<InboundOrderResponseDTO> update(@RequestBody InboundOrderDTO inboundOrderDTO, HttpServletRequest request){
        String jwtToken = getToken(request);
        InboundOrderResponseDTO response = inboundOrderService
                .updateInboundOrder(inboundOrderDTO, SessionServiceImpl.getUsername(jwtToken));
        return ResponseEntity.ok().body(response);
    }

    private String getToken(HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return request.getHeader(authorizationHeader).replace("Bearer ", "");
        }
        return null;
    }

}
