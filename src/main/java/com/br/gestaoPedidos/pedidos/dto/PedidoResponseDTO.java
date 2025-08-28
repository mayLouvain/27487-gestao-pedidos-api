package com.br.gestaoPedidos.pedidos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import com.br.gestaoPedidos.pedidos.enums.StatusPedido;

public class PedidoResponseDTO {
	private Long id;
	private String cpf;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataAlteracao;
	private BigDecimal valorTotal;
	private StatusPedido status;
	private Set<PedidoProdutoDTO> produtos;

	public PedidoResponseDTO(Long id, String cpf, LocalDateTime dataCriacao, LocalDateTime dataAlteracao,
			BigDecimal valorTotal, StatusPedido status, Set<PedidoProdutoDTO> produtos) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.dataCriacao = dataCriacao;
		this.dataAlteracao = dataAlteracao;
		this.valorTotal = valorTotal;
		this.status = status;
		this.produtos = produtos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public Set<PedidoProdutoDTO> getProdutos() {
		return produtos;
	}

	public void setProdutos(Set<PedidoProdutoDTO> produtos) {
		this.produtos = produtos;
	}

	public String getStatusFormatado() {
		return status != null ? status.name() : null;
	}

	public String getDataCriacaoFormatada() {
		return dataCriacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

}
