package com.aelion.suivi.exceptions;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenMissingException extends AuthenticationException {
	private static final long serialVersionUID = 1L;
	
	public JwtTokenMissingException(String error) {
		super(error);
	}
}
