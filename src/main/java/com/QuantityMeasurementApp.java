package com;

public class QuantityMeasurementApp {
	
	public static Length demonstrateLengthAddition(
	        Length length1,
	        Length length2,
	        Length.LengthUnit targetUnit) {

	    return length1.add(length2, targetUnit);
	}

	public static void main(String[] args) {

		System.out.println("\n--- UC7 Explicit Target Unit Addition ---");

		Length a = new Length(1.0, Length.LengthUnit.FEET);
		Length b = new Length(12.0, Length.LengthUnit.INCHES);

		System.out.println("1 ft + 12 in -> FEET: "
		        + demonstrateLengthAddition(a, b, Length.LengthUnit.FEET));

		System.out.println("1 ft + 12 in -> INCHES: "
		        + demonstrateLengthAddition(a, b, Length.LengthUnit.INCHES));

		System.out.println("1 ft + 12 in -> YARDS: "
		        + demonstrateLengthAddition(a, b, Length.LengthUnit.YARDS));

		Length yard = new Length(1.0, Length.LengthUnit.YARDS);
		Length feet = new Length(3.0, Length.LengthUnit.FEET);

		System.out.println("1 yd + 3 ft -> YARDS: "
		        + demonstrateLengthAddition(yard, feet, Length.LengthUnit.YARDS));
	}
}