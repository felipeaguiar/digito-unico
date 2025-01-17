package br.com.bancointer.desafio.interfaces.handler;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.bancointer.desafio.application.errors.UsuarioDuplicadoException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class AppExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(HttpMessageConversionException.class)
	public ResponseEntity<ApiResponse> handleHttpMessageConversionException(HttpMessageConversionException e,
			Locale locale) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = messageSource.getMessage("mensagem.invalida", null, locale);

		ApiResponse error = ApiResponse.of(status.value(), new ApiMessage("json-parse", message));
		return ResponseEntity.badRequest().body(error);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<ApiResponse> handlerEmptyResultDataAccessException(EmptyResultDataAccessException e, Locale locale) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		String message = messageSource.getMessage("recurso.nao-encontrado", null, locale);

		ApiResponse error = ApiResponse.of(status.value(), new ApiMessage("not-found", message));
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiResponse> handlerDataIntegrityViolationException(DataIntegrityViolationException e, Locale locale) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = messageSource.getMessage("integridade", null, locale);

		ApiResponse error = ApiResponse.of(status.value(), new ApiMessage("referential-integrity", message));
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
			Locale locale) {

		HttpStatus status = HttpStatus.BAD_REQUEST;

		List<ApiMessage> messages = e.getBindingResult().getFieldErrors().stream()
			.map(f -> new ApiMessage("validation", messageSource.getMessage(f, locale)))
			.collect(Collectors.toList());

		ApiResponse error = ApiResponse.of(status.value(), messages);
		return ResponseEntity.badRequest().body(error);
	}
	
	@ExceptionHandler(UsuarioDuplicadoException.class)
	public ResponseEntity<ApiResponse> handlerUsuarioDuplicadoError(UsuarioDuplicadoException e, Locale locale) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = messageSource.getMessage("usuario.duplicado", null, locale);

		ApiResponse error = ApiResponse.of(status.value(), new ApiMessage("error", message));
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse> handlerInternalServerError(Exception e, Locale locale) {
		log.error("Error not expected", e);

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String message = messageSource.getMessage("erro.desconhecido", null, locale);

		ApiResponse error = ApiResponse.of(status.value(), new ApiMessage("error", message));
		return ResponseEntity.status(status).body(error);
	}

}
