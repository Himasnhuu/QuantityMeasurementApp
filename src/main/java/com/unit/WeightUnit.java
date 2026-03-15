package com.unit;

import com.core.IMeasurable;

public enum WeightUnit implements IMeasurable {
    KILOGRAMS(1.0),
    GRAMS(0.001),
    POUNDS(0.453592);

    private final double conversionFactor;

    WeightUnit(double value) {
        this.conversionFactor = value;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }

    ;
	@Override
    public String getUnitName() {
        return this.toString();
    }

    // UC15
    @Override
    public String getMeasurementType() {
        return this.getClass().getSimpleName();
    }

    // UC15
    @Override
    public IMeasurable getUnitInstance(String unitName) {
        for (WeightUnit unit : WeightUnit.values()) {
            if (unit.getUnitName().equalsIgnoreCase(unitName)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Invalid weight unit: " + unitName);
    }
}
