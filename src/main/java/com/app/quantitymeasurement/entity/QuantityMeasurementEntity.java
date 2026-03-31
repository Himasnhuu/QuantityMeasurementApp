package com.app.quantitymeasurement.entity;

import com.app.quantitymeasurement.user.User;

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

    // UC-18 User mapping
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Default constructor
    public QuantityMeasurementEntity() {}

    //UC-18
    public QuantityMeasurementEntity(String operation,
                                     String operand1,
                                     String operand2,
                                     String result,
                                     User user) {
        this.operation = operation;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.result = result;
        this.user = user;
    }

    public QuantityMeasurementEntity(String operation,
                                     String operand1,
                                     String operand2,
                                     String result,
                                     String error,
                                     User user) {
        this.operation = operation;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.result = result;
        this.error = error;
        this.user = user;
    }

    public QuantityMeasurementEntity(String operation,
                                     String operand1,
                                     String operand2,
                                     String result) {
        this.operation = operation;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.result = result;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}