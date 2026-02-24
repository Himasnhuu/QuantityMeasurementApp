package com;

public class QuantityMeasurementApp {

	public static class Feet {
		private final double value;

		public Feet(double value) {
			this.value = value;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			Feet other = (Feet) obj;
			return Double.compare(this.value, other.value) == 0;
		}
	}

	public static class Inches {
		private final double value;

		public Inches(double value) {
			this.value = value;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			Inches other = (Inches) obj;
			return Double.compare(this.value, other.value) == 0;
		}
	}

	public static void demonstrateFeetEquality() {
		Feet feet1 = new Feet(1.0);
		Feet feet2 = new Feet(1.0);
		System.out.println("Comparing 1.0 ft and 1.0 ft: " + feet1.equals(feet2));

		Feet feet3 = new Feet(1.0);
		Feet feet4 = new Feet(2.0);
		System.out.println("Comparing 1.0 ft and 2.0 ft: " + feet3.equals(feet4));
	}

	public static void demonstrateInchesEquality() {
		Inches inches1 = new Inches(1.0);
		Inches inches2 = new Inches(1.0);
		System.out.println("Comparing 1.0 inch and 1.0 inch: " + inches1.equals(inches2));

		Inches inches3 = new Inches(1.0);
		Inches inches4 = new Inches(2.0);
		System.out.println("Comparing 1.0 inch and 2.0 inch: " + inches3.equals(inches4));
	}

	public static boolean demonstrateLengthEquality(Length length1, Length length2) {
		return length1.equals(length2);
	}

	public static void demonstrateFeetInchesComparison() {
		Length feet = new Length(1.0, Length.LengthUnit.FEET);
		Length inches = new Length(12.0, Length.LengthUnit.INCHES);
		System.out.println("Comparing 1.0 ft and 12.0 inches: " + demonstrateLengthEquality(feet, inches));
	}

	public static void demonstrateYardsComparison() {
		// Demonstrate Yards and Inches comparison
		Length length = new Length(1.0, Length.LengthUnit.YARDS);
		Length inches = new Length(36.0, Length.LengthUnit.INCHES);
		System.out.println("Comparing 1.0 yd and 36.0 inches: " + demonstrateLengthEquality(length, inches));

		// Demonstrate Yards and Feet comparison
		Length yard1 = new Length(1.0, Length.LengthUnit.YARDS);
		Length feet = new Length(3.0, Length.LengthUnit.FEET);
		System.out.println("Comparing 1.0 yd and 3.0 ft: " + demonstrateLengthEquality(yard1, feet));
	}

	public static void demonstrateCentimetersComparison() {
		// Demonstrate Centimeters and Inches comparison
		Length cm = new Length(1.0, Length.LengthUnit.CENTIMETERS);
		Length inches = new Length(0.393701, Length.LengthUnit.INCHES);
		System.out.println("Comparing 1.0 cm and 0.393701 inches: " + demonstrateLengthEquality(cm, inches));

		// Demonstrate Centimeters and Feet comparison
		Length cm2 = new Length(30.48, Length.LengthUnit.CENTIMETERS);
		Length feet = new Length(1.0, Length.LengthUnit.FEET);
		System.out.println("Comparing 30.48 cm and 1.0 ft: " + demonstrateLengthEquality(cm2, feet));
	}

	public static Length demonstrateLengthConversion(double value, Length.LengthUnit fromUnit,
			Length.LengthUnit toUnit) {
		Length lengthInYards = new Length(value, fromUnit);
		Length lengthInInches = lengthInYards.convertTo(toUnit);
		return lengthInInches;
	}

	public static Length demonstrateLengthConversion(Length length, Length.LengthUnit toUnit) {
		return length.convertTo(toUnit);
	}

	public static boolean demonstrateLengthComparison(double value1, Length.LengthUnit unit1, double value2,
			Length.LengthUnit unit2) {
		Length length1 = new Length(value1, unit1);
		Length length2 = new Length(value2, unit2);
		return demonstrateLengthEquality(length1, length2);
	}

	public static void main(String[] args) {
		demonstrateFeetEquality();
		demonstrateInchesEquality();
		demonstrateFeetInchesComparison();
		demonstrateYardsComparison();
		demonstrateCentimetersComparison();

		System.out.println("\n--- Unit Conversion Demonstrations ---");

		Length lengthInFeet = new Length(3.0, Length.LengthUnit.FEET);
		Length lengthInInches = demonstrateLengthConversion(lengthInFeet, Length.LengthUnit.INCHES);
		System.out.println("Converting 3.0 ft to inches: " + lengthInInches);

		Length lengthInYards = demonstrateLengthConversion(2.0, Length.LengthUnit.YARDS, Length.LengthUnit.INCHES);
		System.out.println("Converting 2.0 yd to inches: " + lengthInYards);

		Length lengthInCm = new Length(30.48, Length.LengthUnit.CENTIMETERS);
		Length expectedLength = demonstrateLengthConversion(lengthInCm, Length.LengthUnit.FEET);
		System.out.println("Converting 30.48 cm to feet: " + expectedLength);

		demonstrateLengthComparison(3.0, Length.LengthUnit.FEET, 1.0, Length.LengthUnit.YARDS);
	}
}
