package com.core;

import java.util.Objects;

import com.unit.LengthUnit;

public class Length implements IMeasurable {

    private final double value;
    private final LengthUnit unit;

    public Length(double value, LengthUnit unit) {
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

    public LengthUnit getUnit() {
        return unit;
    }

    private double toBaseUnit() {
        return unit.getConversionFactor() * value;
    }

    public Boolean compare(Length that) {
        return Double.compare(this.toBaseUnit(), that.toBaseUnit()) == 0;
    }

    public Length convertTo(LengthUnit targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null.");
        }

        double baseValue = toBaseUnit();

        double convertedValue = baseValue / targetUnit.getConversionFactor();

        return new Length(round(convertedValue), targetUnit);
    }

    private double round(double value) {
        return Math.round(value * 1000.0) / 1000.0; // now for three decimal places for uc 7
    }

    @Override
    public boolean equals(Object obj) {

        // reflexive
        if (this == obj) {
            return true;
        }

        // null + Type check
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Length other = (Length) obj;

        // convert both to base unit before comparing
        return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
    }

    //UC6
    public Length add(Length other) {
        if (other == null) {
            throw new IllegalArgumentException("other cant be null");
        }

        double baseSum = this.toBaseUnit() + other.toBaseUnit();
        double thisSum = baseSum / this.unit.getConversionFactor();
        return new Length(round(thisSum), this.unit);
    }

    //uc6 static overload method
    public Length add(Length l1, Length l2) {
        return l1.add(l2);
    }

    //UC7 target Unit addition
    public Length add(Length other, LengthUnit targetUnit) {
        if (other == null) {
            throw new IllegalArgumentException("other cant be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("targetUnit cant be null");
        }
        return addAndConvert(other, targetUnit);
    }

    private Length addAndConvert(Length other, LengthUnit targetUnit) {

        double add = this.toBaseUnit() + other.toBaseUnit();
        double sum = convertFromBaseToTargetUnit(add, targetUnit);
        return new Length(round(sum), targetUnit);
    }

    private double convertFromBaseToTargetUnit(double lengthInches, LengthUnit targetUnit) {
        return lengthInches / targetUnit.getConversionFactor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(toBaseUnit());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
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
