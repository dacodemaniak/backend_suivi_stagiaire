package com.aelion.suivi.exceptions;

public class JwtTokenMalformedException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public JwtTokenMalformedException(String error) {
		super(error);
	}
}
