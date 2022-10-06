package com.aelion.suivi.exceptions;

public class JwtTokenMissingException extends Exception {
	private static final long serialVersionUID = 2L;
	
	public JwtTokenMissingException(String error) {
		super(error);
	}
}
