package com.mercadolibre.projetointegrador.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.projetointegrador.Application;
import com.mercadolibre.projetointegrador.dtos.BatchDTO;
import com.mercadolibre.projetointegrador.dtos.InboundOrderDTO;
import com.mercadolibre.projetointegrador.dtos.SectionDTO;
import com.mercadolibre.projetointegrador.dtos.response.InboundOrderResponseDTO;
import com.mercadolibre.projetointegrador.service.crud.impl.InboundOrderServiceImpl;
import org.hamcrest.Matchers;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"SCOPE_SUFFIX = integration_test"})
@AutoConfigureMockMvc
public class InboundOrderControllerTest {

    private static final String INBOUNDORDER_API = "/api/v1/fresh-products/inboundorder";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InboundOrderServiceImpl inboundOrderService;

    @Test
    @DisplayName("Deve retornar 201 e batchstock")
    public void returnInboundOrderResponseFromPostTest() throws Exception {
        InboundOrderDTO inboundOrderDTO = createInboundOrderDTO();
        String json = new ObjectMapper().writeValueAsString(inboundOrderDTO);

        InboundOrderResponseDTO responseDTO = InboundOrderResponseDTO.builder().batchStock(createBatchStock()).build();

        Mockito.when(inboundOrderService
                .create(
                        Mockito.any(InboundOrderDTO.class),
                        Mockito.isNull()))
                .thenReturn(responseDTO);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(INBOUNDORDER_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .header("Authorization","")
                .content(json);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("batchStock",Matchers.hasSize(3)))
        ;
    }

    @Test
    @DisplayName("Deve retornar 400 quando enviado DTO incorreto")
    public void returnBadRequestWhenRequestDTOIsIncorrect() throws Exception {
        InboundOrderDTO inboundOrderDTO = InboundOrderDTO.builder().build();
        String json = new ObjectMapper().writeValueAsString(inboundOrderDTO);

        InboundOrderResponseDTO responseDTO = InboundOrderResponseDTO.builder().batchStock(createBatchStock()).build();

        Mockito.when(inboundOrderService
                .create(
                        Mockito.any(InboundOrderDTO.class),
                        Mockito.isNull()))
                .thenReturn(responseDTO);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(INBOUNDORDER_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .header("Authorization","")
                .content(json);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    @DisplayName("Deve retornar 201 e batchStock quando PUT bem sucedido")
    public void returnInbountOrderResponsefromPutTest() throws Exception {
        InboundOrderDTO inboundOrderDTO = createInboundOrderDTO();
        String json = new ObjectMapper().writeValueAsString(inboundOrderDTO);

        Mockito.when(inboundOrderService
                .update(
                        Mockito.any(InboundOrderDTO.class),
                        Mockito.any(Long.class),
                        Mockito.isNull()))
                .thenReturn(createBatchStock());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(INBOUNDORDER_API+"/"+1l)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .header("Authorization","")
                .content(json);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isCreated())
        ;
    }

    @Test
    @DisplayName("Retorna 403 quando usuário não autenticado e autenticação está ativada")
    public void returnStatusForbiddenWhenNotAuthenticated() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(INBOUNDORDER_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8");

        mockMvc.perform(request)
                .andExpect(status().isForbidden());
    }

    private InboundOrderDTO createInboundOrderDTO() {
        return InboundOrderDTO.builder()
                .orderDate(LocalDate.of(2021,01,01))
                .batchStock(createBatchStock())
                .section(new SectionDTO("fs", 1l))
                .build();
    }

    private List<BatchDTO> createBatchStock(){
        List<BatchDTO> batchStock = new ArrayList<>();

        BatchDTO batchDTO1 = new BatchDTO(1l,1l,1,1,1,1,1,LocalDate.of(2021,01,01), LocalDateTime.of(2021,01,01,00,00,00),LocalDate.of(2021,01,01));
        BatchDTO batchDTO2 = new BatchDTO(2l,1l,1,1,1,1,1,LocalDate.of(2021,01,01),LocalDateTime.of(2021,01,01,00,00,00),LocalDate.of(2021,01,01));
        BatchDTO batchDTO3 = new BatchDTO(3l,1l,1,1,1,1,1,LocalDate.of(2021,01,01),LocalDateTime.of(2021,01,01,00,00,00),LocalDate.of(2021,01,01));

        batchStock.addAll(Arrays.asList(batchDTO1,batchDTO2,batchDTO3));

        return batchStock;
    }
}
