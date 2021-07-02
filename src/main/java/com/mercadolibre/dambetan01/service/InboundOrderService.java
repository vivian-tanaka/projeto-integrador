package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.dtos.InboundOrderDTO;
import com.mercadolibre.dambetan01.dtos.response.InboundOrderResponseDTO;

public interface InboundOrderService {

    InboundOrderResponseDTO createInboundOrder(InboundOrderDTO inboundOrderDTO, String username);

    InboundOrderResponseDTO updateInboundOrder(InboundOrderDTO inboundOrderDTO, String username);
}
