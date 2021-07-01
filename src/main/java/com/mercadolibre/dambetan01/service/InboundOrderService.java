package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.dtos.InboundOrderDTO;
import com.mercadolibre.dambetan01.dtos.response.InboundOrderResponseDTO;

public interface InboundOrderService {

    InboundOrderResponseDTO createInboundOrder(InboundOrderDTO inboundOrderDTO, Long id);

    InboundOrderResponseDTO updateInboundOrder(InboundOrderDTO inboundOrderDTO, Long id);
}
