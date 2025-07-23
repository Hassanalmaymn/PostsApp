package com.example.app.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Object> handleBadRequestException(BadRequestException ex, WebRequest request) {
		return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, request.getDescription(false));
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
		return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request.getDescription(false));
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
