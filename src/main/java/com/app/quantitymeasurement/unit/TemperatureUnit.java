package com.app.quantitymeasurement.unit;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS,
    FAHRENHEIT,
    KELVIN;

    @Override
    public double getConversionFactor() {
        return 1.0;
    }

    @Override
    public double convertToBaseUnit(double value) {

        switch (this) {
            case CELSIUS:
                return value;

            case FAHRENHEIT:
                return (value - 32) * 5 / 9;

            case KELVIN:
                return value - 273.15;

            default:
                throw new IllegalStateException();
        }
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {

        switch (this) {
            case CELSIUS:
                return baseValue;

            case FAHRENHEIT:
                return baseValue * 9 / 5 + 32;

            case KELVIN:
                return baseValue + 273.15;

            default:
                throw new IllegalStateException();
        }
    }

    @Override
    public String getUnitName() {
        return name();
    }

    @Override
    public boolean supportsArithmetic() {
        return false;
    }

    @Override
    public void validateOperationSupport(String operation) {
        throw new UnsupportedOperationException(
                "Temperature does not support " + operation + " operation"
        );
    }
}