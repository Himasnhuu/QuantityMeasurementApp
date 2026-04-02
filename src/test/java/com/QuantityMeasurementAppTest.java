package com;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

	@Test
	public void testFeetEquality_SameValue() {
		Feet feet1 = new Feet(1.0);
		Feet feet2 = new Feet(1.0);
		assertTrue(feet1.equals(feet2));
	}

	@Test
	public void testFeetEquality_DifferentValue() {
		Feet feet1 = new Feet(1.0);
		Feet feet2 = new Feet(2.0);
		assertFalse(feet1.equals(feet2));
	}

	@Test
	public void testFeetEquality_NullComparison() {
		Feet feet1 = new Feet(1.0);
		assertFalse(feet1.equals(null));
	}

	@Test
	public void testFeetEquality_DifferentClass() {
		Feet feet1 = new Feet(1.0);
		String nonFeetObject = "1.0";
		assertFalse(feet1.equals(nonFeetObject));
	}

	@Test
	public void testFeetEquality_SameReference() {
		Feet feet1 = new Feet(1.0);
		assertTrue(feet1.equals(feet1));
	}

	@Test
	public void testInchesEquality_SameValue() {
		Inches inches1 = new Inches(1.0);
		Inches inches2 = new Inches(1.0);
		assertTrue(inches1.equals(inches2));
	}

	@Test
	public void testInchesEquality_DifferentValue() {
		Inches inches1 = new Inches(1.0);
		Inches inches2 = new Inches(2.0);
		assertFalse(inches1.equals(inches2));
	}

	@Test
	public void testInchesEquality_NullComparison() {
		Inches inches1 = new Inches(1.0);
		assertFalse(inches1.equals(null));
	}

	@Test
	public void testInchesEquality_DifferentClass() {
		Inches inches1 = new Inches(1.0);
		String nonInchesObject = "1.0";
		assertFalse(inches1.equals(nonInchesObject));
	}

	@Test
	public void testInchesEquality_SameReference() {
		Inches inches1 = new Inches(1.0);
		assertTrue(inches1.equals(inches1));
	}

	@Test
	public void testFeetEquality() {
		Length feet1 = new Length(1.0, Length.LengthUnit.FEET);
		Length feet2 = new Length(1.0, Length.LengthUnit.FEET);
		assertTrue(feet1.equals(feet2));
	}

	@Test
	public void testInchesEquality() {
		Length inches1 = new Length(1.0, Length.LengthUnit.INCHES);
		Length inches2 = new Length(1.0, Length.LengthUnit.INCHES);
		assertTrue(inches1.equals(inches2));
	}

	@Test
	public void testFeetInchesComparison() {
		Length feet = new Length(1.0, Length.LengthUnit.FEET);
		Length inches = new Length(12.0, Length.LengthUnit.INCHES);
		assertTrue(feet.equals(inches));
	}

	@Test
	public void testFeetInequality() {
		Length feet1 = new Length(1.0, Length.LengthUnit.FEET);
		Length feet2 = new Length(2.0, Length.LengthUnit.FEET);
		assertFalse(feet1.equals(feet2));
	}

	@Test
	public void testInchesInequality() {
		Length inches1 = new Length(1.0, Length.LengthUnit.INCHES);
		Length inches2 = new Length(2.0, Length.LengthUnit.INCHES);
		assertFalse(inches1.equals(inches2));
	}

	@Test
	public void testCrossUnitInequality() {
		Length feet = new Length(1.0, Length.LengthUnit.FEET);
		Length inches = new Length(1.0, Length.LengthUnit.INCHES);
		assertFalse(feet.equals(inches));
	}

	@Test
	public void testMultipleFeetComparison() {
		Length feet1 = new Length(3.0, Length.LengthUnit.FEET);
		Length feet2 = new Length(36.0, Length.LengthUnit.INCHES);
		assertTrue(feet1.equals(feet2));
	}

	@Test
	public void yardEquals36Inches() {
		Length yard = new Length(1.0, Length.LengthUnit.YARDS);
		Length inches = new Length(36.0, Length.LengthUnit.INCHES);
		assertTrue(yard.equals(inches));
	}

	@Test
	public void centimeterEquals39Point3701Inches() {
		Length cm = new Length(2.54, Length.LengthUnit.CENTIMETERS);
		Length inches = new Length(1.0, Length.LengthUnit.INCHES);
		assertTrue(cm.equals(inches));
	}

	@Test
	public void threeFeetEqualsOneYard() {
		Length feet = new Length(3.0, Length.LengthUnit.FEET);
		Length yard = new Length(1.0, Length.LengthUnit.YARDS);
		assertTrue(feet.equals(yard));
	}

	@Test
	public void yardNotEqualToInches() {
		Length yard = new Length(1.0, Length.LengthUnit.YARDS);
		Length inches = new Length(1.0, Length.LengthUnit.INCHES);
		assertFalse(yard.equals(inches));
	}

	@Test
	public void yardNotEqualToFeet() {
		Length yard = new Length(1.0, Length.LengthUnit.YARDS);
		Length feet = new Length(1.0, Length.LengthUnit.FEET);
		assertFalse(yard.equals(feet));
	}

	@Test
	public void referenceEqualitySameObject() {
		Length yard = new Length(1.0, Length.LengthUnit.YARDS);
		assertTrue(yard.equals(yard));
	}

	@Test
	public void equalsReturnsFalseForNull() {
		Length yard = new Length(1.0, Length.LengthUnit.YARDS);
		assertFalse(yard.equals(null));
	}

	@Test
	public void differentValuesameUnitNotEqual() {
		Length yard1 = new Length(1.0, Length.LengthUnit.YARDS);
		Length yard2 = new Length(2.0, Length.LengthUnit.YARDS);
		assertFalse(yard1.equals(yard2));
	}

	@Test
	public void reflexiveSymmetricAndTransitiveProperty() {
		Length yard = new Length(1.0, Length.LengthUnit.YARDS);
		Length feet = new Length(3.0, Length.LengthUnit.FEET);
		Length inches = new Length(36.0, Length.LengthUnit.INCHES);

		// Reflexive
		assertTrue(yard.equals(yard));

		// Symmetric
		assertTrue(yard.equals(feet));
		assertTrue(feet.equals(yard));

		// Transitive
		assertTrue(yard.equals(feet));
		assertTrue(feet.equals(inches));
		assertTrue(yard.equals(inches));
	}

	@Test
	public void thirtyPoint48CmEqualsOneFoot() {
		Length cm = new Length(30.48, Length.LengthUnit.CENTIMETERS);
		Length feet = new Length(1.0, Length.LengthUnit.FEET);
		assertTrue(cm.equals(feet));
	}

	@Test
	public void convertFeetToInches() {
		Length lengthInInches = QuantityMeasurementApp.demonstrateLengthConversion(1.0, Length.LengthUnit.FEET,
				Length.LengthUnit.INCHES);
		Length expectedLength = new Length(12.0, Length.LengthUnit.INCHES);
		assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(lengthInInches, expectedLength));
	}

	@Test
	public void convertYardsToInchesUsingOverloadedMethod() {
		Length lengthInYards = new Length(2.0, Length.LengthUnit.YARDS);
		Length lengthInInches = QuantityMeasurementApp.demonstrateLengthConversion(lengthInYards,
				Length.LengthUnit.INCHES);
		Length expectedLength = new Length(72.0, Length.LengthUnit.INCHES);
		assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(lengthInInches, expectedLength));
	}

	@Test
	public void convertInchesToFeet() {
		Length lengthInInches = new Length(24.0, Length.LengthUnit.INCHES);
		Length lengthInFeet = lengthInInches.convertTo(Length.LengthUnit.FEET);
		Length expectedLength = new Length(2.0, Length.LengthUnit.FEET);
		assertTrue(lengthInFeet.equals(expectedLength));
	}

	@Test
	public void convertYardsToInches() {
		Length lengthInYards = new Length(1.0, Length.LengthUnit.YARDS);
		Length lengthInInches = lengthInYards.convertTo(Length.LengthUnit.INCHES);
		Length expectedLength = new Length(36.0, Length.LengthUnit.INCHES);
		assertTrue(lengthInInches.equals(expectedLength));
	}

	@Test
	public void convertInchesToYards() {
		Length lengthInInches = new Length(72.0, Length.LengthUnit.INCHES);
		Length lengthInYards = lengthInInches.convertTo(Length.LengthUnit.YARDS);
		Length expectedLength = new Length(2.0, Length.LengthUnit.YARDS);
		assertTrue(lengthInYards.equals(expectedLength));
	}

	@Test
	public void convertCentimetersToInches() {
		Length lengthInCm = new Length(2.54, Length.LengthUnit.CENTIMETERS);
		Length lengthInInches = lengthInCm.convertTo(Length.LengthUnit.INCHES);
		Length expectedLength = new Length(1.0, Length.LengthUnit.INCHES);
		assertTrue(lengthInInches.equals(expectedLength));
	}

	@Test
	public void convertFeetToYards() {
		Length lengthInFeet = new Length(6.0, Length.LengthUnit.FEET);
		Length lengthInYards = lengthInFeet.convertTo(Length.LengthUnit.YARDS);
		Length expectedLength = new Length(2.0, Length.LengthUnit.YARDS);
		assertTrue(lengthInYards.equals(expectedLength));
	}

	@Test
	public void convertRoundTripPreservesValue() {
		Length original = new Length(3.0, Length.LengthUnit.FEET);
		Length converted = original.convertTo(Length.LengthUnit.INCHES).convertTo(Length.LengthUnit.FEET);
		assertTrue(original.equals(converted));
	}

	@Test
	public void convertZeroValue() {
		Length lengthInFeet = new Length(0.0, Length.LengthUnit.FEET);
		Length lengthInInches = lengthInFeet.convertTo(Length.LengthUnit.INCHES);
		Length expectedLength = new Length(0.0, Length.LengthUnit.INCHES);
		assertTrue(lengthInInches.equals(expectedLength));
	}

	@Test
	public void convertNegativeValue() {
		Length lengthInFeet = new Length(-1.0, Length.LengthUnit.FEET);
		Length lengthInInches = lengthInFeet.convertTo(Length.LengthUnit.INCHES);
		Length expectedLength = new Length(-12.0, Length.LengthUnit.INCHES);
		assertTrue(lengthInInches.equals(expectedLength));
	}

	@Test
	public void convertLargeValue() {
		Length lengthInFeet = new Length(1000.0, Length.LengthUnit.FEET);
		Length lengthInInches = lengthInFeet.convertTo(Length.LengthUnit.INCHES);
		Length expectedLength = new Length(12000.0, Length.LengthUnit.INCHES);
		assertTrue(lengthInInches.equals(expectedLength));
	}

	@Test
	public void convertSmallValue() {
		Length lengthInInches = new Length(0.12, Length.LengthUnit.INCHES);
		Length lengthInFeet = lengthInInches.convertTo(Length.LengthUnit.FEET);
		Length backToInches = lengthInFeet.convertTo(Length.LengthUnit.INCHES);
		// Verify round-trip conversion for small values
		assertTrue(lengthInInches.equals(backToInches));
	}

	@Test
	public void convertSameUnit() {
		Length lengthInFeet = new Length(5.0, Length.LengthUnit.FEET);
		Length convertedLength = lengthInFeet.convertTo(Length.LengthUnit.FEET);
		assertTrue(lengthInFeet.equals(convertedLength));
	}

	@Test
	public void convertPrecisionRounding() {
		Length lengthInCm = new Length(30.48, Length.LengthUnit.CENTIMETERS);
		Length lengthInFeet = lengthInCm.convertTo(Length.LengthUnit.FEET);
		Length expectedLength = new Length(1.0, Length.LengthUnit.FEET);
		assertTrue(lengthInFeet.equals(expectedLength));
	}

	@Test
	public void convertInvalidUnitThrows() {
		Length lengthInFeet = new Length(1.0, Length.LengthUnit.FEET);
		assertThrows(IllegalArgumentException.class, () -> {
			lengthInFeet.convertTo(null);
		});
	}

	@Test
	public void convertMultipleSequentialConversions() {
		Length original = new Length(3.0, Length.LengthUnit.YARDS);
		Length toFeet = original.convertTo(Length.LengthUnit.FEET);
		Length toInches = toFeet.convertTo(Length.LengthUnit.INCHES);
		Length backToYards = toInches.convertTo(Length.LengthUnit.YARDS);
		assertTrue(original.equals(backToYards));
	}

	@Test
	public void convertBidirectionalConversion() {
		Length lengthInFeet = new Length(3.0, Length.LengthUnit.FEET);
		Length lengthInInches = lengthInFeet.convertTo(Length.LengthUnit.INCHES);
		Length backToFeet = lengthInInches.convertTo(Length.LengthUnit.FEET);
		assertTrue(lengthInFeet.equals(backToFeet));
	}

	@Test
	public void convertCrossUnitConversion() {
		Length lengthInFeet = new Length(1.0, Length.LengthUnit.FEET);
		Length lengthInCm = lengthInFeet.convertTo(Length.LengthUnit.CENTIMETERS);
		Length expectedLength = new Length(30.48, Length.LengthUnit.CENTIMETERS);
		assertTrue(lengthInCm.equals(expectedLength));
	}

	@Test
	public void convertBaseUnitNormalization() {
		Length lengthInYards = new Length(1.0, Length.LengthUnit.YARDS);
		Length lengthInInches = lengthInYards.convertTo(Length.LengthUnit.INCHES);
		Length expectedLength = new Length(36.0, Length.LengthUnit.INCHES); // 1 yard = 36 inches
		assertTrue(lengthInInches.equals(expectedLength));
	}

	@Test
	public void convertToStringRepresentation() {
		Length lengthInFeet = new Length(3.5, Length.LengthUnit.FEET);
		String representation = lengthInFeet.toString();
		assertEquals("3.50 FEET", representation);
	}

}
