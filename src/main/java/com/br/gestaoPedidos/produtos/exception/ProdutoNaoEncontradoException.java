package com.br.gestaoPedidos.produtos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProdutoNaoEncontradoException extends RuntimeException {
	private static final long serialVersionUID = 678939943403064734L;

	public ProdutoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
}