package com.br.gestaoPedidos.relatorios.dto;

import java.util.List;

import com.br.gestaoPedidos.pedidos.dto.PedidoResponseDTO;
import com.br.gestaoPedidos.produtos.dto.ProdutoResponseDTO;

public class RelatorioAgrupadoProdutoDTO {
	private ProdutoResponseDTO produto;
	private List<PedidoResponseDTO> pedidos;

	public RelatorioAgrupadoProdutoDTO(ProdutoResponseDTO produto, List<PedidoResponseDTO> pedidos) {
		super();
		this.produto = produto;
		this.pedidos = pedidos;
	}

	public ProdutoResponseDTO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoResponseDTO produto) {
		this.produto = produto;
	}

	public List<PedidoResponseDTO> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<PedidoResponseDTO> pedidos) {
		this.pedidos = pedidos;
	}

}
