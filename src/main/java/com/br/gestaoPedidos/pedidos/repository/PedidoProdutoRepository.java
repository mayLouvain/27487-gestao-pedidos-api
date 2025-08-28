package com.br.gestaoPedidos.pedidos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.gestaoPedidos.pedidos.model.PedidoProdutoModel;

import jakarta.transaction.Transactional;

@Repository
public interface PedidoProdutoRepository extends JpaRepository<PedidoProdutoModel, Long> {
	List<PedidoProdutoModel> findByPedidoId(Long pedidoId);

	List<PedidoProdutoModel> findByProdutoId(Long produtoId);

	@Modifying
	@Transactional
	@Query("DELETE FROM PedidoProdutoModel p WHERE p.pedido.id = :pedidoId")
	void deleteAllByPedidoId(@Param("pedidoId") Long pedidoId);
}
