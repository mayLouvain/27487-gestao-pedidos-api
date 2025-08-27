package com.br.gestaoPedidos.pedidos.dto;

import java.util.Set;

import com.br.gestaoPedidos.pedidos.enums.StatusPedido;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PedidoRequestDTO(       

		@NotBlank(message = "{pedido.cpf.NotBlank}")
		String cpf,
        
	    @NotNull(message = "{pedido.status.NotNull}")
        StatusPedido status,
        
        @NotEmpty(message = "{pedido.produtos.NotEmpty}")
        Set<PedidoProdutoDTO> produtos) {

}
