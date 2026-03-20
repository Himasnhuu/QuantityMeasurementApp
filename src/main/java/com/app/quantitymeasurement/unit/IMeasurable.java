package com.app.quantitymeasurement.unit;

public interface IMeasurable {

    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    String getUnitName();
    
    // UC14
    default boolean supportsArithmetic() {
        return true;
    }

    default void validateOperationSupport(String operation) {
    }
}