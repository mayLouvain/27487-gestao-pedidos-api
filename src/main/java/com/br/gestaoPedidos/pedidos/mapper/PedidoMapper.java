package com.br.gestaoPedidos.pedidos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.br.gestaoPedidos.pedidos.dto.PedidoRequestDTO;
import com.br.gestaoPedidos.pedidos.dto.PedidoResponseDTO;
import com.br.gestaoPedidos.pedidos.model.PedidoModel;

@Mapper(componentModel = "spring", uses = PedidoProdutoMapper.class)
public interface PedidoMapper {

	PedidoResponseDTO toResponseDTO(PedidoModel pedido);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "dataCriacao", ignore = true)
	@Mapping(target = "dataAlteracao", ignore = true)
	PedidoModel toEntity(PedidoRequestDTO dto);

}