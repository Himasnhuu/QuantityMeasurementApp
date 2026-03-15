package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.core.Quantity;
import com.unit.LengthUnit;
import com.unit.WeightUnit;

public class QuantitySubtractionAndDivisionTest {

    private static final double EPSILON = 1e-5;

    

    @Test
    void testSubtraction_SameUnit_FeetMinusFeet() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(5.0, LengthUnit.FEET));

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void testSubtraction_CrossUnit_FeetMinusInches() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(6.0, LengthUnit.INCHES));

        assertEquals(9.5, result.getValue(), EPSILON);
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Inches() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(6.0, LengthUnit.INCHES),
                                LengthUnit.INCHES);

        assertEquals(114.0, result.getValue(), EPSILON);
    }

    @Test
    void testSubtraction_ResultingInNegative() {
        Quantity<LengthUnit> result =
                new Quantity<>(5.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(10.0, LengthUnit.FEET));

        assertEquals(-5.0, result.getValue(), EPSILON);
    }

    @Test
    void testSubtraction_ResultingInZero() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(120.0, LengthUnit.INCHES));

        assertEquals(0.0, result.getValue(), EPSILON);
    }

    @Test
    void testSubtraction_NonCommutative() {
        Quantity<LengthUnit> a =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(5.0, LengthUnit.FEET);

        assertNotEquals(a.subtract(b), b.subtract(a));
    }

    @Test
    void testSubtraction_NullOperand() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(10.0, LengthUnit.FEET).subtract(null));
    }

    @Test
    void testSubtraction_CrossCategory() {
        Quantity length = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity weight = new Quantity<>(5.0, WeightUnit.KILOGRAMS);

        assertThrows(IllegalArgumentException.class,
                () -> length.subtract(weight));
    }

    

    @Test
    void testDivision_SameUnit() {
        double result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(2.0, LengthUnit.FEET));

        assertEquals(5.0, result, EPSILON);
    }

    @Test
    void testDivision_CrossUnit() {
        double result =
                new Quantity<>(24.0, LengthUnit.INCHES)
                        .divide(new Quantity<>(2.0, LengthUnit.FEET));

        assertEquals(1.0, result, EPSILON);
    }

    @Test
    void testDivision_RatioGreaterThanOne() {
        double result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(5.0, LengthUnit.FEET));

        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testDivision_RatioLessThanOne() {
        double result =
                new Quantity<>(5.0, LengthUnit.FEET)
                        .divide(new Quantity<>(10.0, LengthUnit.FEET));

        assertEquals(0.5, result, EPSILON);
    }

    @Test
    void testDivision_NonCommutative() {
        Quantity<LengthUnit> a =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(5.0, LengthUnit.FEET);

        assertNotEquals(a.divide(b), b.divide(a));
    }

    @Test
    void testDivision_ByZero() {
        assertThrows(ArithmeticException.class,
                () -> new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(0.0, LengthUnit.FEET)));
    }

    @Test
    void testDivision_NullOperand() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(10.0, LengthUnit.FEET).divide(null));
    }

    @Test
    void testDivision_CrossCategory() {
        Quantity length = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity weight = new Quantity<>(5.0, WeightUnit.KILOGRAMS);

        assertThrows(IllegalArgumentException.class,
                () -> length.divide(weight));
    }

    

    @Test
    void testSubtraction_Immutability() {
        Quantity<LengthUnit> original =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                original.subtract(new Quantity<>(5.0, LengthUnit.FEET));

        assertNotSame(original, result);
        assertEquals(10.0, original.getValue(), EPSILON);
    }

    @Test
    void testDivision_Immutability() {
        Quantity<LengthUnit> original =
                new Quantity<>(10.0, LengthUnit.FEET);

        double result =
                original.divide(new Quantity<>(2.0, LengthUnit.FEET));

        assertEquals(5.0, result, EPSILON);
        assertEquals(10.0, original.getValue(), EPSILON);
    }

    

    @Test
    void testSubtractionAddition_Inverse() {
        Quantity<LengthUnit> a =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                a.add(b).subtract(b);

        assertEquals(a.getValue(), result.getValue(), EPSILON);
    }
}