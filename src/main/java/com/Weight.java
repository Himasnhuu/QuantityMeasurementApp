package com;

public class Weight {

    private final double value;
    private final WeightUnit unit;

    private static final double EPSILON = 0.0001;

    public Weight(double value, WeightUnit unit) {

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }

        this.value = value;
        this.unit = unit;
    }

    private double convertToBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    public Weight convertTo(WeightUnit targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = convertToBaseUnit();
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        converted = Math.round(converted * 100000.0) / 100000.0;

        return new Weight(converted, targetUnit);
    }

    public Weight add(Weight other) {
        return add(other, this.unit);
    }

    public Weight add(Weight other, WeightUnit targetUnit) {

        if (other == null) {
            throw new IllegalArgumentException("Weight cannot be null");
        }

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseSum =
                this.convertToBaseUnit() +
                other.convertToBaseUnit();

        double resultValue =
                targetUnit.convertFromBaseUnit(baseSum);

        resultValue = Math.round(resultValue * 100000.0) / 100000.0;

        return new Weight(resultValue, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Weight other = (Weight) obj;

        double thisBase = convertToBaseUnit();
        double thatBase = other.convertToBaseUnit();

        return Math.abs(thisBase - thatBase) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(convertToBaseUnit());
    }

    @Override
    public String toString() {
        return String.format("%.5f %s", value, unit);
    }
}