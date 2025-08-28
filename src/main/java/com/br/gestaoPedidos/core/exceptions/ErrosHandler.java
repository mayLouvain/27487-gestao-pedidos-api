package com.br.gestaoPedidos.core.exceptions;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.br.gestaoPedidos.core.exceptions.dto.ErroResposta;
import com.br.gestaoPedidos.core.exceptions.dto.ErroValidacaoResposta;
import com.br.gestaoPedidos.relatorios.exception.RelatorioSemDadosException;

@RestControllerAdvice
public class ErrosHandler {
	@Autowired
	private MessageSource mensagem;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<ErroValidacaoResposta>> erroValidacao(MethodArgumentNotValidException ex) {
		var erros = ex.getFieldErrors();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(erros.stream().map(ErroValidacaoResposta::new).toList());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErroResposta> handleAllExceptions(Exception ex) {
		String chaveMensagem = ex.getMessage();

		String mensagemArquivo = getMensagemArquivo(chaveMensagem);

		HttpStatus status = (mensagemArquivo.equals(chaveMensagem) ? HttpStatus.INTERNAL_SERVER_ERROR
				: HttpStatus.BAD_REQUEST);

		ErroResposta erro = new ErroResposta(status.name(), mensagemArquivo);
		return ResponseEntity.status(status).body(erro);
	}

	@ExceptionHandler(RelatorioSemDadosException.class)
	public ResponseEntity<Map<String, String>> handleRelatorioSemDados(RelatorioSemDadosException ex) {

		String mensagemArquivo = getMensagemArquivo(ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensagem", mensagemArquivo));
	}

	private String getMensagemArquivo(String mensagemExcecao) {

		String mensagemArquivo;
		try {
			mensagemArquivo = mensagem.getMessage(mensagemExcecao, null, LocaleContextHolder.getLocale());
		} catch (NoSuchMessageException e) {
			mensagemArquivo = mensagemExcecao != null ? mensagemExcecao : "Ocorreu um erro no processamento.";
		}

		return mensagemArquivo;

	}
}
