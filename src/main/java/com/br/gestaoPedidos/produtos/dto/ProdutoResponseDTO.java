package com.br.gestaoPedidos.produtos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.br.gestaoPedidos.produtos.enums.CategoriaProduto;

public record ProdutoResponseDTO(
		Long id,
		String descricao,
		BigDecimal preco,
		CategoriaProduto categoria,
        LocalDateTime dataCriacao, 
        LocalDateTime dataAlteracao
		) {

}