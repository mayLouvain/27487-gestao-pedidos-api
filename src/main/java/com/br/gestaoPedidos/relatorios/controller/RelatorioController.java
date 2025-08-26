package com.br.gestaoPedidos.relatorios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.gestaoPedidos.relatorios.service.RelatorioService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/relatorios")
@Tag(name = "Relatórios", description = "Operações que geram os relatórios")
public class RelatorioController {

	@Autowired
	private RelatorioService service;

	@ApiResponse(
		    responseCode = "200",
		    description = "Relatório PDF",
		    content = @Content(
		        mediaType = "application/pdf",
		        schema = @Schema(type = "string", format = "binary")
		    )
		)
	@GetMapping("/relatorio/pedidos")
	public ResponseEntity<byte[]> gerarRelatorio() throws Exception {
		byte[] pdf = service.gerarRelatorioPedidos();

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio_produtos.pdf")
				.contentType(MediaType.APPLICATION_PDF).body(pdf);
	}

}
