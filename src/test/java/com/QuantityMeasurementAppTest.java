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
}
