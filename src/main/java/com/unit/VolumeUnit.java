package com.unit;

import com.core.IMeasurable;

public enum VolumeUnit implements IMeasurable {
    LITRE(1.0), //base unit 
    MILLILITRE(0.001),
    GALLON(3.78541);

    private final double conversionFactor;

    VolumeUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
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

    @Override
    public String getUnitName() {
        return this.toString();
    }

    @Override
    public String getMeasurementType() {
        return this.getClass().getSimpleName();
    }

    @Override
    public IMeasurable getUnitInstance(String unitName) {
        for (VolumeUnit unit : VolumeUnit.values()) {
            if (unit.getUnitName().equalsIgnoreCase(unitName)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Invalid volume unit: " + unitName);
    }
}
