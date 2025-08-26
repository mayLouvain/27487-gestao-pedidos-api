package com.br.gestaoPedidos.produtos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProdutoVinculadoPedidoException extends RuntimeException {
	private static final long serialVersionUID = -2917986699164942431L;

	public ProdutoVinculadoPedidoException(String mensagem) {
		super(mensagem);
	}
}