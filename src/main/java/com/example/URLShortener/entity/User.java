package com.example.URLShortener.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "userDetails")
public class User 
{
	@Id
	@Column(length = 255)
	private String email;
	@Column(nullable = false, unique = false, length = 255)
	private String name;
	@Column(nullable = false, unique = false, length = 255)
	private String password;
	@Column(nullable = false, unique = false, length = 255)
	private String role;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
