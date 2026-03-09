package com;

public class Quantity<U extends IMeasurable> {

	private final double value;
	private final U unit;

	public double getValue() {
		return value;
	}

	public U getUnit() {
		return unit;
	}

	private static final double EPSILON = 0.0001;

	public Quantity(double value, U unit) {

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

	public Quantity<U> convertTo(U targetUnit) {

		if (targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null");
		}

		double baseValue = convertToBaseUnit();
		double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

		convertedValue = Math.round(convertedValue * 100.0) / 100.0;

		return new Quantity<>(convertedValue, targetUnit);
	}

	public Quantity<U> add(Quantity<U> other) {
		return add(other, this.unit);
	}

	public Quantity<U> add(Quantity<U> other, U targetUnit) {

		if (other == null) {
			throw new IllegalArgumentException("Other quantity cannot be null");
		}

		if (targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null");
		}

		double sumBase = this.convertToBaseUnit() + other.convertToBaseUnit();

		double resultValue = targetUnit.convertFromBaseUnit(sumBase);

		resultValue = Math.round(resultValue * 100.0) / 100.0;

		return new Quantity<>(resultValue, targetUnit);
	}

	public Quantity<U> subtract(Quantity<U> other) {
		return subtract(other, this.unit);
	}

	public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

		validateQuantity(other);
		validateTargetUnit(targetUnit);

		double base1 = unit.convertToBaseUnit(value);
		double base2 = other.unit.convertToBaseUnit(other.value);

		double resultBase = base1 - base2;

		double result = targetUnit.convertFromBaseUnit(resultBase);

		return new Quantity<>(Math.round(result), targetUnit);
	}

	public double divide(Quantity<U> other) {

		validateQuantity(other);

		double base1 = unit.convertToBaseUnit(value);
		double base2 = other.unit.convertToBaseUnit(other.value);

		if (Math.abs(base2) < EPSILON)
			throw new ArithmeticException("Division by zero");

		return base1 / base2;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null || getClass() != obj.getClass())
			return false;

		Quantity<?> other = (Quantity<?>) obj;

		if (this.unit.getClass() != other.unit.getClass())
			return false;

		double thisBase = this.convertToBaseUnit();
		double thatBase = other.unit.convertToBaseUnit(other.value);

		return Math.abs(thisBase - thatBase) < EPSILON;
	}

	private void validateQuantity(Quantity<U> other) {

		if (other == null)
			throw new IllegalArgumentException("Quantity cannot be null");

		if (!unit.getClass().equals(other.unit.getClass()))
			throw new IllegalArgumentException("Different measurement categories");
	}

	private void validateTargetUnit(U targetUnit) {

		if (targetUnit == null)
			throw new IllegalArgumentException("Target unit cannot be null");
	}

	@Override
	public int hashCode() {
		return Double.hashCode(convertToBaseUnit());
	}

	@Override
	public String toString() {
		return "Quantity(" + value + ", " + unit.getUnitName() + ")";
	}
}