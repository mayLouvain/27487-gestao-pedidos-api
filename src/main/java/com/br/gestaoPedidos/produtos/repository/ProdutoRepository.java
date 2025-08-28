package com.br.gestaoPedidos.produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.gestaoPedidos.produtos.model.ProdutoModel;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {

	boolean existsByDescricaoIgnoreCase(String descricao);

}
