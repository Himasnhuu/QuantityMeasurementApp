package com;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class QuantityMeasurementAppTest {

	@Test
	public void testAddition_ExplicitTargetUnit_Feet() {

	    Length l1 = new Length(1.0, Length.LengthUnit.FEET);
	    Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

	    Length result = l1.add(l2, Length.LengthUnit.FEET);

	    Length expected = new Length(2.0, Length.LengthUnit.FEET);

	    assertTrue(result.equals(expected));
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_Inches() {

	    Length l1 = new Length(1.0, Length.LengthUnit.FEET);
	    Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

	    Length result = l1.add(l2, Length.LengthUnit.INCHES);

	    Length expected = new Length(24.0, Length.LengthUnit.INCHES);

	    assertTrue(result.equals(expected));
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_Yards() {

	    Length l1 = new Length(1.0, Length.LengthUnit.FEET);
	    Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

	    Length result = l1.add(l2, Length.LengthUnit.YARDS);

	    Length expected = new Length(0.667, Length.LengthUnit.YARDS);

	    assertTrue(result.equals(expected));
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_Centimeters() {

	    Length l1 = new Length(1.0, Length.LengthUnit.INCHES);
	    Length l2 = new Length(1.0, Length.LengthUnit.INCHES);

	    Length result = l1.add(l2, Length.LengthUnit.CENTIMETERS);

	    Length expected = new Length(5.08, Length.LengthUnit.CENTIMETERS);

	    assertTrue(result.equals(expected));
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_Commutativity() {

	    Length l1 = new Length(1.0, Length.LengthUnit.FEET);
	    Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

	    Length r1 = l1.add(l2, Length.LengthUnit.YARDS);
	    Length r2 = l2.add(l1, Length.LengthUnit.YARDS);

	    assertTrue(r1.equals(r2));
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_NullTargetUnit() {

	    Length l1 = new Length(1.0, Length.LengthUnit.FEET);
	    Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

	    assertThrows(IllegalArgumentException.class, () -> {
	        l1.add(l2, null);
	    });
	}
}
