package com;

public class QuantityMeasurementApp {

	// Equality demonstration
	public static boolean demonstrateLengthEquality(Length length1, Length length2) {
		if (length1 == null || length2 == null) {
			throw new IllegalArgumentException("Lengths cannot be null");
		}

		return length1.equals(length2);
	}

	// Comparison using raw values and units
	public static boolean demonstrateLengthComparison(double value1, LengthUnit unit1, double value2,
			LengthUnit unit2) {

		Length length1 = new Length(value1, unit1);
		Length length2 = new Length(value2, unit2);

		return demonstrateLengthEquality(length1, length2);
	}

	// Conversion using raw value and unit
	public static Length demonstrateLengthConversion(double value, LengthUnit fromUnit, LengthUnit toUnit) {

		Length length = new Length(value, fromUnit);
		return length.convertTo(toUnit);
	}

	// Conversion using Length object
	public static Length demonstrateLengthConversion(Length length, LengthUnit toUnit) {

		if (length == null) {
			throw new IllegalArgumentException("Length cannot be null");
		}

		return length.convertTo(toUnit);
	}

	// Addition (UC6 – result in unit of first operand)
	public static Length demonstrateLengthAddition(Length length1, Length length2) {

		if (length1 == null || length2 == null) {
			throw new IllegalArgumentException("Lengths cannot be null");
		}

		return length1.add(length2);
	}

	// Addition with explicit target unit (UC7)
	public static Length demonstrateLengthAddition(Length length1, Length length2, LengthUnit targetUnit) {

		if (length1 == null || length2 == null) {
			throw new IllegalArgumentException("Lengths cannot be null");
		}

		return length1.add(length2, targetUnit);
	}

	public static void main(String[] args) {

		System.out.println("---- Equality Demonstration ----");

		Length feet = new Length(1.0, LengthUnit.FEET);
		Length inches = new Length(12.0, LengthUnit.INCHES);

		System.out.println("1 ft == 12 in : " + demonstrateLengthEquality(feet, inches));

		System.out.println("\n---- Comparison Using Raw Values ----");

		System.out.println("3 ft == 1 yd : " + demonstrateLengthComparison(3.0, LengthUnit.FEET, 1.0, LengthUnit.YARDS));

		System.out.println("\n---- Conversion Demonstration ----");

		Length converted = demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);

		System.out.println("1 ft -> inches : " + converted);

		System.out.println("\n---- Conversion Using Length Object ----");

		Length cm = new Length(30.48, LengthUnit.CENTIMETERS);

		Length convertedFeet = demonstrateLengthConversion(cm, LengthUnit.FEET);

		System.out.println("30.48 cm -> feet : " + convertedFeet);

		System.out.println("\n---- Addition (UC6) ----");

		Length result1 = demonstrateLengthAddition(new Length(1.0, LengthUnit.FEET),
				new Length(12.0, LengthUnit.INCHES));

		System.out.println("1 ft + 12 in = " + result1);

		System.out.println("\n---- Addition with Target Unit (UC7) ----");

		Length result2 = demonstrateLengthAddition(new Length(1.0, LengthUnit.FEET),
				new Length(12.0, LengthUnit.INCHES), LengthUnit.INCHES);

		System.out.println("1 ft + 12 in -> inches = " + result2);

		Length result3 = demonstrateLengthAddition(new Length(1.0, LengthUnit.FEET),
				new Length(12.0, LengthUnit.INCHES), LengthUnit.YARDS);

		System.out.println("1 ft + 12 in -> yards = " + result3);
	}
}