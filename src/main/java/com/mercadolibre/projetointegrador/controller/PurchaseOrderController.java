package com.mercadolibre.projetointegrador.controller;

import com.mercadolibre.projetointegrador.dtos.PurchaseOrderDTO;
import com.mercadolibre.projetointegrador.dtos.response.InboundOrderResponseDTO;
import com.mercadolibre.projetointegrador.model.Product;
import com.mercadolibre.projetointegrador.model.PurchaseOrder;
import com.mercadolibre.projetointegrador.service.crud.impl.PurchaseOrderServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/fresh-products")
public class PurchaseOrderController {

    private final PurchaseOrderServiceImpl purchaseOrderService;

    @Operation(summary = "US02 - Get Products from Order", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
    })
    @GetMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProductsFromOrder(@PathVariable Long id){
        return purchaseOrderService.getProducts(id);
    }

    @Operation(summary = "US02 - Create Purchase Order", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Double.class))),
    })
    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public double insertPurchaseOder(@Valid @RequestBody PurchaseOrderDTO purchaseOrderDTO){
        return purchaseOrderService.insertAndCalculatePurchaseOrder(purchaseOrderDTO);
    }

    @Operation(summary = "US02 - Get Products from Category", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
    })
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public Set<Product> getProductsFromCategory(@RequestParam(value = "category", defaultValue = "FS") String category){
        return purchaseOrderService.getSectorProducts(category);
    }

    @Operation(summary = "US02 - Update Purchase Order", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Double.class))),
    })
    @PutMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public double updatePurchaseOder(
            @Valid @RequestBody PurchaseOrderDTO purchaseOrderDTO,
            @PathVariable Long id){
        return purchaseOrderService.updatePurchaseOrder(purchaseOrderDTO, id);
    }
}
