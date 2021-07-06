package com.mercadolibre.projetointegrador.service;

import com.mercadolibre.projetointegrador.dtos.BatchDTO;
import com.mercadolibre.projetointegrador.model.Product;

public interface ProductService {
    Product getProduct(BatchDTO item);
    Product getProduct(Long id);
}
