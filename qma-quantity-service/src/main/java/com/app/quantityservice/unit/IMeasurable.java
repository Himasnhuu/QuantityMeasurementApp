package com.app.quantityservice.unit;

public interface IMeasurable {

    // Basic conversion logic
    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    // Unit name (for display / mapping)
    String getUnitName();

    // Optional: arithmetic support (default = true)
    default boolean supportsArithmetic() {
        return true;
    }

    // Optional validation hook
    default void validateOperationSupport(String operation) {
        // By default, do nothing (allowed)
    }
}