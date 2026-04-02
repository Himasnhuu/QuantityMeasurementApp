package com.app.quantitymeasurement.entity;

import java.io.Serializable;

public class QuantityMeasurementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private double thisValue;
    private String thisUnit;
    private double thatValue;
    private String thatUnit;
    private String operation;
    private String result;
    private boolean isError;
    private String errorMessage;

    public QuantityMeasurementEntity() {
    }

    public QuantityMeasurementEntity(double thisValue, String thisUnit, double thatValue, 
                                     String thatUnit, String operation, String result, 
                                     boolean isError, String errorMessage) {
        this.thisValue = thisValue;
        this.thisUnit = thisUnit;
        this.thatValue = thatValue;
        this.thatUnit = thatUnit;
        this.operation = operation;
        this.result = result;
        this.isError = isError;
        this.errorMessage = errorMessage;
    }

    public double getThisValue() {
        return thisValue;
    }

    public void setThisValue(double thisValue) {
        this.thisValue = thisValue;
    }

    public String getThisUnit() {
        return thisUnit;
    }

    public void setThisUnit(String thisUnit) {
        this.thisUnit = thisUnit;
    }

    public double getThatValue() {
        return thatValue;
    }

    public void setThatValue(double thatValue) {
        this.thatValue = thatValue;
    }

    public String getThatUnit() {
        return thatUnit;
    }

    public void setThatUnit(String thatUnit) {
        this.thatUnit = thatUnit;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        if (isError) {
            return "QuantityMeasurementEntity{ERROR: " + errorMessage + "}";
        }
        return "QuantityMeasurementEntity{" +
                "thisValue=" + thisValue +
                ", thisUnit='" + thisUnit + '\'' +
                ", thatValue=" + thatValue +
                ", thatUnit='" + thatUnit + '\'' +
                ", operation='" + operation + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
