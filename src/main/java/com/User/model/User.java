package com.User.model;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
	@Id
	private String id;
	@NotBlank
	@Size(max = 20)
	private String username;
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	@NotBlank
	@Size(max = 120)
	private String password;
	
	private Set<String> updateroles;

	private Set<String> fullname;
	private Set<String> district;
	private Set<String> phonenumber;
	private Set<String> city;
	private Set<String> address;
	
	
	@DBRef
	private Set<Role> roles=new HashSet<>();
	
	public User() {
		
	}
	
	public User(String username, String email, String password, String fullname, String district, String phonenumber, String city, String address) {
		this.setUsername(username);
		this.setEmail(email);
		this.setPassword(password);
		
	}
	
	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getUpdateroles() {
		return updateroles;
	}

	public void setUpdateroles(Set<String> updateroles) {
		this.updateroles = updateroles;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
