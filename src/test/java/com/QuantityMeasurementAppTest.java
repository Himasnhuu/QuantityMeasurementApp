package com;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

	private static final double EPSILON = 0.0001;

	@Test
	void testTemperatureEquality_CelsiusToCelsius_SameValue() {
		assertTrue(new Quantity<>(0.0, TemperatureUnit.CELSIUS).equals(new Quantity<>(0.0, TemperatureUnit.CELSIUS)));
	}

	@Test
	void testTemperatureEquality_FahrenheitToFahrenheit_SameValue() {
		assertTrue(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT)
				.equals(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT)));
	}

	@Test
	void testTemperatureEquality_CelsiusToFahrenheit_0Celsius32Fahrenheit() {
		assertTrue(
				new Quantity<>(0.0, TemperatureUnit.CELSIUS).equals(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT)));
	}

	@Test
	void testTemperatureEquality_CelsiusToFahrenheit_100Celsius212Fahrenheit() {
		assertTrue(new Quantity<>(100.0, TemperatureUnit.CELSIUS)
				.equals(new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT)));
	}

	@Test
	void testTemperatureEquality_CelsiusToFahrenheit_Negative40Equal() {
		assertTrue(new Quantity<>(-40.0, TemperatureUnit.CELSIUS)
				.equals(new Quantity<>(-40.0, TemperatureUnit.FAHRENHEIT)));
	}

	@Test
	void testTemperatureEquality_SymmetricProperty() {

		Quantity<TemperatureUnit> a = new Quantity<>(0.0, TemperatureUnit.CELSIUS);

		Quantity<TemperatureUnit> b = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

		assertTrue(a.equals(b));
		assertTrue(b.equals(a));
	}

	@Test
	void testTemperatureEquality_ReflexiveProperty() {

		Quantity<TemperatureUnit> t = new Quantity<>(10.0, TemperatureUnit.CELSIUS);

		assertTrue(t.equals(t));
	}

	@Test
	void testTemperatureConversion_CelsiusToFahrenheit_VariousValues() {

		assertEquals(122.0,
				new Quantity<>(50.0, TemperatureUnit.CELSIUS).convertTo(TemperatureUnit.FAHRENHEIT).getValue(),
				EPSILON);

		assertEquals(-4.0,
				new Quantity<>(-20.0, TemperatureUnit.CELSIUS).convertTo(TemperatureUnit.FAHRENHEIT).getValue(),
				EPSILON);
	}

	@Test
	void testTemperatureConversion_FahrenheitToCelsius_VariousValues() {

		assertEquals(50.0,
				new Quantity<>(122.0, TemperatureUnit.FAHRENHEIT).convertTo(TemperatureUnit.CELSIUS).getValue(),
				EPSILON);
	}

	@Test
	void testTemperatureConversion_RoundTrip_PreservesValue() {

		Quantity<TemperatureUnit> original = new Quantity<>(25.0, TemperatureUnit.CELSIUS);

		Quantity<TemperatureUnit> converted = original.convertTo(TemperatureUnit.FAHRENHEIT)
				.convertTo(TemperatureUnit.CELSIUS);

		assertEquals(original.getValue(), converted.getValue(), EPSILON);
	}

	@Test
	void testTemperatureConversion_SameUnit() {

		Quantity<TemperatureUnit> t = new Quantity<>(10.0, TemperatureUnit.CELSIUS);

		Quantity<TemperatureUnit> result = t.convertTo(TemperatureUnit.CELSIUS);

		assertEquals(10.0, result.getValue(), EPSILON);
	}

	@Test
	void testTemperatureConversion_ZeroValue() {

		assertEquals(32.0,
				new Quantity<>(0.0, TemperatureUnit.CELSIUS).convertTo(TemperatureUnit.FAHRENHEIT).getValue(), EPSILON);
	}

	@Test
	void testTemperatureConversion_NegativeValues() {

		assertEquals(-40.0,
				new Quantity<>(-40.0, TemperatureUnit.CELSIUS).convertTo(TemperatureUnit.FAHRENHEIT).getValue(),
				EPSILON);
	}

	@Test
	void testTemperatureConversion_LargeValues() {

		assertEquals(1832.0,
				new Quantity<>(1000.0, TemperatureUnit.CELSIUS).convertTo(TemperatureUnit.FAHRENHEIT).getValue(),
				EPSILON);
	}

	@Test
	void testTemperatureUnsupportedOperation_Add() {

		assertThrows(UnsupportedOperationException.class, () -> new Quantity<>(100.0, TemperatureUnit.CELSIUS)
				.add(new Quantity<>(50.0, TemperatureUnit.CELSIUS)));
	}

	@Test
	void testTemperatureUnsupportedOperation_Subtract() {

		assertThrows(UnsupportedOperationException.class, () -> new Quantity<>(100.0, TemperatureUnit.CELSIUS)
				.subtract(new Quantity<>(50.0, TemperatureUnit.CELSIUS)));
	}

	@Test
	void testTemperatureUnsupportedOperation_Divide() {

		assertThrows(UnsupportedOperationException.class, () -> new Quantity<>(100.0, TemperatureUnit.CELSIUS)
				.divide(new Quantity<>(50.0, TemperatureUnit.CELSIUS)));
	}

	@Test
	void testTemperatureUnsupportedOperation_ErrorMessage() {

		Exception ex = assertThrows(UnsupportedOperationException.class,
				() -> new Quantity<>(100.0, TemperatureUnit.CELSIUS)
						.add(new Quantity<>(50.0, TemperatureUnit.CELSIUS)));

		assertTrue(ex.getMessage().contains("Temperature"));
	}

	@Test
	void testTemperatureVsLengthIncompatibility() {

		assertFalse(new Quantity<>(100.0, TemperatureUnit.CELSIUS).equals(new Quantity<>(100.0, LengthUnit.FEET)));
	}

	@Test
	void testTemperatureVsWeightIncompatibility() {

		assertFalse(new Quantity<>(50.0, TemperatureUnit.CELSIUS).equals(new Quantity<>(50.0, WeightUnit.KILOGRAM)));
	}

	@Test
	void testTemperatureVsVolumeIncompatibility() {

		assertFalse(new Quantity<>(25.0, TemperatureUnit.CELSIUS).equals(new Quantity<>(25.0, VolumeUnit.LITRE)));
	}

	@Test
	void testOperationSupportMethods_TemperatureUnitAddition() {

		assertFalse(TemperatureUnit.CELSIUS.supportsArithmetic());
	}

	@Test
	void testOperationSupportMethods_TemperatureUnitDivision() {

		assertFalse(TemperatureUnit.FAHRENHEIT.supportsArithmetic());
	}

	@Test
	void testOperationSupportMethods_LengthUnitAddition() {

		assertTrue(LengthUnit.FEET.supportsArithmetic());
	}

	@Test
	void testOperationSupportMethods_WeightUnitDivision() {

		assertTrue(WeightUnit.KILOGRAM.supportsArithmetic());
	}

	@Test
	void testTemperatureEnumImplementsIMeasurable() {

		assertTrue(IMeasurable.class.isAssignableFrom(TemperatureUnit.class));
	}

	@Test
	void testTemperatureUnit_AllConstants() {

		assertNotNull(TemperatureUnit.CELSIUS);
		assertNotNull(TemperatureUnit.FAHRENHEIT);
	}

	@Test
	void testTemperatureUnit_NameMethod() {

		assertEquals("CELSIUS", TemperatureUnit.CELSIUS.getUnitName());
	}

	@Test
	void testTemperatureUnit_ConversionFactor() {

		assertEquals(1.0, TemperatureUnit.CELSIUS.getConversionFactor());
	}

	@Test
	void testTemperatureNullUnitValidation() {

		assertThrows(IllegalArgumentException.class, () -> new Quantity<>(100.0, null));
	}

	@Test
	void testTemperatureNullOperandValidation_InComparison() {

		Quantity<TemperatureUnit> t = new Quantity<>(10.0, TemperatureUnit.CELSIUS);

		assertFalse(t.equals(null));
	}

	@Test
	void testTemperatureDifferentValuesInequality() {

		assertFalse(
				new Quantity<>(50.0, TemperatureUnit.CELSIUS).equals(new Quantity<>(100.0, TemperatureUnit.CELSIUS)));
	}

	@Test
	void testTemperatureConversionPrecision_Epsilon() {

		Quantity<TemperatureUnit> c = new Quantity<>(37.0, TemperatureUnit.CELSIUS);

		Quantity<TemperatureUnit> f = c.convertTo(TemperatureUnit.FAHRENHEIT);

		Quantity<TemperatureUnit> back = f.convertTo(TemperatureUnit.CELSIUS);

		assertEquals(c.getValue(), back.getValue(), EPSILON);
	}

	@Test
	void testTemperatureConversionEdgeCase_VerySmallDifference() {

		Quantity<TemperatureUnit> c = new Quantity<>(0.00001, TemperatureUnit.CELSIUS);

		Quantity<TemperatureUnit> f = c.convertTo(TemperatureUnit.FAHRENHEIT);

		assertNotNull(f);
	}

	@Test
	void testTemperatureIntegrationWithGenericQuantity() {

		Quantity<TemperatureUnit> t = new Quantity<>(20.0, TemperatureUnit.CELSIUS);

		Quantity<TemperatureUnit> converted = t.convertTo(TemperatureUnit.FAHRENHEIT);

		assertNotNull(converted);
	}

	@Test
	void testTemperatureValidateOperationSupport_MethodBehavior() {

		assertThrows(UnsupportedOperationException.class,
				() -> TemperatureUnit.CELSIUS.validateOperationSupport("addition"));
	}
}