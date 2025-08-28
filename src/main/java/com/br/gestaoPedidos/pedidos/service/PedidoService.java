package com.br.gestaoPedidos.pedidos.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.br.gestaoPedidos.pedidos.exception.PedidoNaoEncontradoException;
import com.br.gestaoPedidos.pedidos.model.PedidoModel;
import com.br.gestaoPedidos.pedidos.model.PedidoProdutoModel;
import com.br.gestaoPedidos.pedidos.repository.PedidoProdutoRepository;
import com.br.gestaoPedidos.pedidos.repository.PedidoRepository;
import com.br.gestaoPedidos.produtos.exception.ProdutoNaoEncontradoException;
import com.br.gestaoPedidos.produtos.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {

	private final PedidoRepository pedidoRepository;
	private final ProdutoRepository produtoRepository;
	private final PedidoProdutoRepository pedidoProdutoRepository;

	public PedidoService(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository,
			PedidoProdutoRepository pedidoProdutoRepository) {
		this.pedidoRepository = pedidoRepository;
		this.produtoRepository = produtoRepository;
		this.pedidoProdutoRepository = pedidoProdutoRepository;
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
			var produtos = atualizarCamposProdutosPedidos(pedido, null);
			pedido.getProdutos().clear();
			pedido.getProdutos().addAll(produtos);
			pedido.setValorTotal(calcularValorTotal(pedido));
		}

		return pedidoRepository.save(pedido);
	}

	public PedidoModel alterar(Long id, PedidoModel pedido) {

		PedidoModel pedidoExistente = buscarPorId(id);
		pedidoProdutoRepository.deleteAllByPedidoId(id);

		pedidoExistente.setCpf(pedido.getCpf());
		pedidoExistente.setDataAlteracao(LocalDateTime.now());
		pedidoExistente.setStatus(pedido.getStatus());

		if (!Objects.isNull(pedido.getProdutos())) {
			pedidoExistente.getProdutos().addAll(atualizarCamposProdutosPedidos(pedido, pedidoExistente));
			pedidoExistente.setValorTotal(calcularValorTotal(pedidoExistente));
		}
		return pedidoRepository.save(pedidoExistente);

	}

	public void deletar(Long id) {
		pedidoRepository.deleteById(id);
	}

	private List<PedidoProdutoModel> atualizarCamposProdutosPedidos(PedidoModel pedido, PedidoModel pedidoExistente) {
		List<PedidoProdutoModel> novosProdutos = pedido.getProdutos().stream().map(dto -> {
			var produto = produtoRepository.findById(dto.getId().getProdutoId())
					.orElseThrow(() -> new ProdutoNaoEncontradoException("produto.naoEncontrado"));

			PedidoProdutoModel pedidoProduto = new PedidoProdutoModel();
			pedidoProduto.setPedido(!Objects.isNull(pedidoExistente) ? pedidoExistente : pedido);
			pedidoProduto.setProduto(produto);
			pedidoProduto.setQuantidade(dto.getQuantidade());
			return pedidoProduto;
		}).toList();

		return novosProdutos;
	}

	private BigDecimal calcularValorTotal(PedidoModel pedido) {
		return pedido.getProdutos().stream()
				.map(pedidoProduto -> pedidoProduto.getProduto().getPreco()
						.multiply(BigDecimal.valueOf(pedidoProduto.getQuantidade())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}
