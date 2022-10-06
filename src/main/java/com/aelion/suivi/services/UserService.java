package com.aelion.suivi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aelion.suivi.entities.User;

@Service
public class UserService {

	
	public ResponseEntity checkEmail(String email) {
		System.out.println("Service got " + email);
		
		
		return ResponseEntity.notFound().build();
	}
}
