package com.core;

import com.unit.TemperatureUnit;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    private static final double EPSILON = 1e-5;

    public Quantity(double value, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    public double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    // Arithmetic Operation Enum
    private enum ArithmeticOperation {

        ADD {
            @Override
            double compute(double a, double b) {
                return a + b;
            }
        },
        SUBTRACT {
            @Override
            double compute(double a, double b) {
                return a - b;
            }
        },
        DIVIDE {
            @Override
            double compute(double a, double b) {
                if (Math.abs(b) < EPSILON) {
                    throw new ArithmeticException("Division by zero");
                }
                return a / b;
            }
        };

        abstract double compute(double a, double b);
    }

    private void validateArithmeticOperands(Quantity<U> other,
            U targetUnit,
            boolean targetRequired) {

        if (other == null) {
            throw new IllegalArgumentException("Other quantity cannot be null");
        }

        if (targetRequired && targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        if (!this.unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Cross-category operation not allowed");
        }

        if (!Double.isFinite(this.value) || !Double.isFinite(other.value)) {
            throw new IllegalArgumentException("Values must be finite");
        }
    }

    // Core Base Arithmetic
    private double performBaseArithmetic(Quantity<U> other,
            ArithmeticOperation operation) {

        // Validate arithmetic support
        this.unit.validateOperationSupport(operation.name());
        other.unit.validateOperationSupport(operation.name());

        double base1 = this.toBaseUnit();
        double base2 = other.toBaseUnit();

        return operation.compute(base1, base2);
    }

    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        if (unit instanceof TemperatureUnit) {

            TemperatureUnit tempUnit = (TemperatureUnit) unit;
            TemperatureUnit target = (TemperatureUnit) targetUnit;

            double converted
                    = tempUnit.convertTo(value, target);

            return new Quantity<>(converted, targetUnit);
        }

        double base = toBaseUnit();
        double converted = targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(converted, targetUnit);
    }

    // Add
    public Quantity<U> add(Quantity<U> other) {

        validateArithmeticOperands(other, null, false);

        double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double converted = unit.convertFromBaseUnit(baseResult);

        return new Quantity<>(round(converted), unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double converted = targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(round(converted), targetUnit);
    }

    // Subtract
    public Quantity<U> subtract(Quantity<U> other) {

        validateArithmeticOperands(other, null, false);

        double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double converted = unit.convertFromBaseUnit(baseResult);

        return new Quantity<>(round(converted), unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double converted = targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(round(converted), targetUnit);
    }

    // Divide
    public double divide(Quantity<U> other) {

        validateArithmeticOperands(other, null, false);

        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    // Customs equality and hashcode   
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Quantity)) {
            return false;
        }

        Quantity<?> other = (Quantity<?>) obj;

        if (!unit.getClass().equals(other.unit.getClass())) {
            return false;
        }

        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) <= EPSILON;
    }

    @Override
    public int hashCode() {
        long rounded = Math.round(toBaseUnit() / EPSILON);
        return Long.hashCode(rounded);
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }

}
