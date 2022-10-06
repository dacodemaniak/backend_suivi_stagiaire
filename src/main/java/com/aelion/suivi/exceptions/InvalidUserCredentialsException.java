package com.aelion.suivi.exceptions;

public class InvalidUserCredentialsException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public InvalidUserCredentialsException(String error) {
		super(error);
	}
}
