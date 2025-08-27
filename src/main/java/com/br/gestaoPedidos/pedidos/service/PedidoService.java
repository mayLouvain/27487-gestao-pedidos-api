package com.br.gestaoPedidos.pedidos.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.br.gestaoPedidos.pedidos.exception.PedidoNaoEncontradoException;
import com.br.gestaoPedidos.pedidos.model.PedidoModel;
import com.br.gestaoPedidos.pedidos.repository.PedidoRepository;
import com.br.gestaoPedidos.produtos.exception.ProdutoNaoEncontradoException;
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

	public PedidoModel buscarPorId(Long id) {
		return pedidoRepository.findById(id)
				.orElseThrow(() -> new PedidoNaoEncontradoException("pedido.naoEncontrado"));
	}

	public PedidoModel criar(PedidoModel pedido) {

		pedido.setDataCriacao(LocalDateTime.now());
		if (!Objects.isNull(pedido.getProdutos())) {
			atualizarCamposProdutosPedidos(pedido);
			pedido.setValorTotal(calcularValorTotal(pedido));
		}

		return pedidoRepository.save(pedido);
	}

	public PedidoModel alterar(Long id, PedidoModel pedido) {

		PedidoModel pedidoExistente = buscarPorId(id);

		pedidoExistente.setCpf(pedido.getCpf());
		pedidoExistente.setDataAlteracao(LocalDateTime.now());
		pedidoExistente.setStatus(pedido.getStatus());
		pedidoExistente.atualizarProdutos(pedido.getProdutos());

		if (!Objects.isNull(pedido.getProdutos())) {
			atualizarCamposProdutosPedidos(pedidoExistente);
			pedidoExistente.setValorTotal(calcularValorTotal(pedidoExistente));
			pedidoExistente.getProdutos().addAll(pedido.getProdutos());
		}

		return pedidoRepository.save(pedidoExistente);

	}

	public void deletar(Long id) {
		pedidoRepository.deleteById(id);
	}

	private void atualizarCamposProdutosPedidos(PedidoModel pedido) {
		pedido.getProdutos().stream().forEach(pedidoProduto -> {
			var produto = produtoRepository.findById(pedidoProduto.getId().getProdutoId())
					.orElseThrow(() -> new ProdutoNaoEncontradoException("produto.naoEncontrado"));
			pedidoProduto.setPedido(pedido);
			pedidoProduto.setProduto(produto);
		});
	}

	private BigDecimal calcularValorTotal(PedidoModel pedido) {
		return pedido.getProdutos().stream()
				.map(pedidoProduto -> pedidoProduto.getProduto().getPreco()
						.multiply(BigDecimal.valueOf(pedidoProduto.getQuantidade())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}
