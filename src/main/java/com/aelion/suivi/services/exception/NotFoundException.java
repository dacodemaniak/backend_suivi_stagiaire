/**
 * 
 */
package com.aelion.suivi.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Aelion
 *
 */
public class NotFoundException extends Exception implements HttpErrorInterface {
	public NotFoundException() {
		super();
	}
	
	public NotFoundException(String message) {
		super(message);
	}
	
	public ResponseEntity<String> send() {
		return new ResponseEntity<String>(this.getMessage(), HttpStatus.NOT_FOUND);
	}
}
