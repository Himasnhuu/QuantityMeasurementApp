package com.app.historyservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "operation_history")
public class OperationHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String userEmail;
	private String operationType;

	@Column(columnDefinition = "TEXT")
	private String inputData; // JSON string

	private String result;

	private LocalDateTime createdAt;

	public Long getId() {
		return id;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public String getOperationType() {
		return operationType;
	}

	public String getInputData() {
		return inputData;
	}

	public String getResult() {
		return result;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public void setInputData(String inputData) {
		this.inputData = inputData;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}