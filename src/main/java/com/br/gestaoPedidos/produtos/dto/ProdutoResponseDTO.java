package com.br.gestaoPedidos.produtos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.br.gestaoPedidos.produtos.enums.CategoriaProduto;

public record ProdutoResponseDTO(
		UUID id,
		String descricao,
		BigDecimal preco,
		CategoriaProduto categoria,
        LocalDateTime dataCriacao, 
        LocalDateTime dataAlteracao
		) {

}