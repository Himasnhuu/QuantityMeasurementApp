package com;

public class QuantityMeasurementApp {
	
	public static Length demonstrateLengthAddition(Length length1, Length length2) {
	    return length1.add(length2);
	}

	public static void main(String[] args) {

		System.out.println("\n--- Length Addition Demonstrations ---");

		Length a = new Length(1.0, Length.LengthUnit.FEET);
		Length b = new Length(12.0, Length.LengthUnit.INCHES);
		System.out.println("1 ft + 12 in = " + demonstrateLengthAddition(a, b));

		Length c = new Length(12.0, Length.LengthUnit.INCHES);
		Length d = new Length(1.0, Length.LengthUnit.FEET);
		System.out.println("12 in + 1 ft = " + demonstrateLengthAddition(c, d));

		Length e = new Length(1.0, Length.LengthUnit.YARDS);
		Length f = new Length(3.0, Length.LengthUnit.FEET);
		System.out.println("1 yd + 3 ft = " + demonstrateLengthAddition(e, f));
	}
}