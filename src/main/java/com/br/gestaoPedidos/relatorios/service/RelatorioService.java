package com.br.gestaoPedidos.relatorios.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.br.gestaoPedidos.pedidos.model.PedidoModel;
import com.br.gestaoPedidos.pedidos.repository.PedidoProdutoRepository;
import com.br.gestaoPedidos.pedidos.repository.PedidoRepository;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class RelatorioService {

	private final PedidoProdutoRepository pedidoProdutoRepository;
	private final PedidoRepository pedidoRepository;

	private final static String CAMINHO_GESTAO_PEDIDOS_RELATORIO = "/relatorios/gestao_pedidos_relatorio.jrxml";
	private final static String CAMINHO_SUBREPORT_PEDIDOS = "/relatorios/subreport_pedidos.jrxml";

	public RelatorioService(PedidoProdutoRepository pedidoProdutoRepository, PedidoRepository pedidoRepository) {
		this.pedidoProdutoRepository = pedidoProdutoRepository;
		this.pedidoRepository = pedidoRepository;
	}

	public byte[] gerarRelatorioPedidos() throws Exception {
		List<PedidoModel> listaPedidos = this.pedidoRepository.findAll();

		Map<String, Object> parametros = new HashMap<>();

		InputStream subreportPedidosStream = getClass().getResourceAsStream(CAMINHO_SUBREPORT_PEDIDOS);
		JasperReport subreportPedidosJasper = JasperCompileManager.compileReport(subreportPedidosStream);

		parametros.put("pedidos", listaPedidos);
		parametros.put("subreport_pedidos", subreportPedidosJasper);

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaPedidos);

		return gerarRelatorio(dataSource, parametros, CAMINHO_GESTAO_PEDIDOS_RELATORIO);
	}

	private byte[] gerarRelatorio(JRBeanCollectionDataSource dataSource, Map<String, Object> parametros,
			String relatorioPrincipal) throws Exception {

		InputStream relatorioStream = getClass().getResourceAsStream(relatorioPrincipal);
		JasperReport relatorioJasper = JasperCompileManager.compileReport(relatorioStream);

		JasperPrint jasperPrint = JasperFillManager.fillReport(relatorioJasper, parametros, dataSource);

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

}
