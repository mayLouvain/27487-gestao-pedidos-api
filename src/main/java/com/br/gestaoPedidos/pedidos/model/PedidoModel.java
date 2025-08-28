package com.br.gestaoPedidos.pedidos.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.br.gestaoPedidos.pedidos.enums.StatusPedido;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
public class PedidoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String cpf;

	private LocalDateTime dataCriacao;

	private LocalDateTime dataAlteracao;

	private BigDecimal valorTotal;

	@Enumerated(EnumType.STRING)
	private StatusPedido status;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PedidoProdutoModel> produtos;

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

	public List<PedidoProdutoModel> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<PedidoProdutoModel> produtos) {
		this.produtos = produtos;
	}

}