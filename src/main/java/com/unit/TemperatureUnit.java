package com.unit;

import java.util.function.Function;

import com.core.IMeasurable;
import com.core.SupportsArithmetic;

public enum TemperatureUnit implements IMeasurable {
    CELSIUS(false),
    FAHRENHEIT(true);

    private final Function<Double, Double> conversionValue;

    private final SupportsArithmetic supportsArithmetic;

    TemperatureUnit(boolean isFahrenheit) {
        if (isFahrenheit) {
            conversionValue = (f) -> (f - 32) * 5 / 9;
        } else {
            conversionValue = (c) -> c;
        }
        this.supportsArithmetic = () -> false;  // Temperature does not support arithmetic operations
    }

    @Override
    public double getConversionFactor() {
        return 1.0;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return conversionValue.apply(value);
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        if (this == FAHRENHEIT) {
            return (baseValue * 9 / 5) + 32;
        }

        return baseValue;
    }

    @Override
    public boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    public double convertTo(double value, TemperatureUnit targetUnit) {

        double base = convertToBaseUnit(value);

        return targetUnit.convertFromBaseUnit(base);
    }

    @Override
    public String getUnitName() {
        return name();
    }

    @Override
    public void validateOperationSupport(String operation) {

        if (!supportsArithmetic()) {

            throw new UnsupportedOperationException(
                    this.name() + " does not support " + operation + " operations."
            );
        }
    }

    @Override
    public String getMeasurementType() {
        return this.getClass().getSimpleName();
    }

    @Override
    public IMeasurable getUnitInstance(String unitName) {
        for (TemperatureUnit unit : TemperatureUnit.values()) {
            if (unit.getUnitName().equalsIgnoreCase(unitName)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Invalid temperature unit: " + unitName);
    }

}
