package com.core;

import com.unit.WeightUnit;

public class Weight implements IMeasurable {

    private final double value;
    private final WeightUnit unit;

    private static final double EPSILON = 1e-5;//Rounding of the values  	

    public Weight(double value, WeightUnit unit) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite.");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    public Weight convertTo(WeightUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target  cannot be null");
        }

        double base = toBaseUnit();
        double converted = targetUnit.convertFromBaseUnit(base);

        return new Weight(converted, targetUnit);
    }

    public Weight add(Weight other) {
        if (other == null) {
            throw new IllegalArgumentException("Other weight can't be null");
        }

        double sumBase = this.toBaseUnit() + other.toBaseUnit();
        double result = unit.convertFromBaseUnit(sumBase);

        return new Weight(result, unit);
    }

    public Weight add(Weight other, WeightUnit targetUnit) {
        if (other == null) {
            throw new IllegalArgumentException("Other weight cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double sumBase = this.toBaseUnit() + other.toBaseUnit();
        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new Weight(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Weight other = (Weight) obj;

        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

    @Override
    public int hashCode() {
        long rounded = Math.round(toBaseUnit() / EPSILON);
        return Long.hashCode(rounded);
    }

    @Override
    public String toString() {
        return "Weight(" + value + ", " + unit + ")";
    }

    @Override
    public double getConversionFactor() {
        return unit.getConversionFactor();
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * unit.getConversionFactor();
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / unit.getConversionFactor();
    }

    @Override
    public String getUnitName() {
        return unit.getUnitName();
    }

    @Override
    public String getMeasurementType() {
        return unit.getMeasurementType();
    }

    @Override
    public IMeasurable getUnitInstance(String unitName) {
        return unit.getUnitInstance(unitName);
    }

}
