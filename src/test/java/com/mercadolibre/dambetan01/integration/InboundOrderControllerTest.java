package com.mercadolibre.dambetan01.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.dambetan01.Application;
import com.mercadolibre.dambetan01.controller.InboundOrderController;
import com.mercadolibre.dambetan01.dtos.BatchItemDTO;
import com.mercadolibre.dambetan01.dtos.InboundOrderDTO;
import com.mercadolibre.dambetan01.dtos.SectionDTO;
import com.mercadolibre.dambetan01.dtos.response.InboundOrderResponseDTO;
import com.mercadolibre.dambetan01.service.InboundOrderService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"SCOPE_SUFFIX = integration_test"})
@WebMvcTest(controllers = {InboundOrderController.class})
@AutoConfigureMockMvc
public class InboundOrderControllerTest {

    private static final String INBOUNDORDER_API = "/api/v1/fresh-products/inboundorder";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InboundOrderService inboundOrderService;

    @Test
    @DisplayName("Should create new Inbound Order")
    void createNewInboundOrderTest() throws Exception {
        InboundOrderDTO inboundOrderDTO = InboundOrderDTO.builder()
                .orderDate("01-01-2021")
                .orderNumber(1l)
                .batchStock(new ArrayList<BatchItemDTO>())
                .section(new SectionDTO())
                .build();
        String json = new ObjectMapper().writeValueAsString(inboundOrderDTO);

        InboundOrderResponseDTO inboundOrder = InboundOrderResponseDTO.builder().build();

        BDDMockito.given(inboundOrderService
                .createInboundOrder(
                        Mockito.any(InboundOrderDTO.class),
                        Mockito.any(String.class)))
                .willReturn(inboundOrder);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(INBOUNDORDER_API)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("batchStock", Matchers.hasSize(2)));
    }
}
