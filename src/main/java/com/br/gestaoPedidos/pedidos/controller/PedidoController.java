package com.br.gestaoPedidos.pedidos.controller;

import java.util.List;
import java.util.UUID;
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

import com.br.gestaoPedidos.pedidos.dto.PedidoRequestDTO;
import com.br.gestaoPedidos.pedidos.dto.PedidoResponseDTO;
import com.br.gestaoPedidos.pedidos.mapper.PedidoMapper;
import com.br.gestaoPedidos.pedidos.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "Pedidos", description = "Operações de CRUD de Pedidos")
public class PedidoController {

	@Autowired
	private PedidoService service;

	@Autowired
	private PedidoMapper pedidoMapper;

	@GetMapping
	@Operation(summary = "Lista todos os pedidos")
	public ResponseEntity<List<PedidoResponseDTO>> listar() {

		var listaPedidoDTO = service.listar().stream().map(pedidoMapper::toResponseDTO).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(listaPedidoDTO);
	}

	@GetMapping("{id}")
	@Operation(summary = "Busca um pedido pelo identificador")
	public ResponseEntity<PedidoResponseDTO> buscarPorId(@PathVariable UUID id) {

		return ResponseEntity.status(HttpStatus.OK).body(pedidoMapper.toResponseDTO(service.buscarPorId(id)));
	}

	@PostMapping(value = "/criar")
	@Operation(summary = "Cria um novo pedido")
	public ResponseEntity<PedidoResponseDTO> criar(@RequestBody PedidoRequestDTO dto) {

		var pedidoResponseDTO = pedidoMapper.toResponseDTO(service.criar(pedidoMapper.toEntity(dto)));

		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoResponseDTO);
	}

	@PutMapping("/alterar/{id}")
	@Operation(summary = "Atualiza um pedido existente")
	public ResponseEntity<PedidoResponseDTO> alterar(@PathVariable UUID id, @RequestBody PedidoRequestDTO dto) {

		var pedidoResponseDTO = pedidoMapper.toResponseDTO(service.alterar(id, pedidoMapper.toEntity(dto)));

		return ResponseEntity.status(HttpStatus.OK).body(pedidoResponseDTO);
	}

	@DeleteMapping("/deletar/{id}")
	@Operation(summary = "Deleta um pedido existente")
	public void deletar(@PathVariable UUID id) {
		service.deletar(id);
	}

}
