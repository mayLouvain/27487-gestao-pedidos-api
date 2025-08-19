package com.br.gestaoPedidos.produtos.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.br.gestaoPedidos.produtos.model.ProdutoModel;
import com.br.gestaoPedidos.produtos.repository.ProdutoRepository;

@Service
public class ProdutoService {

	private ProdutoRepository produtoRepository;

	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	public List<ProdutoModel> listar() {
		return produtoRepository.findAll();
	}

	public ProdutoModel buscarPorId(UUID id) {
		return produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

	}

	public ProdutoModel criar(ProdutoModel produto) {
		produto.setDataCriacao(LocalDateTime.now());

		return produtoRepository.save(produto);

	}

	public ProdutoModel alterar(UUID id, ProdutoModel produto) {
		ProdutoModel produtoExistente = produtoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

		produto.setId(id);
		produto.setDataCriacao(produtoExistente.getDataCriacao());
		produto.setDataAlteracao(LocalDateTime.now());

		return produtoRepository.save(produto);
	}

	public void deletar(UUID id) {
		produtoRepository.deleteById(id);
	}

}
