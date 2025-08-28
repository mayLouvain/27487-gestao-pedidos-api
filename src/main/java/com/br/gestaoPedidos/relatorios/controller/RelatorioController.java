package com.br.gestaoPedidos.relatorios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.gestaoPedidos.relatorios.dto.RelatorioDTO;
import com.br.gestaoPedidos.relatorios.service.RelatorioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/api/relatorios")
@Tag(name = "Relatórios", description = "Operações que geram os relatórios")
public class RelatorioController {

	private final static String NOME_ARQUIVO_PEDIDOS = "relatorio_pedidos.pdf";
	private final static String NOME_ARQUIVO_PRODUTOS = "relatorio_pedidos_agrupado_produtos.pdf";

	@Autowired
	private RelatorioService service;

//	@Operation(summary = "Gera relatório de pedidos em PDF", responses = {
//			@ApiResponse(responseCode = "200", description = "PDF gerado", content = @Content(mediaType = "application/pdf")) })
	@PostMapping(value = "/pedidos")
	public ResponseEntity<?> gerarRelatorio(@RequestBody RelatorioDTO filtro) throws JRException {
		byte[] pdf = service.gerarRelatorioPedidos(filtro);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment",
				filtro.agrupaProduto() ? NOME_ARQUIVO_PRODUTOS : NOME_ARQUIVO_PEDIDOS);
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentLength(pdf.length);

		return new ResponseEntity<>(pdf, headers, HttpStatus.OK);

	}

}
