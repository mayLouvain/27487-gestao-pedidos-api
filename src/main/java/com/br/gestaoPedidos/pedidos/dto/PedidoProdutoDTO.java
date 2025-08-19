package com.br.gestaoPedidos.pedidos.dto;

import java.util.UUID;

public record PedidoProdutoDTO(
		UUID  produtoId,
        Integer quantidade
) {}
