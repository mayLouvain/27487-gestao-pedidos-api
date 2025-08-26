package com.br.gestaoPedidos.pedidos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.gestaoPedidos.pedidos.model.PedidoProdutoModel;

@Repository
public interface PedidoProdutoRepository extends JpaRepository<PedidoProdutoModel, Long> {
	List<PedidoProdutoModel> findByPedidoId(Long pedidoId);

	List<PedidoProdutoModel> findByProdutoId(Long produtoId);
}
