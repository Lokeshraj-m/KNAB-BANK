package com.bank.knab.exceptions;

import java.time.LocalDateTime;

public class ErrorResponse {
	private LocalDateTime time;
	private int statusCode;
    private String message;

 
    public ErrorResponse() {
		super();
	}
    
	public ErrorResponse(LocalDateTime time, int statusCode, String message) {
		super();
		this.time = time;
		this.statusCode = statusCode;
		this.message = message;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorResponse [time=" + time + ", statusCode=" + statusCode + ", message=" + message + "]";
	}
}
