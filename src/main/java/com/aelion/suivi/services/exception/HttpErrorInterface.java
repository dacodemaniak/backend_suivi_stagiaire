package com.aelion.suivi.services.exception;

import org.springframework.http.ResponseEntity;

public interface HttpErrorInterface {
	public ResponseEntity<String> send();
}
