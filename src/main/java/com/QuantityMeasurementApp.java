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

		// -------- Equality Demonstration --------
		Quantity<WeightUnit> weightInGrams = new Quantity<>(1000.0, WeightUnit.GRAM);

		Quantity<WeightUnit> weightInKilograms = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		boolean areEqual = demonstrateEquality(weightInGrams, weightInKilograms);

		System.out.println("Are weights equal? " + areEqual);

		// -------- Conversion Demonstration --------
		Quantity<WeightUnit> convertedWeight = demonstrateConversion(weightInGrams, WeightUnit.KILOGRAM);

		System.out.println("Converted Weight: " + convertedWeight.getValue() + " " + convertedWeight.getUnit());

		// -------- Addition (implicit target unit) --------
		Quantity<WeightUnit> weightInPounds = new Quantity<>(2.20462, WeightUnit.POUND);

		Quantity<WeightUnit> sumWeight = demonstrateAddition(weightInKilograms, weightInPounds);

		System.out.println("Sum Weight: " + sumWeight.getValue() + " " + sumWeight.getUnit());

		// -------- Addition (explicit target unit) --------
		Quantity<WeightUnit> sumWeightInGrams = demonstrateAddition(weightInKilograms, weightInPounds, WeightUnit.GRAM);

		System.out.println("Sum Weight in Grams: " + sumWeightInGrams.getValue() + " " + sumWeightInGrams.getUnit());

	}
}