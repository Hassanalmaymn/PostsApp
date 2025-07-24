package com.example.app.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	private MessageSource messageSource;

	public GlobalExceptionHandler(MessageSource messageSource) {
		super();
		this.messageSource = messageSource;
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request, Locale locale) {
		String translatedMessage = messageSource.getMessage(ex.getMessage(), null, ex.getMessage(), locale);

		return buildErrorResponse(translatedMessage, HttpStatus.NOT_FOUND, request.getDescription(false));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
		return buildErrorResponse("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR,
				request.getDescription(false));
	}

	private ResponseEntity<Object> buildErrorResponse(String message, HttpStatus status, String path) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("timestamp", LocalDateTime.now());
		errorDetails.put("status", status.value());
		errorDetails.put("error", status.getReasonPhrase());
		errorDetails.put("message", message);
		errorDetails.put("path", path);
		return new ResponseEntity<>(errorDetails, status);
	}

}
