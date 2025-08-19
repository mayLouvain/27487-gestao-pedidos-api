package com.br.gestaoPedidos.pedidos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.br.gestaoPedidos.pedidos.enums.StatusPedido;

public record PedidoResponseDTO(       
		UUID id,
        String cpf,
        LocalDateTime dataCriacao, 
        LocalDateTime dataAlteracao, 
        BigDecimal valorTotal,
        StatusPedido status,
        Set<PedidoProdutoDTO> produtos) {

}
