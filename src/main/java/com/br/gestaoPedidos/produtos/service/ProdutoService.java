package com.br.gestaoPedidos.produtos.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.br.gestaoPedidos.pedidos.repository.PedidoProdutoRepository;
import com.br.gestaoPedidos.produtos.exception.ProdutoJaExisteException;
import com.br.gestaoPedidos.produtos.exception.ProdutoVinculadoPedidoException;
import com.br.gestaoPedidos.produtos.model.ProdutoModel;
import com.br.gestaoPedidos.produtos.repository.ProdutoRepository;

@Service
public class ProdutoService {

	private ProdutoRepository produtoRepository;
	private PedidoProdutoRepository pedidoProdutoRepository;

	public ProdutoService(ProdutoRepository produtoRepository, PedidoProdutoRepository pedidoProdutoRepository) {
		this.produtoRepository = produtoRepository;
		this.pedidoProdutoRepository = pedidoProdutoRepository;
	}

	public List<ProdutoModel> listar() {
		return produtoRepository.findAll();
	}

	public ProdutoModel buscarPorId(Long id) {
		return produtoRepository.findById(id).orElseThrow(() -> new ProdutoJaExisteException("produto.naoEncontrado"));

	}

	public ProdutoModel criar(ProdutoModel produto) {
		boolean existeProduto = produtoRepository.existsByDescricaoIgnoreCase(produto.getDescricao());

		if (existeProduto) {
			throw new ProdutoJaExisteException("produto.jaExistente");
		}

		produto.setDataCriacao(LocalDateTime.now());

		return produtoRepository.save(produto);

	}

	public ProdutoModel alterar(Long id, ProdutoModel produto) {
		ProdutoModel produtoExistente = produtoRepository.findById(id)
				.orElseThrow(() -> new ProdutoJaExisteException("produto.naoEncontrado"));

		produto.setId(id);
		produto.setDataCriacao(produtoExistente.getDataCriacao());
		produto.setDataAlteracao(LocalDateTime.now());

		return produtoRepository.save(produto);
	}

	public void deletar(Long id) {

		if (!this.pedidoProdutoRepository.findByProdutoId(id).isEmpty()) {
			throw new ProdutoVinculadoPedidoException("produto.vinculadoPedido");
		}

		produtoRepository.deleteById(id);
	}

}
