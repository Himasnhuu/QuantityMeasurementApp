package com.app.quantitymeasurement.entity;

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

    public QuantityMeasurementEntity() {
    }

    public QuantityMeasurementEntity(String operation, String op1, String op2, String result) {
        this.operation = operation;
        this.operand1 = op1;
        this.operand2 = op2;
        this.result = result;
    }

    public QuantityMeasurementEntity(String error) {
        this.error = error;
    }

    public boolean hasError() {
        return error != null;
    }

    public Long getId() {
        return id;
    }

    public String getOperation() {
        return operation;
    }

    public String getOperand1() {
        return operand1;
    }

    public String getOperand2() {
        return operand2;
    }

    public String getResult() {
        return result;
    }

    public String getError() {
        return error;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setOperand1(String operand1) {
        this.operand1 = operand1;
    }

    public void setOperand2(String operand2) {
        this.operand2 = operand2;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setError(String error) {
        this.error = error;
    }
}