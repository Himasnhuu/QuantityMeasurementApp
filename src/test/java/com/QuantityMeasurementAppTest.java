package com;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class QuantityMeasurementAppTest {

private static final double EPSILON = 0.0001;

	

	@Test
	public void testLengthUnitEnum_FeetConstant() {
		assertEquals(1.0, LengthUnit.FEET.getConversionFactor(), EPSILON);
	}

	@Test
	public void testLengthUnitEnum_InchesConstant() {
		assertEquals(1.0 / 12.0, LengthUnit.INCHES.getConversionFactor(), EPSILON);
	}

	@Test
	public void testLengthUnitEnum_YardsConstant() {
		assertEquals(3.0, LengthUnit.YARDS.getConversionFactor(), EPSILON);
	}

	@Test
	public void testLengthUnitEnum_CentimetersConstant() {
		assertEquals(1.0 / 30.48, LengthUnit.CENTIMETERS.getConversionFactor(), EPSILON);
	}

	

	@Test
	public void testConvertToBaseUnit_FeetToFeet() {
		double result = LengthUnit.FEET.convertToBaseUnit(5.0);
		assertEquals(5.0, result, EPSILON);
	}

	@Test
	public void testConvertToBaseUnit_InchesToFeet() {
		double result = LengthUnit.INCHES.convertToBaseUnit(12.0);
		assertEquals(1.0, result, EPSILON);
	}

	@Test
	public void testConvertToBaseUnit_YardsToFeet() {
		double result = LengthUnit.YARDS.convertToBaseUnit(1.0);
		assertEquals(3.0, result, EPSILON);
	}

	@Test
	public void testConvertToBaseUnit_CentimetersToFeet() {
		double result = LengthUnit.CENTIMETERS.convertToBaseUnit(30.48);
		assertEquals(1.0, result, EPSILON);
	}

	

	@Test
	public void testConvertFromBaseUnit_FeetToFeet() {
		double result = LengthUnit.FEET.convertFromBaseUnit(2.0);
		assertEquals(2.0, result, EPSILON);
	}

	@Test
	public void testConvertFromBaseUnit_FeetToInches() {
		double result = LengthUnit.INCHES.convertFromBaseUnit(1.0);
		assertEquals(12.0, result, EPSILON);
	}

	@Test
	public void testConvertFromBaseUnit_FeetToYards() {
		double result = LengthUnit.YARDS.convertFromBaseUnit(3.0);
		assertEquals(1.0, result, EPSILON);
	}

	@Test
	public void testConvertFromBaseUnit_FeetToCentimeters() {
		double result = LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0);
		assertEquals(30.48, result, EPSILON);
	}

	

	@Test
	public void testQuantityLengthRefactored_Equality() {

		Length feet = new Length(1.0, LengthUnit.FEET);
		Length inches = new Length(12.0, LengthUnit.INCHES);

		assertTrue(feet.equals(inches));
	}

	@Test
	public void testQuantityLengthRefactored_ConvertTo() {

		Length feet = new Length(1.0, LengthUnit.FEET);
		Length inches = feet.convertTo(LengthUnit.INCHES);

		Length expected = new Length(12.0, LengthUnit.INCHES);

		assertTrue(inches.equals(expected));
	}

	@Test
	public void testQuantityLengthRefactored_Add() {

		Length feet = new Length(1.0, LengthUnit.FEET);
		Length inches = new Length(12.0, LengthUnit.INCHES);

		Length result = feet.add(inches, LengthUnit.FEET);

		Length expected = new Length(2.0, LengthUnit.FEET);

		assertTrue(result.equals(expected));
	}

	@Test
	public void testQuantityLengthRefactored_AddWithTargetUnit() {

		Length feet = new Length(1.0, LengthUnit.FEET);
		Length inches = new Length(12.0, LengthUnit.INCHES);

		Length result = feet.add(inches, LengthUnit.YARDS);

		Length expected = new Length(0.667, LengthUnit.YARDS);

		assertTrue(result.equals(expected));
	}

	

	@Test
	public void testQuantityLengthRefactored_NullUnit() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Length(1.0, null);
		});
	}

	@Test
	public void testQuantityLengthRefactored_InvalidValue() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Length(Double.NaN, LengthUnit.FEET);
		});
	}

	

	@Test
	public void testRoundTripConversion_RefactoredDesign() {

		Length original = new Length(3.0, LengthUnit.FEET);

		Length converted = original.convertTo(LengthUnit.INCHES).convertTo(LengthUnit.FEET);

		assertTrue(original.equals(converted));
	}

	

	@Test
	public void testArchitecturalScalability_MultipleCategories() {

		
		assertNotNull(LengthUnit.FEET);
		assertNotNull(LengthUnit.INCHES);
		assertNotNull(LengthUnit.YARDS);
		assertNotNull(LengthUnit.CENTIMETERS);
	}

	

	@Test
	public void testUnitImmutability() {

		LengthUnit unit = LengthUnit.FEET;

		assertEquals("FEET", unit.name());

		
		assertThrows(NoSuchMethodException.class, () -> {
			LengthUnit.class.getDeclaredMethod("setConversionFactor", double.class);
		});
	}
}
