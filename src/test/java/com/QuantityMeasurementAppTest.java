package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.core.Length;
import com.unit.LengthUnit;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.01;

   

    @Test
    public void testFeetEquality() {
        assertTrue(new Length(1.0, LengthUnit.FEET)
                .equals(new Length(1.0, LengthUnit.FEET)));
    }

    @Test
    public void testInchesEquality() {
        assertTrue(new Length(1.0, LengthUnit.INCHES)
                .equals(new Length(1.0, LengthUnit.INCHES)));
    }

    @Test
    public void testFeetInchesComparison() {
        assertTrue(new Length(1.0, LengthUnit.FEET)
                .equals(new Length(12.0, LengthUnit.INCHES)));
    }

    @Test
    public void testFeetInequality() {
        assertFalse(new Length(1.0, LengthUnit.FEET)
                .equals(new Length(2.0, LengthUnit.FEET)));
    }

    @Test
    public void testInchesInequality() {
        assertFalse(new Length(1.0, LengthUnit.INCHES)
                .equals(new Length(2.0, LengthUnit.INCHES)));
    }

    @Test
    public void testCrossUnitInequality() {
        assertFalse(new Length(1.0, LengthUnit.FEET)
                .equals(new Length(10.0, LengthUnit.INCHES)));
    }

    @Test
    public void testMultipleFeetComparison() {
        assertTrue(new Length(3.0, LengthUnit.FEET)
                .equals(new Length(36.0, LengthUnit.INCHES)));
    }

    @Test
    public void yardEquals36Inches() {
        assertTrue(new Length(1.0, LengthUnit.YARDS)
                .equals(new Length(36.0, LengthUnit.INCHES)));
    }

    @Test
    public void centimeterEqualsInches() {
        assertTrue(new Length(1.0, LengthUnit.CENTIMETERS)
                .equals(new Length(0.393701, LengthUnit.INCHES)));
    }

    @Test
    public void threeFeetEqualOneYard() {
        assertTrue(new Length(3.0, LengthUnit.FEET)
                .equals(new Length(1.0, LengthUnit.YARDS)));
    }

    // ---------------- Equality ----------------

    @Test
    public void referenceEqualitySameObject() {
        Length l = new Length(5.0, LengthUnit.FEET);
        assertTrue(l.equals(l));
    }

    @Test
    public void equalsReturnsFalseForNull() {
        assertFalse(new Length(5.0, LengthUnit.FEET)
                .equals(null));
    }

    @Test
    public void reflexiveSymmetricAndTransitiveProperty() {

        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);
        Length inches = new Length(36.0, LengthUnit.INCHES);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yard.equals(inches));
    }

    @Test
    public void differentValuesSameUnitNotEqual() {
        assertFalse(new Length(2.0, LengthUnit.FEET)
                .equals(new Length(3.0, LengthUnit.FEET)));
    }

    // UC5 CONVERSION TESTS 

    @Test
    public void convertFeetToInches() {
        Length result = QuantityMeasurementApp
                .demonstrateLengthConversion(
                        10.0,
                        LengthUnit.FEET,
                        LengthUnit.INCHES);

        assertEquals(120.0, result.getValue(), EPSILON);
    }

    @Test
    public void convertYardsToInchesUsingOverloadedMethod() {

        Length result = QuantityMeasurementApp
                .demonstrateLengthConversion(
                        new Length(2.0, LengthUnit.YARDS),
                        LengthUnit.INCHES);

        assertEquals(72.0, result.getValue(), EPSILON);
    }

    //UC6 ADDITION TESTS 

    @Test
    public void addFeetAndInches() {

        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);

        Length sumLength =
                QuantityMeasurementApp.demonstrateAddition(length1, length2);

        Length expectedLength =
                new Length(2.0, LengthUnit.FEET);

        assertTrue(QuantityMeasurementApp
                .demonstrateLengthEquality(sumLength, expectedLength));
    }

    @Test
    public void testAddition_SameUnit() {

        Length result = new Length(1.0, LengthUnit.FEET)
                .add(new Length(2.0, LengthUnit.FEET));

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_Commutativity() {

        Length a = new Length(1.0, LengthUnit.FEET);
        Length b = new Length(12.0, LengthUnit.INCHES);

        assertTrue(a.add(b).equals(b.add(a)));
    }

    @Test
    public void testAddition_WithZero() {

        Length result = new Length(5.0, LengthUnit.FEET)
                .add(new Length(0.0, LengthUnit.INCHES));

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_NegativeValues() {

        Length result = new Length(5.0, LengthUnit.FEET)
                .add(new Length(-2.0, LengthUnit.FEET));

        assertEquals(3.0, result.getValue(), EPSILON);
    }
    
    
    // UC7 TragetAddition
    @Test
    public void testAddition_ExplicitTargetUnit_Feet() {

        Length result = new Length(1.0,LengthUnit.FEET)
                .add(new Length(12.0,LengthUnit.INCHES),
                        LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }
    
    @Test
    public void testAddition_ExplicitTargetUnit_Inches() {

        Length result = new Length(1.0, LengthUnit.FEET)
                .add(new Length(12.0, LengthUnit.INCHES),
                        LengthUnit.INCHES);

        assertEquals(24.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }
    @Test
    public void testAddition_ExplicitTargetUnit_Yards() {

        Length result = new Length(1.0, LengthUnit.FEET)
                .add(new Length(12.0, LengthUnit.INCHES),
                        LengthUnit.YARDS);

        assertEquals(0.667, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }
    @Test
    public void testAddition_ExplicitTargetUnit_Centimeters() {

        Length result = new Length(1.0, LengthUnit.INCHES)
                .add(new Length(1.0, LengthUnit.INCHES),
                        LengthUnit.CENTIMETERS);

        assertEquals(5.08, result.getValue(), EPSILON);
        assertEquals(LengthUnit.CENTIMETERS, result.getUnit());
    }
    @Test
    public void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {

        Length result = new Length(2.0, LengthUnit.YARDS)
                .add(new Length(3.0, LengthUnit.FEET),
                        LengthUnit.YARDS);

        assertEquals(3.0, result.getValue(), EPSILON);
    }
    @Test
    public void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {

        Length result = new Length(2.0, LengthUnit.YARDS)
                .add(new Length(3.0, LengthUnit.FEET),
                        LengthUnit.FEET);

        assertEquals(9.0, result.getValue(), EPSILON);
    }
    @Test
    public void testAddition_ExplicitTargetUnit_Commutativity() {

        Length a = new Length(1.0, LengthUnit.FEET);
        Length b = new Length(12.0, LengthUnit.INCHES);

        Length r1 = a.add(b, LengthUnit.YARDS);
        Length r2 = b.add(a, LengthUnit.YARDS);

        assertEquals(r1.getValue(), r2.getValue(), EPSILON);
    }
    @Test
    public void testAddition_ExplicitTargetUnit_WithZero() {

        Length result = new Length(5.0, LengthUnit.FEET)
                .add(new Length(0.0, LengthUnit.INCHES),
                        LengthUnit.YARDS);

        assertEquals(1.667, result.getValue(), EPSILON);
    }
    @Test
    public void testAddition_ExplicitTargetUnit_NegativeValues() {

        Length result = new Length(5.0, LengthUnit.FEET)
                .add(new Length(-2.0, LengthUnit.FEET),
                        LengthUnit.INCHES);

        assertEquals(36.0, result.getValue(), EPSILON);
    }
    @Test
    public void testAddition_ExplicitTargetUnit_NullTargetUnit() {

        assertThrows(IllegalArgumentException.class,
                () -> new Length(1.0, LengthUnit.FEET)
                        .add(new Length(12.0,LengthUnit.INCHES),null));
                                
    }
    @Test
    public void testAddition_ExplicitTargetUnit_LargeToSmallScale() {

        Length result = new Length(1000.0, LengthUnit.FEET)
                .add(new Length(500.0, LengthUnit.FEET),
                        LengthUnit.INCHES);

        assertEquals(18000.0, result.getValue(), EPSILON);
    }
    @Test
    public void testAddition_ExplicitTargetUnit_SmallToLargeScale() {

        Length result = new Length(12.0, LengthUnit.INCHES)
                .add(new Length(12.0, LengthUnit.INCHES),
                        LengthUnit.YARDS);

        assertEquals(0.667, result.getValue(), EPSILON);
    }
    @Test
    public void testAddition_ExplicitTargetUnit_AllUnitCombinations() {

        Length[] lengths = {
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                new Length(1.0, LengthUnit.YARDS),
                new Length(30.48, LengthUnit.CENTIMETERS)
        };
        for (Length a : lengths) {
            for (Length b : lengths) {
                for (LengthUnit target : LengthUnit.values()) {

                    Length result = a.add(b, target);

                    double expectedBase =
                            a.add(b).add(new Length(0, target)).getValue();

                    assertNotNull(result);
                    assertEquals(target, result.getUnit());
                }
            }
        }
    }
    
    @Test
    public void testAddition_ExplicitTargetUnit_PrecisionTolerance() {

        Length result1 = new Length(1.0, LengthUnit.FEET)
                .add(new Length(12.0, LengthUnit.INCHES),
                        LengthUnit.YARDS);

        Length result2 = new Length(24.0, LengthUnit.INCHES)
                .add(new Length(0.0, LengthUnit.INCHES),
                        LengthUnit.YARDS);

        assertEquals(result1.getValue(), result2.getValue(), EPSILON);
    }
    
    

    

}
