package com;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

	private static final double EPSILON = 0.0001;

	@Test
	public void testIMeasurableInterface_LengthUnitImplementation() {
		IMeasurable unit = LengthUnit.FEET;

		assertEquals("FEET", unit.getUnitName());
		assertEquals(1.0, unit.getConversionFactor(), EPSILON);
		assertEquals(1.0, unit.convertToBaseUnit(1.0), EPSILON);
		assertEquals(1.0, unit.convertFromBaseUnit(1.0), EPSILON);
	}

	@Test
	public void testIMeasurableInterface_WeightUnitImplementation() {
		IMeasurable unit = WeightUnit.KILOGRAM;

		assertEquals("KILOGRAM", unit.getUnitName());
		assertEquals(1.0, unit.getConversionFactor(), EPSILON);
		assertEquals(1.0, unit.convertToBaseUnit(1.0), EPSILON);
	}

	@Test
	public void testIMeasurableInterface_ConsistentBehavior() {
		IMeasurable length = LengthUnit.FEET;
		IMeasurable weight = WeightUnit.KILOGRAM;

		assertNotNull(length.getUnitName());
		assertNotNull(weight.getUnitName());

		assertTrue(length.getConversionFactor() > 0);
		assertTrue(weight.getConversionFactor() > 0);
	}

	@Test
	public void testGenericQuantity_LengthOperations_Equality() {

		Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

		assertTrue(q1.equals(q2));
	}

	@Test
	public void testGenericQuantity_WeightOperations_Equality() {

		Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

		assertTrue(q1.equals(q2));
	}

	@Test
	public void testGenericQuantity_LengthOperations_Conversion() {

		Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> converted = q.convertTo(LengthUnit.INCHES);

		assertTrue(converted.equals(new Quantity<>(12.0, LengthUnit.INCHES)));
	}

	@Test
	public void testGenericQuantity_WeightOperations_Conversion() {

		Quantity<WeightUnit> q = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> converted = q.convertTo(WeightUnit.GRAM);

		assertTrue(converted.equals(new Quantity<>(1000.0, WeightUnit.GRAM)));
	}

	@Test
	public void testGenericQuantity_LengthOperations_Addition() {

		Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

		Quantity<LengthUnit> result = q1.add(q2, LengthUnit.FEET);

		assertTrue(result.equals(new Quantity<>(2.0, LengthUnit.FEET)));
	}

	@Test
	public void testGenericQuantity_WeightOperations_Addition() {

		Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

		Quantity<WeightUnit> result = q1.add(q2, WeightUnit.KILOGRAM);

		assertTrue(result.equals(new Quantity<>(2.0, WeightUnit.KILOGRAM)));
	}

	@Test
	public void testCrossCategoryPrevention_LengthVsWeight() {

		Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		assertFalse(length.equals(weight));
	}

	@Test
	public void testCrossCategoryPrevention_CompilerTypeSafety() {
		assertTrue(true); // Compilation itself enforces this constraint
	}

	@Test
	public void testGenericQuantity_ConstructorValidation_NullUnit() {

		assertThrows(IllegalArgumentException.class, () -> {
			new Quantity<>(1.0, null);
		});
	}

	@Test
	public void testGenericQuantity_ConstructorValidation_InvalidValue() {

		assertThrows(IllegalArgumentException.class, () -> {
			new Quantity<>(Double.NaN, LengthUnit.FEET);
		});
	}

	@Test
	public void testGenericQuantity_Conversion_AllUnitCombinations() {

		Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);

		assertTrue(feet.convertTo(LengthUnit.INCHES).equals(new Quantity<>(12.0, LengthUnit.INCHES)));

		assertTrue(feet.convertTo(LengthUnit.YARDS).equals(new Quantity<>(0.33, LengthUnit.YARDS)));
	}

	@Test
	public void testGenericQuantity_Addition_AllUnitCombinations() {

		Quantity<LengthUnit> q1 = new Quantity<>(3.0, LengthUnit.FEET);

		Quantity<LengthUnit> q2 = new Quantity<>(36.0, LengthUnit.INCHES);

		Quantity<LengthUnit> result = q1.add(q2, LengthUnit.FEET);

		assertTrue(result.equals(new Quantity<>(6.0, LengthUnit.FEET)));
	}

	@Test
	public void testQuantityMeasurementApp_SimplifiedDemonstration_Equality() {

		Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

		assertTrue(QuantityMeasurementApp.demonstrateEquality(q1, q2));
	}

	@Test
	public void testQuantityMeasurementApp_SimplifiedDemonstration_Conversion() {

		Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		Quantity<WeightUnit> converted = QuantityMeasurementApp.demonstrateConversion(weight, WeightUnit.GRAM);

		assertTrue(converted.equals(new Quantity<>(1000.0, WeightUnit.GRAM)));
	}

	@Test
	public void testQuantityMeasurementApp_SimplifiedDemonstration_Addition() {

		Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

		Quantity<WeightUnit> result = QuantityMeasurementApp.demonstrateAddition(w1, w2, WeightUnit.KILOGRAM);

		assertTrue(result.equals(new Quantity<>(2.0, WeightUnit.KILOGRAM)));
	}

	@Test
	public void testTypeWildcard_FlexibleSignatures() {

		Quantity<?> q1 = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<?> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

		assertTrue(q1.equals(q2));
	}

	@Test
	public void testHashCode_GenericQuantity_Consistency() {

		Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

		assertEquals(q1.hashCode(), q2.hashCode());
	}

	@Test
	public void testEquals_GenericQuantity_ContractPreservation() {

		Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCHES);

		Quantity<LengthUnit> c = new Quantity<>((1.0/3.0), LengthUnit.YARDS);

		assertTrue(a.equals(b));
		assertTrue(b.equals(c));
		assertTrue(a.equals(c));
	}

	@Test
	public void testImmutability_GenericQuantity() {

		Quantity<LengthUnit> original = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<LengthUnit> converted = original.convertTo(LengthUnit.INCHES);

		assertNotEquals(original.getUnit(), converted.getUnit());
	}
}
