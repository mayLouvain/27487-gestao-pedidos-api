package com.br.gestaoPedidos.pedidos.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.gestaoPedidos.pedidos.model.PedidoModel;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Long> {
	
    List<PedidoModel> findByDataCriacaoBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

}
