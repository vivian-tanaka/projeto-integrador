package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.dtos.BatchDTO;
import com.mercadolibre.dambetan01.model.Product;

import java.util.List;

public interface ProductService {
    Product getProduct(BatchDTO item);
    Product getProduct(Long id);
}
