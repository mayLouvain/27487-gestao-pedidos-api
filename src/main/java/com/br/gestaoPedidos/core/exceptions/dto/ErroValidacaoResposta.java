package com.br.gestaoPedidos.core.exceptions.dto;

import org.springframework.validation.FieldError;

public record ErroValidacaoResposta(String campo, String mensagem) {
	public ErroValidacaoResposta(FieldError campoErro) {
		this(campoErro.getField(), campoErro.getDefaultMessage());
	}
}