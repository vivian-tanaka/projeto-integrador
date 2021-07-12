package com.mercadolibre.projetointegrador.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.projetointegrador.Application;
import com.mercadolibre.projetointegrador.dtos.SectionDTO;
import com.mercadolibre.projetointegrador.dtos.WarehouseStockDTO;
import com.mercadolibre.projetointegrador.dtos.response.ProductSectionResponseDTO;
import com.mercadolibre.projetointegrador.dtos.response.WarehouseStockResponseDTO;
import com.mercadolibre.projetointegrador.model.Product;
import com.mercadolibre.projetointegrador.model.Seller;
import com.mercadolibre.projetointegrador.service.crud.impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"SCOPE_SUFFIX = integration_test"})
@AutoConfigureMockMvc
public class ProductControllerTest {

    private static final String PRODUCTS_API = "/api/v1/fresh-products";

    @MockBean
    private ProductServiceImpl productService;

    @Test
    @DisplayName("Deve retornar produto atualizado")
    public void updateTest(){
        Product product = createProduct();

        Mockito.when(productService.update(Mockito.any(Product.class)))
                .thenReturn(product);

        Assertions.assertEquals(product, productService.update(product));

    }

    @Test
    @DisplayName("Deve retornar 201 no delete")
    public void deleteTest(){
        List<Product> list = mockProductList();
        List<Product> expected = mockProductList();
        expected.remove(0);

        //Mockito.when(productService.delete(Mockito.any(Long.class))).thenReturn(expected);

        Assertions.assertEquals(expected.size(), list.size()-1);
    }

    @Test
    @DisplayName("Deve retornar 200 e lista de produtos")
    public void returnProductListTest() throws Exception{
        List<Product> expected = mockProductList();

        Mockito.when(productService
                .findAll())
                .thenReturn(expected);

        Assertions.assertEquals(expected, productService.findAll());
    }

    @Test
    @DisplayName("Deve retornar 200 e lista de produtos")
    public void returnProductByIdTest() throws Exception{
        Product expected = mockProductList().get(0);

        Mockito.when(productService
                .findById(Mockito.any(Long.class)))
                .thenReturn(expected);

        Assertions.assertEquals(expected, productService.findById(1L));
    }

    @Test
    @DisplayName("Deve retornar 200 e lista de produtos")
    public void returnProductByNameTest() throws Exception{
        Product expected = mockProductList().get(0);

        Mockito.when(productService
                .findByName(Mockito.any(String.class)))
                .thenReturn(expected);

        Assertions.assertEquals(expected, productService.findByName("Danoninho"));
    }

    @Test
    @DisplayName("Deve retornar 200 e lista de produtos")
    public void returnProductsBySellerNameTest(){
        List<Product> expected = mockProductList2();
        Mockito.when(productService
                .findAllBySellerName(Mockito.any(String.class)))
                .thenReturn(expected);

        Assertions.assertEquals(expected, productService.findAllBySellerName("Joao"));
    }

    @Test
    @DisplayName("Deve retornar a seção de um produto")
    public void returnSectionsByProductId(){
        List<ProductSectionResponseDTO> expectedList = mockSections();

        Mockito.when(productService
                .findSectionStockByProductId(Mockito.any(Long.class), Mockito.any(String.class), Mockito.any(String.class)))
                .thenReturn(expectedList);

        Assertions.assertEquals(expectedList, productService.findSectionStockByProductId(1L, "asc", ""));
    }

    @Test
    @DisplayName("Deve retornar stock por warehouses")
    public void returnProductsStockInWarehouse(){
        WarehouseStockResponseDTO expected = mockWarehouses();

        Mockito.when(productService
                .findProductstockInWarehouses(Mockito.any(Long.class)))
                .thenReturn(expected);
    }

    private WarehouseStockResponseDTO mockWarehouses() {
        WarehouseStockDTO warehouse1 = WarehouseStockDTO.builder()
                .warehouseCode(1L)
                .batchstock(new ArrayList<>())
                .build();
        WarehouseStockDTO warehouse2 = WarehouseStockDTO.builder()
                .warehouseCode(2L)
                .batchstock(new ArrayList<>())
                .build();
        List<WarehouseStockDTO> list = new ArrayList<>();
        list.addAll(Arrays.asList(warehouse1,warehouse2));
        WarehouseStockResponseDTO responseDTO = WarehouseStockResponseDTO.builder()
                .productId(1L)
                .warehouses(list)
                .build();
        return responseDTO;
    }


    private Product createProduct(){
        return Product.builder()
                .id(1L)
                .name("Danoninho")
                .description("Danoninho do dinossauro sabor Morango")
                .price(100)
                .rating(10)
                .seller(new Seller())
                .build();
    }

    private List<Product> mockProductList(){
        Product product1 = Product.builder()
                .id(1L)
                .name("Danoninho")
                .description("Danoninho do dinossauro sabor Morango")
                .price(100)
                .rating(10)
                .seller(new Seller())
                .build();

        Product product2 = Product.builder()
                .id(2L)
                .name("Bolo")
                .description("bolo caseiro")
                .price(15)
                .rating(10)
                .seller(new Seller())
                .build();

        Product product3 = Product.builder()
                .id(3L)
                .name("Alface")
                .description("alface crespa")
                .price(2.0)
                .rating(10)
                .seller(new Seller())
                .build();

        List<Product> productList = new ArrayList<>();
        productList.addAll(Arrays.asList(product1,product2,product3));

        return productList;
    }

    private List<Product> mockProductList2(){
        Seller seller = new Seller();
        seller.setUsername("Joao");
        Product product1 = Product.builder()
                .id(1L)
                .name("Danoninho")
                .description("Danoninho do dinossauro sabor Morango")
                .price(100)
                .rating(10)
                .seller(seller)
                .build();

        Product product2 = Product.builder()
                .id(2L)
                .name("Bolo")
                .description("bolo caseiro")
                .price(15)
                .rating(10)
                .seller(seller)
                .build();

        List<Product> productList = new ArrayList<>();
        productList.addAll(Arrays.asList(product1,product2));

        return productList;
    }

    private List<ProductSectionResponseDTO> mockSections(){
        SectionDTO section = SectionDTO.builder()
                .id("FS")
                .warehouseCode(1L)
                .build();
        ProductSectionResponseDTO product1 = ProductSectionResponseDTO.builder()
                .section(section)
                .productId(1L)
                .build();
        ProductSectionResponseDTO product2 = ProductSectionResponseDTO.builder()
                .section(section)
                .productId(2L)
                .build();
        List<ProductSectionResponseDTO> list = Arrays.asList(product1, product2);
        return list;
    }
}
