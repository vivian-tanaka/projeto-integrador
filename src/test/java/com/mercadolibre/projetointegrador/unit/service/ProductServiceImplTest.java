package com.mercadolibre.projetointegrador.unit.service;

import com.mercadolibre.projetointegrador.model.Product;
import com.mercadolibre.projetointegrador.model.Seller;
import com.mercadolibre.projetointegrador.service.crud.impl.ProductServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Mock
    private ProductServiceImpl productService;

    @Test
    public void findByIdTest() {
        Product product = mockProduct();
        when(productService.findById(1L)).thenReturn(product);
        assertEquals(product, productService.findById(1L));
    }

    private Product mockProduct() {
        return Product.builder()
                .id(1L)
                .name("Danoninho")
                .description("Danoninho do dinossauro sabor Morango")
                .price(100)
                .rating(10)
                .seller(new Seller())
                .build();
    }
}
