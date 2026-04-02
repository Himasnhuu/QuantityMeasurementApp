package com;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.quantitymeasurement.Quantity;
import com.app.quantitymeasurement.unit.LengthUnit;
import com.app.quantitymeasurement.unit.TemperatureUnit;
import com.app.quantitymeasurement.unit.VolumeUnit;
import com.app.quantitymeasurement.unit.WeightUnit;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.app.quantitymeasurement.service.QuantityMeasurementServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.app.quantitymeasurement.QuantityMeasurementApp.class)
public class QuantityMeasurementAppTest {

	private static final double EPSILON = 1e-6;

	@Autowired
	private IQuantityMeasurementRepository repository;

	// LENGTH EQUALITY TESTS

	@Test
	void testLength_FeetToInches_Equivalent() {
		Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);

		assertTrue(l1.equals(l2));
	}

	@Test
	void testLength_SameUnit() {
		Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.INCHES);
		Quantity<LengthUnit> l2 = new Quantity<>(1.0, LengthUnit.INCHES);

		assertTrue(l1.equals(l2));
	}

	@Test
	void testLength_DifferentValue() {
		Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> l2 = new Quantity<>(2.0, LengthUnit.FEET);

		assertFalse(l1.equals(l2));
	}

	@Test
	void testLength_InvalidUnit() {
		assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, null));
	}

	@Test
	void testEquality_YardToYard_SameValue() {
		Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.YARDS);
		Quantity<LengthUnit> l2 = new Quantity<>(1.0, LengthUnit.YARDS);
		assertTrue(l1.equals(l2));
	}

	@Test
	void testEquality_YardToFeet_EquivalentValue() {
		Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.YARDS);
		Quantity<LengthUnit> l2 = new Quantity<>(3.0, LengthUnit.FEET);
		assertTrue(l1.equals(l2));
	}

	@Test
	void testEquality_YardToInches_EquivalentValue() {
		Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.YARDS);
		Quantity<LengthUnit> l2 = new Quantity<>(36.0, LengthUnit.INCHES);
		assertTrue(l1.equals(l2));
	}

	@Test
	void testEquality_CentimetersToInches_EquivalentValue() {
		Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.CENTIMETERS);
		Quantity<LengthUnit> l2 = new Quantity<>(0.393701, LengthUnit.INCHES);
		assertTrue(l1.equals(l2));
	}

	@Test
	void testEquality_MultiUnit_TransitiveProperty() {
		Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARDS);
		Quantity<LengthUnit> feet = new Quantity<>(3.0, LengthUnit.FEET);
		Quantity<LengthUnit> inches = new Quantity<>(36.0, LengthUnit.INCHES);

		assertTrue(yard.equals(feet));
		assertTrue(feet.equals(inches));
		assertTrue(yard.equals(inches));
	}

	// LENGTH CONVERSION TESTS

	@Test
	void testConversion_FeetToInches() {
		Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> result = q.convertTo(LengthUnit.INCHES);

		assertEquals(12.0, result.getValue(), EPSILON);
	}

	@Test
	void testConversion_YardsToFeet() {
		Quantity<LengthUnit> q = new Quantity<>(3.0, LengthUnit.YARDS);
		Quantity<LengthUnit> result = q.convertTo(LengthUnit.FEET);

		assertEquals(9.0, result.getValue(), EPSILON);
	}

	@Test
	void testConversion_CentimetersToInches() {
		Quantity<LengthUnit> q = new Quantity<>(2.54, LengthUnit.CENTIMETERS);
		Quantity<LengthUnit> result = q.convertTo(LengthUnit.INCHES);

		assertEquals(1.0, result.getValue(), EPSILON);
	}

	@Test
	void testConversion_SameUnit() {
		Quantity<LengthUnit> q = new Quantity<>(5.0, LengthUnit.FEET);
		Quantity<LengthUnit> result = q.convertTo(LengthUnit.FEET);

		assertEquals(5.0, result.getValue(), EPSILON);
	}

	@Test
	void testConversion_InvalidInput() {
		assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, null));

		assertThrows(IllegalArgumentException.class, () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
	}

	// LENGTH ADDITION TESTS

	@Test
	void testAddition_SameUnit() {
		Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> l2 = new Quantity<>(2.0, LengthUnit.FEET);

		Quantity<LengthUnit> result = l1.add(l2);

		assertEquals(3.0, result.getValue(), EPSILON);
	}

	@Test
	void testAddition_CrossUnit_FeetPlusInches() {
		Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);

		Quantity<LengthUnit> result = l1.add(l2);

		assertEquals(2.0, result.getValue(), EPSILON);
	}

	@Test
	void testAddition_Commutativity() {
		Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);

		Quantity<LengthUnit> r1 = l1.add(l2);
		Quantity<LengthUnit> r2 = l2.add(l1);

		assertEquals(r1.convertTo(LengthUnit.INCHES).getValue(), r2.convertTo(LengthUnit.INCHES).getValue(), EPSILON);
	}

	@Test
	void testAddition_WithZero() {
		Quantity<LengthUnit> l1 = new Quantity<>(5.0, LengthUnit.FEET);
		Quantity<LengthUnit> zero = new Quantity<>(0.0, LengthUnit.INCHES);

		Quantity<LengthUnit> result = l1.add(zero);

		assertEquals(5.0, result.getValue(), EPSILON);
	}

	@Test
	void testAddition_NullOperand() {
		Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);

		assertThrows(IllegalArgumentException.class, () -> l1.add(null));
	}

	@Test
	void testAddition_TargetUnit_Feet() {
		Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);

		Quantity<LengthUnit> result = l1.add(l2, LengthUnit.FEET);

		assertEquals(2.0, result.getValue(), EPSILON);
	}

	@Test
	void testAddition_TargetUnit_Inches() {
		Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);

		Quantity<LengthUnit> result = l1.add(l2, LengthUnit.INCHES);

		assertEquals(24.0, result.getValue(), EPSILON);
	}

	@Test
	void testAddition_TargetUnit_Yards() {
		Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);

		Quantity<LengthUnit> result = l1.add(l2, LengthUnit.YARDS);

		assertEquals(0.667, result.getValue(), 1e-3);
	}

	@Test
	void testAddition_TargetUnit_Commutative() {
		Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);

		Quantity<LengthUnit> r1 = l1.add(l2, LengthUnit.YARDS);
		Quantity<LengthUnit> r2 = l2.add(l1, LengthUnit.YARDS);

		assertEquals(r1.getValue(), r2.getValue(), EPSILON);
	}

	@Test
	void testAddition_TargetUnit_Null() {
		Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);

		assertThrows(IllegalArgumentException.class, () -> l1.add(l2, null));
	}

	// WEIGHT TESTS

	@Test
	void testEquality_KilogramToKilogram_SameValue() {
		Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> w2 = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		assertTrue(w1.equals(w2));
	}

	@Test
	void testEquality_KilogramToGram_EquivalentValue() {
		Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

		assertTrue(w1.equals(w2));
	}

	@Test
	void testEquality_KilogramToPound_EquivalentValue() {
		Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> w2 = new Quantity<>(2.20462, WeightUnit.POUND);

		assertTrue(w1.equals(w2));
	}

	@Test
	void testConversion_KilogramToGram() {
		Quantity<WeightUnit> w = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		Quantity<WeightUnit> result = w.convertTo(WeightUnit.GRAM);

		assertEquals(1000.0, result.getValue(), EPSILON);
	}

	@Test
	void testConversion_PoundToKilogram() {
		Quantity<WeightUnit> w = new Quantity<>(2.20462, WeightUnit.POUND);

		Quantity<WeightUnit> result = w.convertTo(WeightUnit.KILOGRAM);

		assertEquals(1.0, result.getValue(), 1e-3);
	}

	@Test
	void testAddition_SameUnit_KilogramPlusKilogram() {
		Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> w2 = new Quantity<>(2.0, WeightUnit.KILOGRAM);

		Quantity<WeightUnit> result = w1.add(w2);

		assertEquals(3.0, result.getValue(), EPSILON);
	}

	@Test
	void testAddition_CrossUnit_KilogramPlusGram() {
		Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

		Quantity<WeightUnit> result = w1.add(w2);

		assertEquals(2.0, result.getValue(), EPSILON);
	}

	@Test
	void testAddition_ExplicitTargetUnit() {
		Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

		Quantity<WeightUnit> result = w1.add(w2, WeightUnit.GRAM);

		assertEquals(2000.0, result.getValue(), EPSILON);
	}

	@Test
	void testEquality_NullComparison() {
		Quantity<WeightUnit> w = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		assertFalse(w.equals(null));
	}

	@Test
	void testConversion_ZeroValue() {
		Quantity<WeightUnit> w = new Quantity<>(0.0, WeightUnit.KILOGRAM);

		Quantity<WeightUnit> result = w.convertTo(WeightUnit.GRAM);

		assertEquals(0.0, result.getValue(), EPSILON);
	}

	// UC10 GENERIC TEST

	@Test
	void testCrossCategoryComparison_LengthVsWeight() {

		Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		assertFalse(length.equals(weight));
	}

	// UC11 VOLUME TESTS

	@Test
	void testEquality_LitreToLitre_SameValue() {
		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(1.0, VolumeUnit.LITRE);

		assertTrue(v1.equals(v2));
	}

	@Test
	void testEquality_LitreToMillilitre_EquivalentValue() {
		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

		assertTrue(v1.equals(v2));
	}

	@Test
	void testEquality_GallonToLitre_EquivalentValue() {
		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.GALLON);
		Quantity<VolumeUnit> v2 = new Quantity<>(3.78541, VolumeUnit.LITRE);

		assertTrue(v1.equals(v2));
	}

	@Test
	void testConversion_LitreToMillilitre() {
		Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);

		Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.MILLILITRE);

		assertEquals(1000.0, result.getValue(), EPSILON);
	}

	@Test
	void testConversion_GallonToLitre() {
		Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.GALLON);

		Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.LITRE);

		assertEquals(3.78541, result.getValue(), 1e-3);
	}

	@Test
	void testAddition_SameUnit_LitrePlusLitre() {
		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(2.0, VolumeUnit.LITRE);

		Quantity<VolumeUnit> result = v1.add(v2);

		assertEquals(3.0, result.getValue(), EPSILON);
	}

	@Test
	void testAddition_CrossUnit_LitrePlusMillilitre() {
		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

		Quantity<VolumeUnit> result = v1.add(v2);

		assertEquals(2.0, result.getValue(), EPSILON);
	}

	@Test
	void testAddition_TargetUnit_Millilitre() {
		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

		Quantity<VolumeUnit> result = v1.add(v2, VolumeUnit.MILLILITRE);

		assertEquals(2000.0, result.getValue(), EPSILON);
	}

	@Test
	void testCrossCategoryComparison_VolumeVsLength() {
		Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);

		Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);

		assertFalse(volume.equals(length));
	}

	@Test
	void testCrossCategoryComparison_VolumeVsWeight() {
		Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);

		Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		assertFalse(volume.equals(weight));
	}

	// UC12

	@Test
	void testSubtraction_SameUnit_Feet() {
		Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

		Quantity<LengthUnit> result = q1.subtract(q2);

		assertEquals(5.0, result.getValue(), EPSILON);
	}

	@Test
	void testSubtraction_CrossUnit_FeetMinusInches() {
		Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);

		Quantity<LengthUnit> result = q1.subtract(q2);

		assertEquals(9.5, result.getValue(), EPSILON);
	}

	@Test
	void testSubtraction_ResultZero() {
		Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(120.0, LengthUnit.INCHES);

		Quantity<LengthUnit> result = q1.subtract(q2);

		assertEquals(0.0, result.getValue(), EPSILON);
	}

	@Test
	void testSubtraction_NullOperand() {
		Quantity<LengthUnit> q = new Quantity<>(10.0, LengthUnit.FEET);

		assertThrows(IllegalArgumentException.class, () -> q.subtract(null));
	}

	@Test
	void testDivision_SameUnit() {
		Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);

		double result = q1.divide(q2);

		assertEquals(5.0, result, EPSILON);
	}

	@Test
	void testDivision_CrossUnit() {
		Quantity<LengthUnit> q1 = new Quantity<>(24.0, LengthUnit.INCHES);
		Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);

		double result = q1.divide(q2);

		assertEquals(1.0, result, EPSILON);
	}

	@Test
	void testDivision_ByZero() {
		Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(0.0, LengthUnit.FEET);

		assertThrows(ArithmeticException.class, () -> q1.divide(q2));
	}

	@Test
	void testDivision_NullOperand() {
		Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);

		assertThrows(IllegalArgumentException.class, () -> q1.divide(null));
	}

	// UC13 REFACTORING TESTS

	@Test
	void testArithmeticOperation_Add_Computation() {
		double result = 10 + 5;
		assertEquals(15.0, result);
	}

	@Test
	void testArithmeticOperation_Subtract_Computation() {
		double result = 10 - 5;
		assertEquals(5.0, result);
	}

	@Test
	void testArithmeticOperation_Divide_Computation() {
		double result = 10.0 / 5.0;
		assertEquals(2.0, result);
	}

	@Test
	void testValidation_NullOperand_Add() {
		Quantity<LengthUnit> q = new Quantity<>(10.0, LengthUnit.FEET);

		assertThrows(IllegalArgumentException.class, () -> q.add(null));
	}

	@Test
	void testValidation_NullOperand_Subtract() {
		Quantity<LengthUnit> q = new Quantity<>(10.0, LengthUnit.FEET);

		assertThrows(IllegalArgumentException.class, () -> q.subtract(null));
	}

	@Test
	void testValidation_NullOperand_Divide() {
		Quantity<LengthUnit> q = new Quantity<>(10.0, LengthUnit.FEET);

		assertThrows(IllegalArgumentException.class, () -> q.divide(null));
	}

	@Test
	void testValidation_CrossCategory_Add() {

		Quantity<LengthUnit> length = new Quantity<>(10.0, LengthUnit.FEET);

		Quantity<WeightUnit> weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);

		assertThrows(IllegalArgumentException.class, () -> length.add((Quantity) weight));
	}

	@Test
	void testValidation_CrossCategory_Subtract() {

		Quantity<LengthUnit> length = new Quantity<>(10.0, LengthUnit.FEET);

		Quantity<WeightUnit> weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);

		assertThrows(IllegalArgumentException.class, () -> length.subtract((Quantity) weight));
	}

	@Test
	void testValidation_CrossCategory_Divide() {

		Quantity<LengthUnit> length = new Quantity<>(10.0, LengthUnit.FEET);

		Quantity<WeightUnit> weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);

		assertThrows(IllegalArgumentException.class, () -> length.divide((Quantity) weight));
	}

	@Test
	void testDivide_ByZero_Exception() {

		Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);

		Quantity<LengthUnit> q2 = new Quantity<>(0.0, LengthUnit.FEET);

		assertThrows(ArithmeticException.class, () -> q1.divide(q2));
	}

	@Test
	void testImmutability_AfterAddition() {

		Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);

		Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);

		Quantity<LengthUnit> result = q1.add(q2);

		assertEquals(10.0, q1.getValue(), EPSILON);
		assertEquals(2.0, q2.getValue(), EPSILON);
		assertEquals(12.0, result.getValue(), EPSILON);
	}

	@Test
	void testImmutability_AfterSubtraction() {

		Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);

		Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

		Quantity<LengthUnit> result = q1.subtract(q2);

		assertEquals(10.0, q1.getValue(), EPSILON);
		assertEquals(5.0, q2.getValue(), EPSILON);
		assertEquals(5.0, result.getValue(), EPSILON);
	}

	@Test
	void testAllOperations_AcrossCategories() {

		Quantity<LengthUnit> l1 = new Quantity<>(10.0, LengthUnit.FEET);

		Quantity<LengthUnit> l2 = new Quantity<>(2.0, LengthUnit.FEET);

		assertEquals(12.0, l1.add(l2).getValue(), EPSILON);
		assertEquals(8.0, l1.subtract(l2).getValue(), EPSILON);
		assertEquals(5.0, l1.divide(l2), EPSILON);
	}

	// UC14 TEMPERATURE TESTS

	@Test
	void testTemperatureEquality_CelsiusToCelsius() {
		Quantity<TemperatureUnit> t1 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);

		Quantity<TemperatureUnit> t2 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);

		assertTrue(t1.equals(t2));
	}

	@Test
	void testTemperatureEquality_CelsiusToFahrenheit() {
		Quantity<TemperatureUnit> t1 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);

		Quantity<TemperatureUnit> t2 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

		assertTrue(t1.equals(t2));
	}

	@Test
	void testTemperatureEquality_CelsiusToKelvin() {
		Quantity<TemperatureUnit> t1 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);

		Quantity<TemperatureUnit> t2 = new Quantity<>(273.15, TemperatureUnit.KELVIN);

		assertTrue(t1.equals(t2));
	}

	@Test
	void testTemperatureEquality_Negative40Point() {
		Quantity<TemperatureUnit> t1 = new Quantity<>(-40.0, TemperatureUnit.CELSIUS);

		Quantity<TemperatureUnit> t2 = new Quantity<>(-40.0, TemperatureUnit.FAHRENHEIT);

		assertTrue(t1.equals(t2));
	}

	// TEMPERATURE CONVERSION

	@Test
	void testTemperatureConversion_CelsiusToFahrenheit() {

		Quantity<TemperatureUnit> t = new Quantity<>(100.0, TemperatureUnit.CELSIUS);

		Quantity<TemperatureUnit> result = t.convertTo(TemperatureUnit.FAHRENHEIT);

		assertEquals(212.0, result.getValue(), EPSILON);
	}

	@Test
	void testTemperatureConversion_FahrenheitToCelsius() {

		Quantity<TemperatureUnit> t = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

		Quantity<TemperatureUnit> result = t.convertTo(TemperatureUnit.CELSIUS);

		assertEquals(0.0, result.getValue(), EPSILON);
	}

	@Test
	void testTemperatureConversion_CelsiusToKelvin() {

		Quantity<TemperatureUnit> t = new Quantity<>(0.0, TemperatureUnit.CELSIUS);

		Quantity<TemperatureUnit> result = t.convertTo(TemperatureUnit.KELVIN);

		assertEquals(273.15, result.getValue(), EPSILON);
	}

	@Test
	void testTemperatureConversion_KelvinToCelsius() {

		Quantity<TemperatureUnit> t = new Quantity<>(273.15, TemperatureUnit.KELVIN);

		Quantity<TemperatureUnit> result = t.convertTo(TemperatureUnit.CELSIUS);

		assertEquals(0.0, result.getValue(), EPSILON);
	}

	@Test
	void testTemperatureConversion_SameUnit() {

		Quantity<TemperatureUnit> t = new Quantity<>(50.0, TemperatureUnit.CELSIUS);

		Quantity<TemperatureUnit> result = t.convertTo(TemperatureUnit.CELSIUS);

		assertEquals(50.0, result.getValue(), EPSILON);
	}

	// UNSUPPORTED OPERATIONS

	@Test
	void testTemperature_Add_NotSupported() {

		Quantity<TemperatureUnit> t1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);

		Quantity<TemperatureUnit> t2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);

		assertThrows(UnsupportedOperationException.class, () -> t1.add(t2));
	}

	@Test
	void testTemperature_Subtract_NotSupported() {

		Quantity<TemperatureUnit> t1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);

		Quantity<TemperatureUnit> t2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);

		assertThrows(UnsupportedOperationException.class, () -> t1.subtract(t2));
	}

	@Test
	void testTemperature_Divide_NotSupported() {

		Quantity<TemperatureUnit> t1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);

		Quantity<TemperatureUnit> t2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);

		assertThrows(UnsupportedOperationException.class, () -> t1.divide(t2));
	}

	// CROSS CATEGORY TESTS

	@Test
	void testTemperatureVsLength() {

		Quantity<TemperatureUnit> temp = new Quantity<>(100.0, TemperatureUnit.CELSIUS);

		Quantity<LengthUnit> length = new Quantity<>(100.0, LengthUnit.FEET);

		assertFalse(temp.equals(length));
	}

	@Test
	void testTemperatureVsWeight() {

		Quantity<TemperatureUnit> temp = new Quantity<>(50.0, TemperatureUnit.CELSIUS);

		Quantity<WeightUnit> weight = new Quantity<>(50.0, WeightUnit.KILOGRAM);

		assertFalse(temp.equals(weight));
	}

	@Test
	void testTemperatureVsVolume() {

		Quantity<TemperatureUnit> temp = new Quantity<>(25.0, TemperatureUnit.CELSIUS);

		Quantity<VolumeUnit> volume = new Quantity<>(25.0, VolumeUnit.LITRE);

		assertFalse(temp.equals(volume));
	}

	// UC17 TESTS

	@Test
	void testRepository_SaveMeasurement() {

		repository.deleteAll();

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity("ADD", "Quantity(1 FEET)",
				"Quantity(12 INCHES)", "Quantity(2 FEET)");

		repository.save(entity);

		assertEquals(1, repository.findAll().size());
	}

	@Test
	void testRepository_FindAllMeasurements() {

		repository.deleteAll();

		repository.save(
				new QuantityMeasurementEntity("ADD", "Quantity(1 FEET)", "Quantity(12 INCHES)", "Quantity(2 FEET)"));

		repository.save(new QuantityMeasurementEntity("SUBTRACT", "Quantity(10 FEET)", "Quantity(6 INCHES)",
				"Quantity(9.5 FEET)"));

		assertEquals(2, repository.findAll().size());
	}

	@Test
	void testRepository_DeleteAllMeasurements() {

		repository.save(
				new QuantityMeasurementEntity("ADD", "Quantity(1 FEET)", "Quantity(12 INCHES)", "Quantity(2 FEET)"));

		repository.deleteAll();

		assertTrue(repository.findAll().isEmpty());
	}

	@Test
	void testService_IntegrationWithRepository() {

		repository.deleteAll();

		QuantityMeasurementServiceImpl service = new QuantityMeasurementServiceImpl(repository);

		service.add(new QuantityDTO(1.0, "FEET"), new QuantityDTO(12.0, "INCHES"));

		assertEquals(1, repository.findAll().size());
	}

	@Test
	void testRepository_MultipleOperationsStored() {

		repository.deleteAll();

		repository.save(
				new QuantityMeasurementEntity("ADD", "Quantity(1 FEET)", "Quantity(12 INCHES)", "Quantity(2 FEET)"));

		repository.save(new QuantityMeasurementEntity("DIVIDE", "Quantity(10 FEET)", "Quantity(2 FEET)", "5"));

		repository.save(new QuantityMeasurementEntity("SUBTRACT", "Quantity(5 LITRE)", "Quantity(500 ML)",
				"Quantity(4.5 LITRE)"));

		assertEquals(3, repository.findAll().size());
	}

	@Test
	void testRepository_SaveErrorMeasurement() {

		repository.deleteAll();

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity("Division by zero");

		repository.save(entity);

		assertEquals(1, repository.findAll().size());
	}
}
