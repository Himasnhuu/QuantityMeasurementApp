package com.app.quantitymeasurement;

import com.app.quantitymeasurement.unit.IMeasurable;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    private double convertToBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    //Equality

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (!this.unit.getClass().equals(other.unit.getClass()))
            return false;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < 1e-3;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(convertToBaseUnit());
    }

    //Conversion

    public Quantity<U> convertTo(U targetUnit) {

        double base = unit.convertToBaseUnit(value);

        double result = targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(result, targetUnit);
    }

    //Addition

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
    	
    	unit.validateOperationSupport("addition");
    	
    	validateArithmeticOperands(other, targetUnit, true);

        double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);

        double result = targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(result, targetUnit);
    }
    
    //UC 12
    //Subtract
    public Quantity<U> subtract(Quantity<U> other){
    	return subtract(other, this.unit);
    }
    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
    	
    	unit.validateOperationSupport("subtraction");

    	validateArithmeticOperands(other, targetUnit, true);

    	double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

    	double result = targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(result, targetUnit);
    }
    
    //Division
    public double divide(Quantity<U> other) {
    	
    	unit.validateOperationSupport("division");

    	validateArithmeticOperands(other, null, false);

        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }
    
    @SuppressWarnings("unused")
	private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
    
    //UC 13
    private enum ArithmeticOperation {

        ADD {
            double compute(double a, double b) {
                return a + b;
            }
        },

        SUBTRACT {
            double compute(double a, double b) {
                return a - b;
            }
        },

        DIVIDE {
            double compute(double a, double b) {
                if (b == 0)
                    throw new ArithmeticException("Division by zero");
                return a / b;
            }
        };

        abstract double compute(double a, double b);
    }
    
    private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetRequired) {

        if (other == null)
            throw new IllegalArgumentException("Other quantity cannot be null");

        if (!this.unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Different measurement categories");

        if (!Double.isFinite(this.value) || !Double.isFinite(other.value))
            throw new IllegalArgumentException("Values must be finite");

        if (targetRequired && targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");
    }
    
    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation operation) {

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return operation.compute(base1, base2);
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}