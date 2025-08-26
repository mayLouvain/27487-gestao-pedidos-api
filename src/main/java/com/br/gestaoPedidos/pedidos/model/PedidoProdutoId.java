package com.br.gestaoPedidos.pedidos.model;

import java.util.Objects;

import jakarta.persistence.Embeddable;
import lombok.Setter;

@Embeddable
@Setter
public class PedidoProdutoId {

	private Long pedidoId;
	private Long produtoId;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof PedidoProdutoId))
			return false;
		PedidoProdutoId that = (PedidoProdutoId) o;
		return Objects.equals(pedidoId, that.pedidoId) && Objects.equals(produtoId, that.produtoId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(pedidoId, produtoId);
	}

	public Long getPedidoId() {
		return pedidoId;
	}

	public Long getProdutoId() {
		return produtoId;
	}

}
