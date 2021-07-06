package com.mercadolibre.projetointegrador.service;

import com.mercadolibre.projetointegrador.dtos.InboundOrderDTO;
import com.mercadolibre.projetointegrador.dtos.response.InboundOrderResponseDTO;

public interface InboundOrderService {

    InboundOrderResponseDTO createInboundOrder(InboundOrderDTO inboundOrderDTO, String username);

    InboundOrderResponseDTO updateInboundOrder(InboundOrderDTO inboundOrderDTO, String username);
}
