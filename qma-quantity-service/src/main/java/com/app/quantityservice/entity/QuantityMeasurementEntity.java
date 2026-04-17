package com.app.quantityservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "quantity_measurement")
public class QuantityMeasurementEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String operation;
	private String operand1;
	private String operand2;
	private String result;
	private String error;

	// Default constructor
	public QuantityMeasurementEntity() {
	}

	// UC-18
	public QuantityMeasurementEntity(String operation, String operand1, String operand2, String result) {
		this.operation = operation;
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.result = result;
	}

	public QuantityMeasurementEntity(String operation, String operand1, String operand2, String result, String error) {
		this.operation = operation;
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.result = result;
		this.error = error;
	}

	public QuantityMeasurementEntity(String error) {
		this.error = error;
	}

	public Long getId() {
		return id;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getOperand1() {
		return operand1;
	}

	public void setOperand1(String operand1) {
		this.operand1 = operand1;
	}

	public String getOperand2() {
		return operand2;
	}

	public void setOperand2(String operand2) {
		this.operand2 = operand2;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}