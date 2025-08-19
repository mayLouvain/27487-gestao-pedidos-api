package com.br.gestaoPedidos.pedidos.dto;

import java.math.BigDecimal;
import java.util.Set;

import com.br.gestaoPedidos.pedidos.enums.StatusPedido;

public record PedidoRequestDTO(       
        String cpf,
        BigDecimal valorTotal,
        StatusPedido status,
        Set<PedidoProdutoDTO> produtos) {

}
