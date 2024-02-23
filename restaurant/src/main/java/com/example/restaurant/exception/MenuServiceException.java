package com.example.restaurant.exception;

public class MenuServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MenuServiceException(String message) {
		super(message);
	}

	public MenuServiceException(String string, Exception e) {
		super(string, e);
	}

}
