package com.br.gestaoPedidos.pedidos.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.br.gestaoPedidos.pedidos.model.PedidoModel;
import com.br.gestaoPedidos.pedidos.repository.PedidoRepository;
import com.br.gestaoPedidos.produtos.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {

	private final PedidoRepository pedidoRepository;
	private final ProdutoRepository produtoRepository;

	public PedidoService(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository) {
		this.pedidoRepository = pedidoRepository;
		this.produtoRepository = produtoRepository;
	}

	public List<PedidoModel> listar() {
		return pedidoRepository.findAll();
	}

	public PedidoModel buscarPorId(UUID id) {
		return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
	}

	public PedidoModel criar(PedidoModel pedido) {

		pedido.setDataCriacao(LocalDateTime.now());
		if (!Objects.isNull(pedido.getProdutos())) {
			atualizarCamposProdutosPedidos(pedido);
		}

		return pedidoRepository.save(pedido);
	}

	public PedidoModel alterar(UUID id, PedidoModel pedido) {

		PedidoModel pedidoExistente = pedidoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

		pedido.setId(id);
		pedido.setDataCriacao(pedidoExistente.getDataCriacao());
		pedido.setDataAlteracao(LocalDateTime.now());

		if (!Objects.isNull(pedido.getProdutos())) {
			atualizarCamposProdutosPedidos(pedido);
		}


		return pedidoRepository.save(pedido);

	}

	public void deletar(UUID id) {
		pedidoRepository.deleteById(id);
	}

	private void atualizarCamposProdutosPedidos(PedidoModel pedido) {
		pedido.getProdutos().stream().forEach(pedidoProduto -> {
			var produto = produtoRepository.findById(pedidoProduto.getId().getProdutoId()).orElseThrow();
			pedidoProduto.setPedido(pedido);
			pedidoProduto.setProduto(produto);
		});
	}
}
