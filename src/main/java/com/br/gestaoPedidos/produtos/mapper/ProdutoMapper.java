package com.br.gestaoPedidos.produtos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.br.gestaoPedidos.produtos.dto.ProdutoRequestDTO;
import com.br.gestaoPedidos.produtos.dto.ProdutoResponseDTO;
import com.br.gestaoPedidos.produtos.model.ProdutoModel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProdutoMapper {

	ProdutoResponseDTO toResponseDTO(ProdutoModel model);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "dataCriacao", ignore = true)
	@Mapping(target = "dataAlteracao", ignore = true)
	ProdutoModel toEntity(ProdutoRequestDTO dto);
}
