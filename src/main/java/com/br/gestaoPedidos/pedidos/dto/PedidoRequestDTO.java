package com.br.gestaoPedidos.pedidos.dto;

import java.math.BigDecimal;
import java.util.Set;

import com.br.gestaoPedidos.pedidos.enums.StatusPedido;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PedidoRequestDTO(       

		@NotBlank(message = "{pedido.cpf.NotBlank}")
		String cpf,
        
	    @NotNull(message = "{pedido.valorTotal.NotNull}")
	    @DecimalMin(value = "0.1", inclusive = true, message = "{pedido.valorTotal.DecimalMin}")
		BigDecimal valorTotal,
        
	    @NotNull(message = "{pedido.status.NotNull}")
        StatusPedido status,
        
        @NotEmpty(message = "{pedido.produtos.NotEmpty}")
        Set<PedidoProdutoDTO> produtos) {

}
