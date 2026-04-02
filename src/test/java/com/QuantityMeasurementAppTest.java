package com;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testLengthEquals_SameObject_ShouldReturnTrue() {
        Length length = new Length(1.0, Length.LengthUnit.FEET);
        assertTrue(length.equals(length));
    }
    
    @Test
    public void testLengthEquals_WithNull_ShouldReturnFalse() {
        Length length = new Length(1.0, Length.LengthUnit.FEET);
        assertFalse(length.equals(null));
    }
    
    @Test
    public void testLengthEquals_SameValueAndUnit_ShouldReturnTrue() {
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(1.0, Length.LengthUnit.FEET);
        assertTrue(length1.equals(length2));
    }
    
    @Test
    public void testLengthEquals_DifferentValuesSameUnit_ShouldReturnFalse() {
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(2.0, Length.LengthUnit.FEET);
        assertFalse(length1.equals(length2));
    }
    
    @Test
    public void testLengthEquals_OneFeetEquals12Inches_ShouldReturnTrue() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        Length inches = new Length(12.0, Length.LengthUnit.INCHES);
        assertTrue(feet.equals(inches));
    }
    
    @Test
    public void testLengthEquals_OneFeetNotEquals10Inches_ShouldReturnFalse() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        Length inches = new Length(10.0, Length.LengthUnit.INCHES);
        assertFalse(feet.equals(inches));
    }
    
    @Test
    public void testLengthCompare_EquivalentLengths_ShouldReturnTrue() {
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0, Length.LengthUnit.INCHES);
        assertTrue(length1.compare(length2));
    }
    
    @Test
    public void testDemonstrateLengthEquality_EqualLengths_ShouldReturnTrue() {
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0, Length.LengthUnit.INCHES);
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(length1, length2));
    }
    
    @Test
    public void testDemonstrateLengthEquality_DifferentLengths_ShouldReturnFalse() {
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(6.0, Length.LengthUnit.INCHES);
        assertFalse(QuantityMeasurementApp.demonstrateLengthEquality(length1, length2));
    }
    
    @Test
    public void testLength_DecimalValues_ShouldWork() {
        Length length1 = new Length(0.5, Length.LengthUnit.FEET);
        Length length2 = new Length(6.0, Length.LengthUnit.INCHES);
        assertTrue(length1.equals(length2));
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
}
