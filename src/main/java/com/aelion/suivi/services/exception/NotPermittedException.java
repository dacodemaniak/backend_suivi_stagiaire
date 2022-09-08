package com.aelion.suivi.services.exception;

import org.springframework.http.ResponseEntity;

public class NotPermittedException extends Exception implements HttpErrorInterface {
	public NotPermittedException(String message) {
		super(message); // Always invoke parent class constructor
	}
	
	@Override
	public ResponseEntity<String> send() {
		return ResponseEntity.unprocessableEntity().build();
	}

}
