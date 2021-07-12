package com.mercadolibre.projetointegrador.controller;


import com.mercadolibre.projetointegrador.dtos.response.BatchDueDateResponseDTO;
import com.mercadolibre.projetointegrador.service.crud.impl.BatchServiceImpl;
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
    public List<BatchDueDateResponseDTO> findByDueDate(@RequestParam int days){
        return batchService.findByDueDateBetween(days);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<BatchDueDateResponseDTO> findByDueDateList(@RequestParam int days,
                                                           @RequestParam(defaultValue = "FS") String category,
                                                           @RequestParam(defaultValue = "asc") String order) {
        return batchService.findByDueDateCategoryBetweenDates(category, days, order);
    }


}
