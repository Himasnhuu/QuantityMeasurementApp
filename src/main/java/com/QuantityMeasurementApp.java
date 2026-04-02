package com;

public class QuantityMeasurementApp {

	public static <U extends IMeasurable> boolean demonstrateEquality(Quantity<U> quantity1, Quantity<U> quantity2) {

		if (quantity1 == null || quantity2 == null) {
			throw new IllegalArgumentException("Quantities cannot be null");
		}

		return quantity1.equals(quantity2);
	}

	public static <U extends IMeasurable> Quantity<U> demonstrateConversion(Quantity<U> quantity, U targetUnit) {

		if (quantity == null) {
			throw new IllegalArgumentException("Quantity cannot be null");
		}

		if (targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null");
		}

		return quantity.convertTo(targetUnit);
	}

	public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1,
			Quantity<U> quantity2) {

		if (quantity1 == null || quantity2 == null) {
			throw new IllegalArgumentException("Quantities cannot be null");
		}

		return quantity1.add(quantity2);
	}

	public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2,
			U targetUnit) {

		if (quantity1 == null || quantity2 == null) {
			throw new IllegalArgumentException("Quantities cannot be null");
		}

		if (targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null");
		}

		return quantity1.add(quantity2, targetUnit);
	}

	public static void main(String[] args) {

		// -------- LENGTH --------
		Quantity<LengthUnit> length1 = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<LengthUnit> length2 = new Quantity<>(12.0, LengthUnit.INCHES);

		System.out.println("Length Equality: " + demonstrateEquality(length1, length2));

		System.out.println("Length Conversion: " + demonstrateConversion(length1, LengthUnit.INCHES));

		System.out.println("Length Addition: " + demonstrateAddition(length1, length2, LengthUnit.FEET));

		// -------- WEIGHT --------
		Quantity<WeightUnit> weight1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		Quantity<WeightUnit> weight2 = new Quantity<>(1000.0, WeightUnit.GRAM);

		System.out.println("Weight Equality: " + demonstrateEquality(weight1, weight2));

		System.out.println("Weight Conversion: " + demonstrateConversion(weight1, WeightUnit.GRAM));

		System.out.println("Weight Addition: " + demonstrateAddition(weight1, weight2, WeightUnit.KILOGRAM));

		// -------- VOLUME (UC11) --------
		Quantity<VolumeUnit> volume1 = new Quantity<>(1.0, VolumeUnit.LITRE);

		Quantity<VolumeUnit> volume2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

		Quantity<VolumeUnit> volume3 = new Quantity<>(1.0, VolumeUnit.GALLON);

		System.out.println("Volume Equality: " + demonstrateEquality(volume1, volume2));

		System.out.println("Volume Conversion: " + demonstrateConversion(volume3, VolumeUnit.LITRE));

		System.out.println("Volume Addition: " + demonstrateAddition(volume1, volume2, VolumeUnit.LITRE));
	}
}