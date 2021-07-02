package com.mercadolibre.dambetan01.controller;

import com.mercadolibre.dambetan01.dtos.InboundOrderDTO;
import com.mercadolibre.dambetan01.dtos.response.InboundOrderResponseDTO;
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
        InboundOrderResponseDTO response = inboundOrderService.createInboundOrder(inboundOrderDTO,getUsernameFromToken(request));
        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<InboundOrderResponseDTO> update(@RequestBody InboundOrderDTO inboundOrderDTO, HttpServletRequest request){
        InboundOrderResponseDTO response = inboundOrderService.updateInboundOrder(inboundOrderDTO, request);
        return ResponseEntity.ok().body(response);
    }

    private String getUsernameFromToken(HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return getUsername(authorizationHeader);
        }

        return null;
    }

    private String getUsername(String token) {
        Claims claims = getClaims(token);

        if(claims != null) {
            String username = claims.getSubject();
            return username;
        }

        return null;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey("asdasdsadasd".getBytes())
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e) {
            return null;
        }
    }

}
