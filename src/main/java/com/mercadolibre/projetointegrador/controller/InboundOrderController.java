package com.mercadolibre.projetointegrador.controller;

import com.mercadolibre.projetointegrador.dtos.InboundOrderDTO;
import com.mercadolibre.projetointegrador.dtos.response.InboundOrderResponseDTO;
import com.mercadolibre.projetointegrador.service.InboundOrderService;
import com.mercadolibre.projetointegrador.service.impl.SessionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products/inboundorder")
public class InboundOrderController {

    private InboundOrderService inboundOrderService;

    public InboundOrderController(InboundOrderService inboundOrderService){
        this.inboundOrderService = inboundOrderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InboundOrderResponseDTO create(
            @Valid @RequestBody InboundOrderDTO inboundOrderDTO,
            @RequestHeader(value="Authorization") String token){
        String username = getUsername(token);
        InboundOrderResponseDTO response = inboundOrderService
                .createInboundOrder(inboundOrderDTO, username);
        return response;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InboundOrderResponseDTO update(
            @Valid @RequestBody InboundOrderDTO inboundOrderDTO,
            @RequestHeader(value="Authorization") String token){
        String username = getUsername(token);
        InboundOrderResponseDTO response = inboundOrderService
                .updateInboundOrder(inboundOrderDTO, username);
        return response;
    }

    private String getUsername(String token){
        if(token != null && token.startsWith("Bearer ")) {
            String username = SessionServiceImpl.getUsername(token.replace("Bearer ", ""));
            return username;
        }
        return null;
    }

}
