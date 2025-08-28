package com.br.gestaoPedidos.relatorios.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RelatorioSemDadosException extends RuntimeException {

	private static final long serialVersionUID = 1029871223695302083L;

	public RelatorioSemDadosException(String mensagem) {
		super(mensagem);
	}
}