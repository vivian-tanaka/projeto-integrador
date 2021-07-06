package com.mercadolibre.projetointegrador.integration;

import com.mercadolibre.projetointegrador.Application;
import com.mercadolibre.projetointegrador.controller.InboundOrderController;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"SCOPE_SUFFIX = integration_test"})
@WebMvcTest(controllers = {InboundOrderController.class})
@AutoConfigureMockMvc
public class InboundOrderControllerTest {

    private static final String INBOUNDORDER_API = "/api/v1/fresh-products/inboundorder";

    @Autowired
    private MockMvc mockMvc;

//    @MockBean
//    private InboundOrderService inboundOrderService;

//    @Test
//    @DisplayName("Should create new Inbound Order")
//    void createNewInboundOrderTest() throws Exception {
//        InboundOrderDTO inboundOrderDTO = InboundOrderDTO.builder()
//                .orderDate(LocalDate.now())
//                .orderNumber(1l)
//                .batchStock(new ArrayList<BatchDTO>())
//                .section(new SectionDTO())
//                .build();
//        String json = new ObjectMapper().writeValueAsString(inboundOrderDTO);
//
//        InboundOrderResponseDTO inboundOrder = InboundOrderResponseDTO.builder().build();
//
//        BDDMockito.given(inboundOrderService
//                .createInboundOrder(
//                        Mockito.any(InboundOrderDTO.class),
//                        Mockito.any(String.class)))
//                .willReturn(inboundOrder);
//
//        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
//                .post(INBOUNDORDER_API)
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json);
//
//        mockMvc.perform(request)
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("batchStock", Matchers.hasSize(2)));
//    }
}
