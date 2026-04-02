package com;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class QuantityMeasurementAppTest {

	@Test
	public void testAddition_SameUnit_FeetPlusFeet() {
	    Length l1 = new Length(1.0, Length.LengthUnit.FEET);
	    Length l2 = new Length(2.0, Length.LengthUnit.FEET);

	    Length result = l1.add(l2);

	    Length expected = new Length(3.0, Length.LengthUnit.FEET);
	    assertTrue(result.equals(expected));
	}

	@Test
	public void testAddition_SameUnit_InchPlusInch() {
	    Length l1 = new Length(6.0, Length.LengthUnit.INCHES);
	    Length l2 = new Length(6.0, Length.LengthUnit.INCHES);

	    Length result = l1.add(l2);

	    Length expected = new Length(12.0, Length.LengthUnit.INCHES);
	    assertTrue(result.equals(expected));
	}

	@Test
	public void testAddition_CrossUnit_FeetPlusInches() {
	    Length feet = new Length(1.0, Length.LengthUnit.FEET);
	    Length inches = new Length(12.0, Length.LengthUnit.INCHES);

	    Length result = feet.add(inches);

	    Length expected = new Length(2.0, Length.LengthUnit.FEET);
	    assertTrue(result.equals(expected));
	}

	@Test
	public void testAddition_CrossUnit_InchPlusFeet() {
	    Length inches = new Length(12.0, Length.LengthUnit.INCHES);
	    Length feet = new Length(1.0, Length.LengthUnit.FEET);

	    Length result = inches.add(feet);

	    Length expected = new Length(24.0, Length.LengthUnit.INCHES);
	    assertTrue(result.equals(expected));
	}

	@Test
	public void testAddition_WithZero() {
	    Length feet = new Length(5.0, Length.LengthUnit.FEET);
	    Length zero = new Length(0.0, Length.LengthUnit.INCHES);

	    Length result = feet.add(zero);

	    assertTrue(result.equals(feet));
	}

	@Test
	public void testAddition_NegativeValues() {
	    Length l1 = new Length(5.0, Length.LengthUnit.FEET);
	    Length l2 = new Length(-2.0, Length.LengthUnit.FEET);

	    Length result = l1.add(l2);

	    Length expected = new Length(3.0, Length.LengthUnit.FEET);
	    assertTrue(result.equals(expected));
	}

	@Test
	public void testAddition_NullSecondOperand() {
	    Length l1 = new Length(1.0, Length.LengthUnit.FEET);

	    assertThrows(IllegalArgumentException.class, () -> {
	        l1.add(null);
	    });
	}

	@Test
	public void testAddition_LargeValues() {
	    Length l1 = new Length(1e6, Length.LengthUnit.FEET);
	    Length l2 = new Length(1e6, Length.LengthUnit.FEET);

	    Length result = l1.add(l2);

	    Length expected = new Length(2e6, Length.LengthUnit.FEET);
	    assertTrue(result.equals(expected));
	}

	@Test
	public void testAddition_SmallValues() {
	    Length l1 = new Length(0.001, Length.LengthUnit.FEET);
	    Length l2 = new Length(0.002, Length.LengthUnit.FEET);

	    Length result = l1.add(l2);

	    Length expected = new Length(0.003, Length.LengthUnit.FEET);
	    assertTrue(result.equals(expected));
	}

}
