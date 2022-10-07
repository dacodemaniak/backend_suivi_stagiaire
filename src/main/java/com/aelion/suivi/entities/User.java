package com.aelion.suivi.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String userName;
	
	@Column
	private String userPass;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	private Set<UserRole> userRoles = new HashSet<>();
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	
	public String getUserPass() {
		return this.userPass;
	}
	
	public void setUserRoles(Set<UserRole> roles) {
		this.userRoles = roles;
		
		for (UserRole r : roles) {
			r.setUser(this);
		}
	}
	
	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}
}
