package com.br.gestaoPedidos.relatorios.dto;

import java.time.OffsetDateTime;

import jakarta.validation.constraints.NotNull;

public record RelatorioDTO(

		@NotNull(message = "{relatorio.dataInicio.NotNull}") 
		OffsetDateTime dataInicio,

		@NotNull(message = "{relatorio.dataFim.NotNull}")
		OffsetDateTime dataFim,

		boolean agrupaProduto) {

}
