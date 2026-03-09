package com;

public class Length {

	private double value;
	private LengthUnit unit;

	public static void main(String[] args) {
		Length length1 = new Length(1.0, LengthUnit.FEET);
		Length length2 = new Length(12.0, LengthUnit.INCHES);
		System.out.println("Are lengths equal? " + length1.equals(length2));
	}

	// -----------------------------------------------------------------------
	// enum
	public enum LengthUnit {
		FEET(12.0), INCHES(1.0), YARDS(36.0), CENTIMETERS(1.0 / 2.54);

		private final double conversionFactor;

		LengthUnit(double conversionFactor) {
			this.conversionFactor = conversionFactor;
		}

		public double getConversionFactor() {
			return conversionFactor;
		}
	}
	// -------------------------------------------------------------------------

	public Length(double value, LengthUnit unit) {
		this.value = value;
		this.unit = unit;
	}

	private double convertToBaseUnit() {
		return this.value * this.unit.getConversionFactor();
	}

	public boolean compare(Length thatLength) {
		double thisBase = this.convertToBaseUnit();
		double thatBase = thatLength.convertToBaseUnit();

		double epsilon = 0.0001;
		return Math.abs(thisBase - thatBase) < epsilon;
	}

	public Length convertTo(LengthUnit targetUnit) {
		if (targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null");
		}

		double baseValue = this.convertToBaseUnit();
		double convertedValue = baseValue / targetUnit.getConversionFactor();
		convertedValue = Math.round(convertedValue * 100.0) / 100.0;

		return new Length(convertedValue, targetUnit);
	}

	public Length add(Length other) {

		if (other == null) {
			throw new IllegalArgumentException("Length to add cannot be null");
		}

		if (this.unit == null || other.unit == null) {
			throw new IllegalArgumentException("Length unit cannot be null");
		}

		if (!Double.isFinite(this.value) || !Double.isFinite(other.value)) {
			throw new IllegalArgumentException("Length values must be finite numbers");
		}

		double thisBase = this.convertToBaseUnit();
		double otherBase = other.convertToBaseUnit();

		double sumBase = thisBase + otherBase;

		double resultValue = sumBase / this.unit.getConversionFactor();

		resultValue = Math.round(resultValue * 100.0) / 100.0;

		return new Length(resultValue, this.unit);
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
