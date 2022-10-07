package com.aelion.suivi.controllers;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aelion.suivi.components.JwtUtil;
import com.aelion.suivi.dto.SignupMessage;
import com.aelion.suivi.dto.UserRequest;
import com.aelion.suivi.dto.UserResponse;
import com.aelion.suivi.exceptions.DisabledUserException;
import com.aelion.suivi.exceptions.InvalidUserCredentialsException;
import com.aelion.suivi.services.UserAuthService;
import com.aelion.suivi.services.UserService;

@RequestMapping("/user")
@RestController
@CrossOrigin(value="http://localhost:4200")
public class UserController {
	@Autowired
	private UserAuthService service;
	
	@Autowired
	private UserAuthService userAuthService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@PostMapping("/signup")
	public ResponseEntity<SignupMessage> signup(@RequestBody UserRequest request) {
		this.service.saveUser(request);
		
		SignupMessage message = new SignupMessage("User was created!");
		
		return new ResponseEntity<SignupMessage>(message, HttpStatus.CREATED);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<UserResponse> generateJwtToken(@RequestBody UserRequest request) {
		Authentication authentication = null;
		
		try {
			authentication = this.authenticationManager
				.authenticate(
					new UsernamePasswordAuthenticationToken(
						request.getUserName(),
						request.getUserPass()
					)
			);
			
		} catch (DisabledException e) {
			throw new DisabledUserException("User was disabled");
		} catch (BadCredentialsException e) {
			throw new InvalidUserCredentialsException("Invalid credentials");
		}
		
		// Got a Spring Security User
		User user = (User) authentication.getPrincipal();
		
		Set<String> roles = user
				.getAuthorities()
				.stream().map(r -> r.getAuthority())
				.collect(Collectors.toSet());
		
		// Make a token from "authentication" object
		String token = jwtUtil.generateToken(authentication);
		
		// Create a Response DTO to send to client
		UserResponse response = new UserResponse();
		response.setToken(token);
		response.setRoles(roles.stream().collect(Collectors.toList()));
		
		return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
	}
}
	

