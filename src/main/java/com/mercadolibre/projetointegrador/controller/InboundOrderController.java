package com.mercadolibre.projetointegrador.controller;

import com.mercadolibre.projetointegrador.dtos.BatchDTO;
import com.mercadolibre.projetointegrador.dtos.InboundOrderDTO;
import com.mercadolibre.projetointegrador.dtos.response.InboundOrderResponseDTO;
import com.mercadolibre.projetointegrador.service.crud.impl.InboundOrderServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.mercadolibre.projetointegrador.service.impl.SessionServiceImpl.getUsername;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/fresh-products/inboundorder")
public class InboundOrderController {

    private final InboundOrderServiceImpl inboundOrderService;

    @Operation(summary = "US01 - Create Inbound Order", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InboundOrderResponseDTO.class))),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InboundOrderResponseDTO create(
            @Valid @RequestBody InboundOrderDTO inboundOrderDTO,
            @RequestHeader(value="Authorization") String token){
        return inboundOrderService.create(inboundOrderDTO, getUsername(token));
    }

    @Operation(summary = "US01 - Update Inbound Order", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BatchDTO.class))),
    })
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public List<BatchDTO> update(
            @Valid @RequestBody InboundOrderDTO inboundOrderDTO,
            @PathVariable("id") Long idInboundOrder,
            @RequestHeader(value="Authorization") String token){
        return inboundOrderService.update(inboundOrderDTO, idInboundOrder, getUsername(token));
    }

}
