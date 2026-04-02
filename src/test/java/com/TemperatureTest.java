package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.core.Quantity;
import com.unit.LengthUnit;
import com.unit.TemperatureUnit;
import com.unit.WeightUnit;

public class TemperatureTest {
	private static final double EPSILON = 0.0001;

    // ------------------------------------------------------
    // Equality Tests
    // ------------------------------------------------------

    @Test
    void testTemperatureEquality_CelsiusToCelsius_SameValue() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        assertTrue(t1.equals(t2));
    }

    @Test
    void testTemperatureEquality_FahrenheitToFahrenheit_SameValue() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(t1.equals(t2));
    }

    @Test
    void testTemperatureEquality_CelsiusToFahrenheit_0Celsius32Fahrenheit() {

        Quantity<TemperatureUnit> celsius =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> fahrenheit =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(celsius.equals(fahrenheit));
    }

    @Test
    void testTemperatureEquality_CelsiusToFahrenheit_100Celsius212Fahrenheit() {

        Quantity<TemperatureUnit> celsius =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> fahrenheit =
                new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(celsius.equals(fahrenheit));
    }

    @Test
    void testTemperatureEquality_Negative40Equal() {

        Quantity<TemperatureUnit> c =
                new Quantity<>(-40.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> f =
                new Quantity<>(-40.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(c.equals(f));
    }

    // ------------------------------------------------------
    // Conversion Tests
    // ------------------------------------------------------

    @Test
    void testTemperatureConversion_CelsiusToFahrenheit() {

        Quantity<TemperatureUnit> celsius =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> fahrenheit =
                celsius.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(212.0, fahrenheit.getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_FahrenheitToCelsius() {

        Quantity<TemperatureUnit> fahrenheit =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        Quantity<TemperatureUnit> celsius =
                fahrenheit.convertTo(TemperatureUnit.CELSIUS);

        assertEquals(0.0, celsius.getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_RoundTrip() {

        Quantity<TemperatureUnit> original =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> converted =
                original.convertTo(TemperatureUnit.FAHRENHEIT)
                        .convertTo(TemperatureUnit.CELSIUS);

        assertEquals(original.getValue(), converted.getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_SameUnit() {

        Quantity<TemperatureUnit> celsius =
                new Quantity<>(25.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result =
                celsius.convertTo(TemperatureUnit.CELSIUS);

        assertEquals(25.0, result.getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_NegativeValues() {

        Quantity<TemperatureUnit> celsius =
                new Quantity<>(-20.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> fahrenheit =
                celsius.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(-4.0, fahrenheit.getValue(), EPSILON);
    }

    // ------------------------------------------------------
    // Unsupported Arithmetic
    // ------------------------------------------------------

    @Test
    void testTemperatureUnsupportedOperation_Add() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(
                UnsupportedOperationException.class,
                () -> t1.add(t2)
        );
    }

    @Test
    void testTemperatureUnsupportedOperation_Subtract() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(
                UnsupportedOperationException.class,
                () -> t1.subtract(t2)
        );
    }

    @Test
    void testTemperatureUnsupportedOperation_Divide() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(
                UnsupportedOperationException.class,
                () -> t1.divide(t2)
        );
    }

    // ------------------------------------------------------
    // Cross Category Safety
    // ------------------------------------------------------

    @Test
    void testTemperatureVsLengthIncompatibility() {

        Quantity<TemperatureUnit> temp =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        Quantity<LengthUnit> length =
                new Quantity<>(50.0, LengthUnit.FEET);

        assertFalse(temp.equals(length));
    }

    @Test
    void testTemperatureVsWeightIncompatibility() {

        Quantity<TemperatureUnit> temp =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        Quantity<WeightUnit> weight =
                new Quantity<>(50.0, WeightUnit.KILOGRAMS);

        assertFalse(temp.equals(weight));
    }

    // ------------------------------------------------------
    // Operation Support Methods
    // ------------------------------------------------------

    @Test
    void testOperationSupportMethods_TemperatureUnitAddition() {

        assertFalse(TemperatureUnit.CELSIUS.supportsArithmetic());
    }

    @Test
    void testOperationSupportMethods_LengthUnitAddition() {

        assertTrue(LengthUnit.FEET.supportsArithmetic());
    }

    // ------------------------------------------------------
    // Constructor Validation
    // ------------------------------------------------------

    @Test
    void testTemperatureNullUnitValidation() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(100.0, null)
        );
    }

    @Test
    void testTemperatureNullOperandValidation_InComparison() {

        Quantity<TemperatureUnit> temp =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertFalse(temp.equals(null));
    }

    // ------------------------------------------------------
    // Inequality
    // ------------------------------------------------------

    @Test
    void testTemperatureDifferentValuesInequality() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        assertFalse(t1.equals(t2));
    }
}
