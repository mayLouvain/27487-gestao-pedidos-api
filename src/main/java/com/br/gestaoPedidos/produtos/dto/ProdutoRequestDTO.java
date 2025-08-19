package com.br.gestaoPedidos.produtos.dto;

import java.math.BigDecimal;

import com.br.gestaoPedidos.produtos.enums.CategoriaProduto;

public record ProdutoRequestDTO(
		String descricao,
		BigDecimal preco,
		CategoriaProduto categoria) {

}