package com.br.gestaoPedidos.pedidos.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.br.gestaoPedidos.pedidos.dto.PedidoProdutoDTO;
import com.br.gestaoPedidos.pedidos.model.PedidoProdutoModel;

@Mapper(componentModel = "spring")
public interface PedidoProdutoMapper {

	@Mapping(target = "produtoId", source = "id.produtoId")
    PedidoProdutoDTO toDTO(PedidoProdutoModel produto);
    
	@Mapping(target = "id.produtoId", source = "produtoId")
	@Mapping(target = "pedido", ignore = true)
	@Mapping(target = "produto", ignore = true)
    PedidoProdutoModel toEntity(PedidoProdutoDTO dto);
}
