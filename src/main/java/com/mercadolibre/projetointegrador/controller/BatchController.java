package com.mercadolibre.projetointegrador.controller;


import com.mercadolibre.projetointegrador.dtos.response.BatchDueDateResponseDTO;
import com.mercadolibre.projetointegrador.dtos.response.WarehouseStockResponseDTO;
import com.mercadolibre.projetointegrador.service.crud.impl.BatchServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/fresh-products/duedate")
public class BatchController {

    private final BatchServiceImpl batchService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "US05 - Check Batch Stock Due Date", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BatchDueDateResponseDTO.class))),
    })
    public List<BatchDueDateResponseDTO> findByDueDate(@RequestParam int days){
        return batchService.findByDueDateBetween(days);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "US05 - Check Batch Stock Due Date by Category", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BatchDueDateResponseDTO.class))),
    })
    public List<BatchDueDateResponseDTO> findByDueDateList(@RequestParam int days,
                                                           @RequestParam(defaultValue = "FS") String category,
                                                           @RequestParam(defaultValue = "asc") String order) {
        return batchService.findByDueDateCategoryBetweenDates(category, days, order);
    }


}
