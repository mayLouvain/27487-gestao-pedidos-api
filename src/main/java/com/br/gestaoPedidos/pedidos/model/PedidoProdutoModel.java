package com.br.gestaoPedidos.pedidos.model;

import java.math.BigDecimal;

import com.br.gestaoPedidos.produtos.model.ProdutoModel;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos_produtos")
public class PedidoProdutoModel {

	@EmbeddedId
	private PedidoProdutoId id = new PedidoProdutoId();

	@ManyToOne
	@MapsId("pedidoId")
	private PedidoModel pedido;

	@ManyToOne
	@MapsId("produtoId")
	private ProdutoModel produto;

	private Integer quantidade;

	public PedidoProdutoId getId() {
		return id;
	}

	public PedidoModel getPedido() {
		return pedido;
	}

	public ProdutoModel getProduto() {
		return produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setId(PedidoProdutoId id) {
		this.id = id;
	}

	public void setPedido(PedidoModel pedido) {
		this.pedido = pedido;
	}

	public void setProduto(ProdutoModel produto) {
		this.produto = produto;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	// Getters Para Relatório
	public BigDecimal getPrecoProduto() {
	    return produto != null ? produto.getPreco() : BigDecimal.ZERO;
	}
	
	public String getDescricaoProduto() {
	    return produto != null ? produto.getDescricao() : "";
	}

	public String getCategoriaProduto() {
	    return produto != null ? produto.getCategoria().toString() : "";
	}
}
