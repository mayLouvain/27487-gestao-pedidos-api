package com.br.gestaoPedidos.pedidos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PedidoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 29215910289907162L;

	public PedidoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
}