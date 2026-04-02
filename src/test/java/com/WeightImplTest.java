package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.core.Length;
import com.core.Weight;
import com.unit.LengthUnit;
import com.unit.WeightUnit;

public class WeightImplTest {
    //Weight feature testing 
    
	private static final double EPSILON = 1e-5;//Rounding of the values 
    //Weight equality test 
    @Test
    public void testWeightEquality_SameUnit() {
        assertTrue(new Weight(1.0, WeightUnit.KILOGRAMS)
                .equals(new Weight(1.0, WeightUnit.KILOGRAMS)));
    }

    @Test
    public void testWeightEquality_KgToGram() {
        assertTrue(new Weight(1.0, WeightUnit.KILOGRAMS)
                .equals(new Weight(1000.0, WeightUnit.GRAMS)));
    }

    @Test
    public void testWeightEquality_KgToPound() {
        assertTrue(new Weight(1.0, WeightUnit.KILOGRAMS)
                .equals(new Weight(2.20462, WeightUnit.POUNDS)));
    }

    @Test
    public void testWeightEquality_GramToKg() {
        assertTrue(new Weight(500.0, WeightUnit.GRAMS)
                .equals(new Weight(0.5, WeightUnit.KILOGRAMS)));
    }
    //Weight inequality test
    
    @Test
    public void testWeightInequality_DifferentValues() {
        assertFalse(new Weight(1.0, WeightUnit.KILOGRAMS)
                .equals(new Weight(2.0, WeightUnit.KILOGRAMS)));
    }
    //Compatability
    @Test
    public void testWeightAndLengthAreNotEqual() {
        assertFalse(new Weight(1.0, WeightUnit.KILOGRAMS)
                .equals(new Length(1.0, LengthUnit.FEET)));
    }
    //conversion
    @Test
    public void testWeightConvert_KgToGram() {
        Weight result = new Weight(1.0, WeightUnit.KILOGRAMS)
                .convertTo(WeightUnit.GRAMS);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    public void testWeightConvert_PoundToKg() {
        Weight result = new Weight(2.0, WeightUnit.POUNDS)
                .convertTo(WeightUnit.KILOGRAMS);

        assertEquals(0.907184, result.getValue(), EPSILON);
    }

    @Test
    public void testWeightConvert_GramToPound() {
        Weight result = new Weight(500.0, WeightUnit.GRAMS)
                .convertTo(WeightUnit.POUNDS);

        assertEquals(1.10231, result.getValue(), EPSILON);
    }
    //
    @Test
    public void testWeightAddition_SameUnit() {
        Weight result = new Weight(1.0, WeightUnit.KILOGRAMS)
                .add(new Weight(2.0, WeightUnit.KILOGRAMS));

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    public void testWeightAddition_KgPlusGram() {
        Weight result = new Weight(1.0, WeightUnit.KILOGRAMS)
                .add(new Weight(1000.0, WeightUnit.GRAMS));

        assertEquals(2.0, result.getValue(), EPSILON);
    }
    //Null
    @Test
    public void testWeightAddition_NullOperand() {
        assertThrows(IllegalArgumentException.class,
                () -> new Weight(1.0, WeightUnit.KILOGRAMS)
                        .add(null));
    }

    @Test
    public void testWeightAddition_NullTargetUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Weight(1.0, WeightUnit.KILOGRAMS)
                        .add(new Weight(1.0, WeightUnit.GRAMS), null));
    }
}
