package com.br.gestaoPedidos.pedidos.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.br.gestaoPedidos.pedidos.enums.StatusPedido;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
public class PedidoModel {

	@Id
	@GeneratedValue
	private UUID id;

	private String cpf;

	private LocalDateTime dataCriacao;

	private LocalDateTime dataAlteracao;

	private BigDecimal valorTotal;

	@Enumerated(EnumType.STRING)
	private StatusPedido status;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<PedidoProdutoModel> produtos;

	public void atualizarProdutos(Set<PedidoProdutoModel> produtos) {
		if (!this.produtos.isEmpty()) {
			this.produtos.clear();
			this.produtos.addAll(produtos);
		}
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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

	public Set<PedidoProdutoModel> getProdutos() {
		return produtos;
	}

	public void setProdutos(Set<PedidoProdutoModel> produtos) {
		this.produtos = produtos;
	}

}