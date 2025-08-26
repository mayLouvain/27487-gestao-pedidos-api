package com.br.gestaoPedidos.produtos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ProdutoJaExisteException extends RuntimeException {
	private static final long serialVersionUID = 8693757953778069213L;

	public ProdutoJaExisteException(String mensagem) {
		super(mensagem);
	}
}