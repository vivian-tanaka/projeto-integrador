package com.mercadolibre.projetointegrador.controller;

import com.mercadolibre.projetointegrador.model.Batch;
import com.mercadolibre.projetointegrador.service.impl.ExpiredBatchServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class ExpiredBatchController {

    private final ExpiredBatchServiceImpl expiredBatchService;

    public ExpiredBatchController(ExpiredBatchServiceImpl expiredBatchService) {
        this.expiredBatchService = expiredBatchService;
    }

    @Operation(summary = "US06 - Remove expired Batches", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Batch.class))),
    })
    @GetMapping(name = "/expired")
    @ResponseStatus(HttpStatus.OK)
    public List<Batch> checkForDueDate() {
        return expiredBatchService.removeExpiredBatches();
    }
}
