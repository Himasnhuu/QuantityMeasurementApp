package com;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

	private static final double EPSILON = 0.0001;

	@Test
	public void testEquality_LitreToLitre_SameValue() {
		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(1.0, VolumeUnit.LITRE);

		assertTrue(v1.equals(v2));
	}

	@Test
	public void testEquality_LitreToLitre_DifferentValue() {
		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(2.0, VolumeUnit.LITRE);

		assertFalse(v1.equals(v2));
	}

	@Test
	public void testEquality_LitreToMillilitre_EquivalentValue() {
		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

		assertTrue(v1.equals(v2));
	}

	@Test
	public void testEquality_MillilitreToLitre_EquivalentValue() {
		Quantity<VolumeUnit> v1 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(1.0, VolumeUnit.LITRE);

		assertTrue(v1.equals(v2));
	}

	@Test
	public void testEquality_LitreToGallon_EquivalentValue() {
		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(0.264172, VolumeUnit.GALLON);

		assertTrue(v1.equals(v2));
	}

	@Test
	public void testEquality_GallonToLitre_EquivalentValue() {
		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.GALLON);
		Quantity<VolumeUnit> v2 = new Quantity<>(3.78541, VolumeUnit.LITRE);

		assertTrue(v1.equals(v2));
	}

	// -----------------------------
	// Category Incompatibility
	// -----------------------------

	@Test
	public void testEquality_VolumeVsLength_Incompatible() {
		Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);

		assertFalse(volume.equals(length));
	}

	@Test
	public void testEquality_VolumeVsWeight_Incompatible() {
		Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		assertFalse(volume.equals(weight));
	}

	// -----------------------------
	// Conversion Tests
	// -----------------------------

	@Test
	public void testConversion_LitreToMillilitre() {
		Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.MILLILITRE);

		assertEquals(1000.0, result.getValue(), EPSILON);
	}

	@Test
	public void testConversion_MillilitreToLitre() {
		Quantity<VolumeUnit> v = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
		Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.LITRE);

		assertEquals(1.0, result.getValue(), EPSILON);
	}

	@Test
	public void testConversion_LitreToGallon() {
		Quantity<VolumeUnit> v = new Quantity<>(3.78541, VolumeUnit.LITRE);
		Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.GALLON);

		assertEquals(1.0, result.getValue(), EPSILON);
	}

	@Test
	public void testConversion_RoundTrip() {
		Quantity<VolumeUnit> v = new Quantity<>(1.5, VolumeUnit.LITRE);

		Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.MILLILITRE).convertTo(VolumeUnit.LITRE);

		assertEquals(1.5, result.getValue(), EPSILON);
	}

	// -----------------------------
	// Addition Tests
	// -----------------------------

	@Test
	public void testAddition_SameUnit_LitrePlusLitre() {

		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(2.0, VolumeUnit.LITRE);

		Quantity<VolumeUnit> result = v1.add(v2);

		assertEquals(3.0, result.getValue(), EPSILON);
	}

	@Test
	public void testAddition_CrossUnit_LitrePlusMillilitre() {

		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

		Quantity<VolumeUnit> result = v1.add(v2);

		assertEquals(2.0, result.getValue(), EPSILON);
	}

	@Test
	public void testAddition_ExplicitTargetUnit_Millilitre() {

		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

		Quantity<VolumeUnit> result = v1.add(v2, VolumeUnit.MILLILITRE);

		assertEquals(2000.0, result.getValue(), EPSILON);
	}

	@Test
	public void testAddition_ExplicitTargetUnit_Gallon() {

		Quantity<VolumeUnit> v1 = new Quantity<>(3.78541, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(3.78541, VolumeUnit.LITRE);

		Quantity<VolumeUnit> result = v1.add(v2, VolumeUnit.GALLON);

		assertEquals(2.0, result.getValue(), EPSILON);
	}

	// -----------------------------
	// VolumeUnit Enum Tests
	// -----------------------------

	@Test
	public void testVolumeUnitEnum_LitreConstant() {
		assertEquals(1.0, VolumeUnit.LITRE.getConversionFactor(), EPSILON);
	}

	@Test
	public void testVolumeUnitEnum_MillilitreConstant() {
		assertEquals(0.001, VolumeUnit.MILLILITRE.getConversionFactor(), EPSILON);
	}

	@Test
	public void testVolumeUnitEnum_GallonConstant() {
		assertEquals(3.78541, VolumeUnit.GALLON.getConversionFactor(), EPSILON);
	}

	// -----------------------------
	// Base Unit Conversion Tests
	// -----------------------------

	@Test
	public void testConvertToBaseUnit_MillilitreToLitre() {
		double result = VolumeUnit.MILLILITRE.convertToBaseUnit(1000.0);

		assertEquals(1.0, result, EPSILON);
	}

	@Test
	public void testConvertToBaseUnit_GallonToLitre() {
		double result = VolumeUnit.GALLON.convertToBaseUnit(1.0);

		assertEquals(3.78541, result, EPSILON);
	}

	@Test
	public void testConvertFromBaseUnit_LitreToMillilitre() {
		double result = VolumeUnit.MILLILITRE.convertFromBaseUnit(1.0);

		assertEquals(1000.0, result, EPSILON);
	}

	@Test
	public void testConvertFromBaseUnit_LitreToGallon() {
		double result = VolumeUnit.GALLON.convertFromBaseUnit(3.78541);

		assertEquals(1.0, result, EPSILON);
	}
}
