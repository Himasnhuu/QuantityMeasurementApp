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

	// ---------- Equality Tests ----------

	@Test
	public void testEquality_KilogramToKilogram_SameValue() {
		assertTrue(new Weight(1.0, WeightUnit.KILOGRAM).equals(new Weight(1.0, WeightUnit.KILOGRAM)));
	}

	@Test
	public void testEquality_KilogramToKilogram_DifferentValue() {
		assertFalse(new Weight(1.0, WeightUnit.KILOGRAM).equals(new Weight(2.0, WeightUnit.KILOGRAM)));
	}

	@Test
	public void testEquality_KilogramToGram_EquivalentValue() {
		assertTrue(new Weight(1.0, WeightUnit.KILOGRAM).equals(new Weight(1000.0, WeightUnit.GRAM)));
	}

	@Test
	public void testEquality_GramToKilogram_EquivalentValue() {
		assertTrue(new Weight(1000.0, WeightUnit.GRAM).equals(new Weight(1.0, WeightUnit.KILOGRAM)));
	}

	@Test
	public void testEquality_WeightVsLength_Incompatible() {

		Weight weight = new Weight(1.0, WeightUnit.KILOGRAM);
		Length length = new Length(1.0, LengthUnit.FEET);

		assertFalse(weight.equals(length));
	}

	@Test
	public void testEquality_NullComparison() {
		assertFalse(new Weight(1.0, WeightUnit.KILOGRAM).equals(null));
	}

	@Test
	public void testEquality_SameReference() {

		Weight w = new Weight(1.0, WeightUnit.KILOGRAM);

		assertTrue(w.equals(w));
	}

	@Test
	public void testEquality_ZeroValue() {
		assertTrue(new Weight(0.0, WeightUnit.KILOGRAM).equals(new Weight(0.0, WeightUnit.GRAM)));
	}

	@Test
	public void testEquality_NegativeWeight() {
		assertTrue(new Weight(-1.0, WeightUnit.KILOGRAM).equals(new Weight(-1000.0, WeightUnit.GRAM)));
	}

	@Test
	public void testEquality_LargeWeightValue() {
		assertTrue(new Weight(1000000.0, WeightUnit.GRAM).equals(new Weight(1000.0, WeightUnit.KILOGRAM)));
	}

	@Test
	public void testEquality_SmallWeightValue() {
		assertTrue(new Weight(0.001, WeightUnit.KILOGRAM).equals(new Weight(1.0, WeightUnit.GRAM)));
	}

	// ---------- Conversion Tests ----------

	@Test
	public void testConversion_PoundToKilogram() {

		Weight pound = new Weight(2.20462, WeightUnit.POUND);
		Weight kg = pound.convertTo(WeightUnit.KILOGRAM);

		Weight expected = new Weight(1.0, WeightUnit.KILOGRAM);

		assertTrue(kg.equals(expected));
	}

	// ---------- Addition Tests ----------

	@Test
	public void testAddition_SameUnit_KilogramPlusKilogram() {

		Weight result = new Weight(1.0, WeightUnit.KILOGRAM).add(new Weight(2.0, WeightUnit.KILOGRAM));

		assertTrue(result.equals(new Weight(3.0, WeightUnit.KILOGRAM)));
	}

	@Test
	public void testAddition_CrossUnit_KilogramPlusGram() {

		Weight result = new Weight(1.0, WeightUnit.KILOGRAM).add(new Weight(1000.0, WeightUnit.GRAM));

		assertTrue(result.equals(new Weight(2.0, WeightUnit.KILOGRAM)));
	}

	@Test
	public void testAddition_CrossUnit_PoundPlusKilogram() {

		Weight result = new Weight(2.20462, WeightUnit.POUND).add(new Weight(1.0, WeightUnit.KILOGRAM));

		assertTrue(result.equals(new Weight(4.40924, WeightUnit.POUND)));
	}

	@Test
	public void testAddition_ExplicitTargetUnit_Kilogram() {

		Weight result = new Weight(1.0, WeightUnit.KILOGRAM).add(new Weight(1000.0, WeightUnit.GRAM), WeightUnit.GRAM);

		assertTrue(result.equals(new Weight(2000.0, WeightUnit.GRAM)));
	}

	@Test
	public void testAddition_WithZero() {

		Weight result = new Weight(5.0, WeightUnit.KILOGRAM).add(new Weight(0.0, WeightUnit.GRAM));

		assertTrue(result.equals(new Weight(5.0, WeightUnit.KILOGRAM)));
	}

	@Test
	public void testAddition_NegativeValues() {

		Weight result = new Weight(5.0, WeightUnit.KILOGRAM).add(new Weight(-2000.0, WeightUnit.GRAM));

		assertTrue(result.equals(new Weight(3.0, WeightUnit.KILOGRAM)));
	}

	@Test
	public void testAddition_LargeValues() {

		Weight result = new Weight(1e6, WeightUnit.KILOGRAM).add(new Weight(1e6, WeightUnit.KILOGRAM));

		assertTrue(result.equals(new Weight(2e6, WeightUnit.KILOGRAM)));
	}
}
