package com.app.quantitymeasurement.model;

import com.app.quantitymeasurement.units.IMeasurable;

public class QuantityModel {

    private final double value;
    private final IMeasurable unit;

    private static final double EPSILON = 0.0001;

    public QuantityModel(double value, IMeasurable unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public IMeasurable getUnit() {
        return unit;
    }

    private double convertToBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    // ---------------- CONVERSION ----------------

    public QuantityModel convertTo(IMeasurable targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        if (!unit.getClass().equals(targetUnit.getClass())) {
            throw new IllegalArgumentException("Cannot convert between different measurement types");
        }

        double baseValue = convertToBaseUnit();
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

        return new QuantityModel(roundToTwoDecimals(convertedValue), targetUnit);
    }

    // ---------------- ADDITION ----------------

    public QuantityModel add(QuantityModel other) {
        return add(other, this.unit);
    }

    public QuantityModel add(QuantityModel other, IMeasurable targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);

        double resultBase = performBaseArithmetic(other, ArithmeticOperation.ADD);

        double result = targetUnit.convertFromBaseUnit(resultBase);

        return new QuantityModel(roundToTwoDecimals(result), targetUnit);
    }

    // ---------------- SUBTRACTION ----------------

    public QuantityModel subtract(QuantityModel other) {
        return subtract(other, this.unit);
    }

    public QuantityModel subtract(QuantityModel other, IMeasurable targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);

        double resultBase = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double result = targetUnit.convertFromBaseUnit(resultBase);

        return new QuantityModel(roundToTwoDecimals(result), targetUnit);
    }

    // ---------------- DIVISION ----------------

    public double divide(QuantityModel other) {
        validateArithmeticOperands(other, null, false);

        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    // ---------------- CENTRALIZED VALIDATION ----------------

    private void validateArithmeticOperands(
            QuantityModel other,
            IMeasurable targetUnit,
            boolean targetUnitRequired) {

        if (other == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }

        if (!unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Different measurement categories");
        }

        if (!Double.isFinite(this.value) || !Double.isFinite(other.value)) {
            throw new IllegalArgumentException("Values must be finite");
        }

        if (targetUnitRequired && targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
    }

    // ---------------- CORE ARITHMETIC ENGINE ----------------

    private double performBaseArithmetic(
            QuantityModel other,
            ArithmeticOperation operation) {
        
        unit.validateOperationSupport(operation.name());

        double base1 = this.convertToBaseUnit();
        double base2 = other.convertToBaseUnit();

        return operation.compute(base1, base2);
    }

    // ---------------- ARITHMETIC OPERATION ENUM ----------------

    private enum ArithmeticOperation {

        ADD(new DoubleBinaryOperator() {
            @Override
            public double applyAsDouble(double a, double b) {
                return a + b;
            }
        }),

        SUBTRACT(new DoubleBinaryOperator() {
            @Override
            public double applyAsDouble(double a, double b) {
                return a - b;
            }
        }),

        DIVIDE(new DoubleBinaryOperator() {
            @Override
            public double applyAsDouble(double a, double b) {
                if (Math.abs(b) < EPSILON) {
                    throw new ArithmeticException("Division by zero");
                }
                return a / b;
            }
        });

        private final DoubleBinaryOperator operator;

        ArithmeticOperation(DoubleBinaryOperator operator) {
            this.operator = operator;
        }

        public double compute(double a, double b) {
            return operator.applyAsDouble(a, b);
        }
    }

    // ---------------- ROUNDING ----------------

    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    // ---------------- EQUALITY ----------------

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        QuantityModel other = (QuantityModel) obj;

        if (this.unit.getClass() != other.unit.getClass()) {
            return false;
        }

        double thisBase = this.convertToBaseUnit();
        double thatBase = other.unit.convertToBaseUnit(other.value);

        return Math.abs(thisBase - thatBase) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Double.valueOf(convertToBaseUnit()).hashCode();
    }

    @Override
    public String toString() {
        return "QuantityModel(" + value + ", " + unit.getUnitName() + ")";
    }
}

interface DoubleBinaryOperator {
    double applyAsDouble(double a, double b);
}
