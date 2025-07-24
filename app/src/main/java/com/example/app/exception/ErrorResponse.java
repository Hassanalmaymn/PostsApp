package com.example.app.exception;

public class ErrorResponse {
	private int status;
	private String message;
	private String timestamp;
	private String path;

	public ErrorResponse(String message, int status) {
		this.status = status;
		this.message = message;
		this.timestamp = java.time.LocalDateTime.now().toString();

	}

	// Getters and setters
}
