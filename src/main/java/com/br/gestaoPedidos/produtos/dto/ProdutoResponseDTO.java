package com.br.gestaoPedidos.produtos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.br.gestaoPedidos.produtos.enums.CategoriaProduto;

public class ProdutoResponseDTO {
	private Long id;
	private String descricao;
	private BigDecimal preco;
	private CategoriaProduto categoria;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataAlteracao;

	public ProdutoResponseDTO(Long id, String descricao, BigDecimal preco, CategoriaProduto categoria,
			LocalDateTime dataCriacao, LocalDateTime dataAlteracao) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
		this.dataCriacao = dataCriacao;
		this.dataAlteracao = dataAlteracao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public CategoriaProduto getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaProduto categoria) {
		this.categoria = categoria;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(LocalDateTime dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public String getDataCriacaoFormatada() {
		return dataCriacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public String getCategoriaFormatada() {
		return categoria != null ? categoria.name() : null;
	}
}