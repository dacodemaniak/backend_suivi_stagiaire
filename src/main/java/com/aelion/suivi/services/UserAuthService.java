package com.aelion.suivi.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aelion.suivi.dto.UserRequest;
import com.aelion.suivi.entities.User;
import com.aelion.suivi.entities.UserRole;
import com.aelion.suivi.repositories.UserRepository;

@Service
public class UserAuthService implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUserName(username).get();
		
		// Way to convert Set to List
		List<UserRole> roles = user.getUserRoles()
				.stream()
				.collect(Collectors.toList());
		
		
		// @todo Check for Eclipse compliance with map function
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for (UserRole role : roles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
		}
		
		return new org.springframework.security.core.userdetails.User(username, user.getUserPass(), grantedAuthorities);

	}
	
	public void saveUser(UserRequest request) {
		Optional<User> oUser = this.userRepository.findByUserName(request.getUserName());
		
		if (oUser.isPresent()) {
			throw new RuntimeException("User already exists!");
		}
		
		User user = new User();
		user.setUserName(request.getUserName());
		user.setUserPass(this.passwordEncoder.encode(request.getUserPass()));
		
		
		// Sets roles
		user.setUserRoles(
				request.getRoles().stream().map(r -> {
					UserRole userRole = new UserRole();
					userRole.setRole(r);
					return userRole;
				}).collect(Collectors.toSet())
		);
		
		// Persist
		this.userRepository.save(user);
	}

}
