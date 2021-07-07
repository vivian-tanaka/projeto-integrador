package com.mercadolibre.projetointegrador.unit.service;

import com.mercadolibre.projetointegrador.model.Buyer;
import com.mercadolibre.projetointegrador.service.crud.impl.BuyerServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BuyerServiceImplTest {

    @Mock
    private BuyerServiceImpl buyerService;

    @Test
    public void findByIdTest() {
        Buyer buyer = mockBuyer();
        when(buyerService.findById(1L)).thenReturn(buyer);
        assertEquals(buyer, buyerService.findById(1L));
    }

    private Buyer mockBuyer() {
        Buyer buyer = new Buyer();
        buyer.setId(1L);
        buyer.setName("random buyer");
        buyer.setEmail("randombuyer@meli.com");
        return buyer;
    }
}
