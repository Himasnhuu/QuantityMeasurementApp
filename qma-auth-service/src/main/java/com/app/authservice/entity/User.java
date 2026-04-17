package com.app.authservice.entity;

import com.app.authservice.dto.AuthProvider;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	@Column(unique = true, nullable = false)
	private String email;
	private String password;
	
	@Enumerated(EnumType.STRING)
	private AuthProvider provider;

	public User() {
	}

	public User(String name, String email, String password, AuthProvider provider) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.provider = provider;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public AuthProvider getProvider() {
		return provider;
	}

	public void setProvider(AuthProvider provider) {
		this.provider = provider;
	}
}