package com.mercadolibre.projetointegrador.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.projetointegrador.Application;
import com.mercadolibre.projetointegrador.dtos.OrderStatusDTO;
import com.mercadolibre.projetointegrador.dtos.ProductDTO;
import com.mercadolibre.projetointegrador.dtos.PurchaseOrderDTO;
import com.mercadolibre.projetointegrador.exceptions.BadRequestException;
import com.mercadolibre.projetointegrador.model.Product;
import com.mercadolibre.projetointegrador.model.Seller;
import com.mercadolibre.projetointegrador.service.crud.impl.PurchaseOrderServiceImpl;
import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"SCOPE_SUFFIX = integration_test"})
@AutoConfigureMockMvc
public class PurchaseOrderControllerTest {

    private static final String PURCHASEORDER_API = "/api/v1/fresh-products";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PurchaseOrderServiceImpl purchaseOrderService;

    @Test
    @DisplayName("Deve retornar 201 e valor da purchase order")
    public void returnValueFromPurchaseOrderTest(){
        PurchaseOrderDTO purchaseOrderDTO = createPurchaseOrderDTO();

        double product1Price = 20.0, product2Price = 12.0;
        Double purchaseValue = purchaseOrderDTO.getProducts().get(0).getQuantity() * product1Price
                + purchaseOrderDTO.getProducts().get(1).getQuantity() * product2Price;

        Mockito.when(purchaseOrderService.insertAndCalculatePurchaseOrder(Mockito.any(PurchaseOrderDTO.class)))
                .thenReturn(purchaseValue);

        Assertions.assertEquals(purchaseValue, purchaseOrderService.insertAndCalculatePurchaseOrder(purchaseOrderDTO));
    }

    @Test
    @DisplayName("deve retornar 400 quando enviado DTO incorreto")
    public void returnBadRequestWhenDTOIsIncorrect() throws Exception {
        PurchaseOrderDTO purchaseOrderDTO = PurchaseOrderDTO.builder().build();
        String json = new ObjectMapper().writeValueAsString(purchaseOrderDTO);

        double product1Price = 20.0, product2Price = 12.0;
        Double purchaseValue = purchaseOrderDTO.getProducts().get(0).getQuantity() * product1Price
                + purchaseOrderDTO.getProducts().get(1).getQuantity() * product2Price;

        Mockito.when(purchaseOrderService
                .insertAndCalculatePurchaseOrder(
                        Mockito.any(PurchaseOrderDTO.class)))
                .thenReturn(purchaseValue);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(PURCHASEORDER_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .header("Authorization","")
                .content(json);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("deve retornar estoque insuficiente")
    public void returnWhenNotEnoghtItemsInBatch() {
        PurchaseOrderDTO purchaseOrderDTO = createPurchaseOrderDTO();

        double product1Price = 20.0, product2Price = 12.0;
        Double purchaseValue = purchaseOrderDTO.getProducts().get(0).getQuantity() * product1Price
                + purchaseOrderDTO.getProducts().get(1).getQuantity() * product2Price;

        Mockito.when(purchaseOrderService
                .insertAndCalculatePurchaseOrder(Mockito.any(PurchaseOrderDTO.class)))
                .thenThrow(new NotFoundException("Estoque insuficiente"));

        BadRequestException exception = Assertions
                .assertThrows(BadRequestException.class, () -> purchaseOrderService.insertAndCalculatePurchaseOrder(purchaseOrderDTO));
    }

    @Test
    @DisplayName("Deve retornar lista de produtos no pedido")
    public void returnProductsFromOrderTest() throws Exception{
        List<Product> expectedList = createProductList();

        Mockito.when(purchaseOrderService
                .getProducts(Mockito.any(Long.class)))
                .thenReturn(expectedList);

        Assertions.assertEquals(expectedList.size(), purchaseOrderService.getProducts(1L).size());
        Assertions.assertEquals(expectedList.get(1).getId(), purchaseOrderService.getProducts(1L).get(1).getId());
    }

    @Test
    @DisplayName("Deve retornar 201 e valor da purchase order no put correto")
    public void returnValueFromPurchaseOrderPutTest() throws Exception{
        PurchaseOrderDTO purchaseOrderDTO = createPurchaseOrderDTO();
        String json = new ObjectMapper().writeValueAsString(purchaseOrderDTO);

        double product1Price = 20.0, product2Price = 12.0;

        Double purchaseValue = purchaseOrderDTO.getProducts().get(0).getQuantity() * product1Price
                + purchaseOrderDTO.getProducts().get(1).getQuantity() * product2Price;

        Mockito.when(purchaseOrderService
                .updatePurchaseOrder(
                        Mockito.any(PurchaseOrderDTO.class),
                        Mockito.any(Long.class)))
                .thenReturn(purchaseValue);

        Assertions.assertEquals(purchaseValue, purchaseOrderService.updatePurchaseOrder(purchaseOrderDTO, 1L));
    }

    private PurchaseOrderDTO createPurchaseOrderDTO() {
        return PurchaseOrderDTO.builder()
                .date(LocalDate.of(2021,7,6))
                .buyerId(8L)
                .orderStatus(new OrderStatusDTO("Open"))
                .products(createProductDTOList())
                .build();
    }

    private List<ProductDTO> createProductDTOList() {
        List<ProductDTO> productDTOList = new ArrayList<>();

        ProductDTO productDTO1 = new ProductDTO(1L,5);
        ProductDTO productDTO2 = new ProductDTO(2L,5);

        productDTOList.addAll(Arrays.asList(productDTO1, productDTO2));

        return productDTOList;
    }

    private List<Product> createProductList(){
        List<Product> productList = new ArrayList<>();

        Product product1 = new Product(1L, new Seller(), "Danone", "danoninho do dinossauro", 20.0, 10.0);
        Product product2 = new Product(1L, new Seller(), "Bolo", "bolo caseiro", 12.0, 9.0);

        productList.addAll(Arrays.asList(product1, product2));

        return productList;
    }

}
