package com.br.gestaoPedidos.relatorios.dto;

import java.util.UUID;

public record RelatorioDTO(
					String dataInicio,
					String dataFim,
					UUID idProduto
        		) {

}
