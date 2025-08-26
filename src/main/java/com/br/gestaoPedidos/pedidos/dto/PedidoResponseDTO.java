package com.br.gestaoPedidos.pedidos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import com.br.gestaoPedidos.pedidos.enums.StatusPedido;

public record PedidoResponseDTO(       
		Long id,
        String cpf,
        LocalDateTime dataCriacao, 
        LocalDateTime dataAlteracao, 
        BigDecimal valorTotal,
        StatusPedido status,
        Set<PedidoProdutoDTO> produtos) {

}
