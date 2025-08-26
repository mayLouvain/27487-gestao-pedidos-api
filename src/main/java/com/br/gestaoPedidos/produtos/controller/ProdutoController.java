package com.br.gestaoPedidos.produtos.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.gestaoPedidos.produtos.dto.ProdutoRequestDTO;
import com.br.gestaoPedidos.produtos.dto.ProdutoResponseDTO;
import com.br.gestaoPedidos.produtos.mapper.ProdutoMapper;
import com.br.gestaoPedidos.produtos.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/produtos")
@Tag(name = "Produtos", description = "Operações de CRUD de Produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@Autowired
	private ProdutoMapper produtoMapper;

	@GetMapping
	@Operation(summary = "Lista todos os produtos")
	public ResponseEntity<List<ProdutoResponseDTO>> listar() {
		var listaProdutoDTO = service.listar().stream().map(produtoMapper::toResponseDTO).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(listaProdutoDTO);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Busca um produto pelo identificador")
	public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(produtoMapper.toResponseDTO(service.buscarPorId(id)));
	}

	@PostMapping(value = "/criar")
	@Operation(summary = "Cria um novo produto")
	public ResponseEntity<ProdutoResponseDTO> criar(@Valid @RequestBody ProdutoRequestDTO dto) {
		var produtoModel = produtoMapper.toEntity(dto);
		var produtoResponseDTO = produtoMapper.toResponseDTO(service.criar(produtoModel));
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoResponseDTO);
	}

	@PutMapping("/alterar/{id}")
	@Operation(summary = "Atualiza um produto existente")
	public ResponseEntity<ProdutoResponseDTO> alterar(@PathVariable Long id, @RequestBody ProdutoRequestDTO dto) {
		var pedidoResponseDTO = produtoMapper.toResponseDTO(service.alterar(id, produtoMapper.toEntity(dto)));

		return ResponseEntity.status(HttpStatus.OK).body(pedidoResponseDTO);
	}

	@DeleteMapping("/deletar/{id}")
	@Operation(summary = "Deleta um produto existente")
	public void deletar(@PathVariable Long id) {
		service.deletar(id);
	}
}
