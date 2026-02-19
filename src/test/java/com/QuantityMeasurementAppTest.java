package com;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.QuantityMeasurementApp.Feet;
import com.QuantityMeasurementApp.Inches;

public class QuantityMeasurementAppTest {

    @Test
    public void testFeetEquality_SameValue() {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);
        assertTrue(feet1.equals(feet2));
    }

    @Test
    public void testFeetEquality_DifferentValue() {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(2.0);
        assertFalse(feet1.equals(feet2));
    }

    @Test
    public void testFeetEquality_NullComparison() {
        Feet feet1 = new Feet(1.0);
        assertFalse(feet1.equals(null));
    }

    @Test
    public void testFeetEquality_DifferentClass() {
        Feet feet1 = new Feet(1.0);
        String nonFeetObject = "1.0";
        assertFalse(feet1.equals(nonFeetObject));
    }

    @Test
    public void testFeetEquality_SameReference() {
        Feet feet1 = new Feet(1.0);
        assertTrue(feet1.equals(feet1));
    }
    
    @Test
    public void testInchesEquality_SameValue() {
        Inches inches1 = new Inches(1.0);
        Inches inches2 = new Inches(1.0);
        assertTrue(inches1.equals(inches2));
    }

    @Test
    public void testInchesEquality_DifferentValue() {
        Inches inches1 = new Inches(1.0);
        Inches inches2 = new Inches(2.0);
        assertFalse(inches1.equals(inches2));
    }

    @Test
    public void testInchesEquality_NullComparison() {
        Inches inches1 = new Inches(1.0);
        assertFalse(inches1.equals(null));
    }

    @Test
    public void testInchesEquality_DifferentClass() {
        Inches inches1 = new Inches(1.0);
        String nonInchesObject = "1.0";
        assertFalse(inches1.equals(nonInchesObject));
    }

    @Test
    public void testInchesEquality_SameReference() {
        Inches inches1 = new Inches(1.0);
        assertTrue(inches1.equals(inches1));
    }
}
