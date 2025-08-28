package com.br.gestaoPedidos.relatorios.service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.br.gestaoPedidos.pedidos.mapper.PedidoMapper;
import com.br.gestaoPedidos.pedidos.model.PedidoModel;
import com.br.gestaoPedidos.pedidos.repository.PedidoRepository;
import com.br.gestaoPedidos.produtos.mapper.ProdutoMapper;
import com.br.gestaoPedidos.relatorios.dto.RelatorioAgrupadoProdutoDTO;
import com.br.gestaoPedidos.relatorios.dto.RelatorioDTO;
import com.br.gestaoPedidos.relatorios.exception.RelatorioSemDadosException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class RelatorioService {

	private final PedidoRepository pedidoRepository;
	private final PedidoMapper pedidoMapper;
	private final ProdutoMapper produtoMapper;

	private final static String CAMINHO_GESTAO_PEDIDOS_RELATORIO = "/relatorios/gestao_pedidos_relatorio.jrxml";
	private final static String CAMINHO_GESTAO_PEDIDOS_GRUPO_PRODUTOS_RELATORIO = "/relatorios/gestao_pedidos_grupo_produto_relatorio.jrxml";

	private final static String CAMINHO_SUBREPORT_PRODUTOS = "/relatorios/subreport_produtos.jrxml";
	private final static String CAMINHO_SUBREPORT_PEDIDOS = "/relatorios/subreport_pedidos.jrxml";

	public RelatorioService(PedidoRepository pedidoRepository, PedidoMapper pedidoMapper, ProdutoMapper produtoMapper) {
		this.pedidoRepository = pedidoRepository;
		this.pedidoMapper = pedidoMapper;
		this.produtoMapper = produtoMapper;
	}

	public byte[] gerarRelatorioPedidos(RelatorioDTO dto) throws JRException {
		List<PedidoModel> listaPedidos = this.pedidoRepository.findByDataCriacaoBetween(
				dto.dataInicio().atZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime(),
				dto.dataFim().atZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime());

		if (listaPedidos.size() == 0) {
			throw new RelatorioSemDadosException("relatorio.vazio");
		}
		if (dto.agrupaProduto()) {
			return gerarRelatorioAgrupadoProdutos(listaPedidos, dto);
		} else {
			return gerarRelatorioPedidos(listaPedidos, dto);
		}

	}

	private byte[] gerarRelatorioAgrupadoProdutos(List<PedidoModel> listaPedidos, RelatorioDTO dto) throws JRException {
		List<RelatorioAgrupadoProdutoDTO> listaProdutosAgrupados = listaPedidos.stream()
				.flatMap(pedido -> pedido.getProdutos().stream()).collect(Collectors.groupingBy(p -> p.getProduto()))
				.entrySet().stream().map(entry -> {
					var produtoDTO = produtoMapper.toResponseDTO(entry.getKey());
					var pedidosDTO = entry.getValue().stream()
							.map(pedido -> pedidoMapper.toResponseDTO(pedido.getPedido())).collect(Collectors.toList());
					return new RelatorioAgrupadoProdutoDTO(produtoDTO, pedidosDTO);
				}).toList();
		Map<String, Object> parametros = new HashMap<>();

		InputStream subreportPedidosStream = getClass().getResourceAsStream(CAMINHO_SUBREPORT_PEDIDOS);
		JasperReport subreportPedidosJasper;
		subreportPedidosJasper = JasperCompileManager.compileReport(subreportPedidosStream);

		parametros.put("produtos", listaProdutosAgrupados);
		parametros.put("subreport_pedidos", subreportPedidosJasper);
		parametros.put("periodo", getDataFormatada(dto.dataInicio().toLocalDateTime()).concat(" a ")
				.concat(getDataFormatada(dto.dataFim().toLocalDateTime())));

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaProdutosAgrupados);

		return gerarRelatorio(dataSource, parametros, CAMINHO_GESTAO_PEDIDOS_GRUPO_PRODUTOS_RELATORIO);

	}

	private byte[] gerarRelatorioPedidos(List<PedidoModel> listaPedidos, RelatorioDTO dto) throws JRException {

		Map<String, Object> parametros = new HashMap<>();

		InputStream subreportProdutosStream = getClass().getResourceAsStream(CAMINHO_SUBREPORT_PRODUTOS);
		JasperReport subreportProdutosJasper;
		subreportProdutosJasper = JasperCompileManager.compileReport(subreportProdutosStream);

		parametros.put("pedidos", listaPedidos);
		parametros.put("subreport_produtos", subreportProdutosJasper);
		parametros.put("periodo", getDataFormatada(dto.dataInicio().toLocalDateTime()).concat(" a ")
				.concat(getDataFormatada(dto.dataFim().toLocalDateTime())));

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaPedidos);

		return gerarRelatorio(dataSource, parametros, CAMINHO_GESTAO_PEDIDOS_RELATORIO);

	}

	private byte[] gerarRelatorio(JRBeanCollectionDataSource dataSource, Map<String, Object> parametros,
			String relatorioPrincipal) throws JRException {

		JasperPrint jasperPrint;
		InputStream relatorioStream = getClass().getResourceAsStream(relatorioPrincipal);
		JasperReport relatorioJasper = JasperCompileManager.compileReport(relatorioStream);
		jasperPrint = JasperFillManager.fillReport(relatorioJasper, parametros, dataSource);
		return JasperExportManager.exportReportToPdf(jasperPrint);

	}

	private String getDataFormatada(LocalDateTime data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return data.format(formatter);

	}

}
