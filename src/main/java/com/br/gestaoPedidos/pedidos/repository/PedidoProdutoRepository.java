package com.br.gestaoPedidos.pedidos.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.gestaoPedidos.pedidos.model.PedidoProdutoModel;

@Repository
public interface PedidoProdutoRepository extends JpaRepository<PedidoProdutoModel, UUID> {
    List<PedidoProdutoModel> findByPedidoId(UUID pedidoId);

    List<PedidoProdutoModel> findByProdutoId(UUID produtoId);
}
