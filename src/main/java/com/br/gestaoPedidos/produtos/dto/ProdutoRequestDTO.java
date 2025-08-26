package com.br.gestaoPedidos.produtos.dto;

import java.math.BigDecimal;

import com.br.gestaoPedidos.produtos.enums.CategoriaProduto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoRequestDTO(
		
	    @NotBlank(message = "{produto.descricao.NotBlank}")
	    String descricao,

	    @NotNull(message = "{produto.preco.NotNull}")
	    @DecimalMin(value = "0.1", inclusive = true, message = "{produto.preco.DecimalMin}")
		@NotNull BigDecimal preco,
		
	    @NotNull(message = "{produto.endereco.NotNull}")
	    CategoriaProduto categoria
) {}