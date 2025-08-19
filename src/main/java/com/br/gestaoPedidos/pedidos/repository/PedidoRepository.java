package com.br.gestaoPedidos.pedidos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.gestaoPedidos.pedidos.model.PedidoModel;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, UUID> {

}
