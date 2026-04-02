package com;

public class Length {

	private final double value;
	private final LengthUnit unit;

	public static void main(String[] args) {
		Length length1 = new Length(1.0, LengthUnit.FEET);
		Length length2 = new Length(12.0, LengthUnit.INCHES);
		System.out.println("Are lengths equal? " + length1.equals(length2));
	}

	public Length(double value, LengthUnit unit) {

		if (unit == null) {
			throw new IllegalArgumentException("Unit cannot be null");
		}
		if (!Double.isFinite(value)) {
			throw new IllegalArgumentException("Invalid Length Value");
		}

		this.value = value;
		this.unit = unit;
	}

	private double convertToBaseUnit() {
		return unit.convertToBaseUnit(value);
	}

	public boolean compare(Length other) {
		double thisBase = this.convertToBaseUnit();
		double thatBase = other.convertToBaseUnit();

		double epsilon = 0.0001;
		return Math.abs(thisBase - thatBase) < epsilon;
	}

	public Length convertTo(LengthUnit targetUnit) {
		if (targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null");
		}

		double baseValue = convertToBaseUnit();
		double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
		convertedValue = Math.round(convertedValue * 100.0) / 100.0;

		return new Length(convertedValue, targetUnit);
	}

	public Length add(Length other) {
		return add(other, this.unit);
	}

	public Length add(Length other, LengthUnit targetUnit) {

		if (other == null) {
			throw new IllegalArgumentException("Other length cannot be null");
		}

		if (targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null");
		}

		double baseSum = this.convertToBaseUnit() + other.convertToBaseUnit();

		double resultValue = targetUnit.convertFromBaseUnit(baseSum);

		resultValue = Math.round(resultValue * 1000.0) / 1000.0;

		return new Length(resultValue, targetUnit);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (getClass() != o.getClass()) {
			return false;
		}
		Length length = (Length) o;
		return compare(length);
	}

	@Override
	public String toString() {
		return String.format("%.2f %s", value, unit);
	}

}
