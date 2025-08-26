package com.br.gestaoPedidos.core.exceptions;

import java.util.List;

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

		String mensagemArquivo;
		try {
			mensagemArquivo = mensagem.getMessage(chaveMensagem, null, LocaleContextHolder.getLocale());
		} catch (NoSuchMessageException e) {
			mensagemArquivo = chaveMensagem != null ? chaveMensagem : "Ocorreu um erro no processamento.";
		}

		HttpStatus status = (mensagemArquivo.equals(chaveMensagem) ? HttpStatus.INTERNAL_SERVER_ERROR
				: HttpStatus.BAD_REQUEST);

		ErroResposta erro = new ErroResposta(status.name(), mensagemArquivo);
		return ResponseEntity.status(status).body(erro);
	}
}
