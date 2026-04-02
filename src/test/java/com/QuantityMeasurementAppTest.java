package com;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

	private static final double EPSILON = 0.0001;

	@Test
	public void testEquality_LitreToLitre_SameValue() {
		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(1.0, VolumeUnit.LITRE);

		assertTrue(v1.equals(v2));
	}

	@Test
	public void testEquality_LitreToLitre_DifferentValue() {
		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(2.0, VolumeUnit.LITRE);

		assertFalse(v1.equals(v2));
	}

	@Test
	public void testEquality_LitreToMillilitre_EquivalentValue() {
		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

		assertTrue(v1.equals(v2));
	}

	@Test
	public void testEquality_MillilitreToLitre_EquivalentValue() {
		Quantity<VolumeUnit> v1 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(1.0, VolumeUnit.LITRE);

		assertTrue(v1.equals(v2));
	}

	@Test
	public void testEquality_LitreToGallon_EquivalentValue() {
		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(0.264172, VolumeUnit.GALLON);

		assertTrue(v1.equals(v2));
	}

	@Test
	public void testEquality_GallonToLitre_EquivalentValue() {
		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.GALLON);
		Quantity<VolumeUnit> v2 = new Quantity<>(3.78541, VolumeUnit.LITRE);

		assertTrue(v1.equals(v2));
	}

	// -----------------------------
	// Category Incompatibility
	// -----------------------------

	@Test
	public void testEquality_VolumeVsLength_Incompatible() {
		Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);

		assertFalse(volume.equals(length));
	}

	@Test
	public void testEquality_VolumeVsWeight_Incompatible() {
		Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		assertFalse(volume.equals(weight));
	}

	// -----------------------------
	// Conversion Tests
	// -----------------------------

	@Test
	public void testConversion_LitreToMillilitre() {
		Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.MILLILITRE);

		assertEquals(1000.0, result.getValue(), EPSILON);
	}

	@Test
	public void testConversion_MillilitreToLitre() {
		Quantity<VolumeUnit> v = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
		Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.LITRE);

		assertEquals(1.0, result.getValue(), EPSILON);
	}

	@Test
	public void testConversion_LitreToGallon() {
		Quantity<VolumeUnit> v = new Quantity<>(3.78541, VolumeUnit.LITRE);
		Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.GALLON);

		assertEquals(1.0, result.getValue(), EPSILON);
	}

	@Test
	public void testConversion_RoundTrip() {
		Quantity<VolumeUnit> v = new Quantity<>(1.5, VolumeUnit.LITRE);

		Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.MILLILITRE).convertTo(VolumeUnit.LITRE);

		assertEquals(1.5, result.getValue(), EPSILON);
	}

	// -----------------------------
	// Addition Tests
	// -----------------------------

	@Test
	public void testAddition_SameUnit_LitrePlusLitre() {

		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(2.0, VolumeUnit.LITRE);

		Quantity<VolumeUnit> result = v1.add(v2);

		assertEquals(3.0, result.getValue(), EPSILON);
	}

	@Test
	public void testAddition_CrossUnit_LitrePlusMillilitre() {

		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

		Quantity<VolumeUnit> result = v1.add(v2);

		assertEquals(2.0, result.getValue(), EPSILON);
	}

	@Test
	public void testAddition_ExplicitTargetUnit_Millilitre() {

		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

		Quantity<VolumeUnit> result = v1.add(v2, VolumeUnit.MILLILITRE);

		assertEquals(2000.0, result.getValue(), EPSILON);
	}

	@Test
	public void testAddition_ExplicitTargetUnit_Gallon() {

		Quantity<VolumeUnit> v1 = new Quantity<>(3.78541, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(3.78541, VolumeUnit.LITRE);

		Quantity<VolumeUnit> result = v1.add(v2, VolumeUnit.GALLON);

		assertEquals(2.0, result.getValue(), EPSILON);
	}

	// -----------------------------
	// VolumeUnit Enum Tests
	// -----------------------------

	@Test
	public void testVolumeUnitEnum_LitreConstant() {
		assertEquals(1.0, VolumeUnit.LITRE.getConversionFactor(), EPSILON);
	}

	@Test
	public void testVolumeUnitEnum_MillilitreConstant() {
		assertEquals(0.001, VolumeUnit.MILLILITRE.getConversionFactor(), EPSILON);
	}

	@Test
	public void testVolumeUnitEnum_GallonConstant() {
		assertEquals(3.78541, VolumeUnit.GALLON.getConversionFactor(), EPSILON);
	}

	// -----------------------------
	// Base Unit Conversion Tests
	// -----------------------------

	@Test
	public void testConvertToBaseUnit_MillilitreToLitre() {
		double result = VolumeUnit.MILLILITRE.convertToBaseUnit(1000.0);

		assertEquals(1.0, result, EPSILON);
	}

	@Test
	public void testConvertToBaseUnit_GallonToLitre() {
		double result = VolumeUnit.GALLON.convertToBaseUnit(1.0);

		assertEquals(3.78541, result, EPSILON);
	}

	@Test
	public void testConvertFromBaseUnit_LitreToMillilitre() {
		double result = VolumeUnit.MILLILITRE.convertFromBaseUnit(1.0);

		assertEquals(1000.0, result, EPSILON);
	}

	@Test
	public void testConvertFromBaseUnit_LitreToGallon() {
		double result = VolumeUnit.GALLON.convertFromBaseUnit(3.78541);

		assertEquals(1.0, result, EPSILON);
	}
	
	@Test
    void testSubtraction_SameUnit_FeetMinusFeet() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(new Quantity<>(5.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_SameUnit_LitreMinusLitre() {
        Quantity<VolumeUnit> q1 = new Quantity<>(10.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(3.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result = q1.subtract(q2);

        assertEquals(new Quantity<>(7.0, VolumeUnit.LITRE), result);
    }

    @Test
    void testSubtraction_CrossUnit_FeetMinusInches() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(new Quantity<>(9.5, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_CrossUnit_InchesMinusFeet() {
        Quantity<LengthUnit> q1 = new Quantity<>(120.0, LengthUnit.INCHES);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(new Quantity<>(60.0, LengthUnit.INCHES), result);
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Inches() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.subtract(q2, LengthUnit.INCHES);

        assertEquals(new Quantity<>(114.0, LengthUnit.INCHES), result);
    }

    @Test
    void testSubtraction_ResultingInNegative() {
        Quantity<LengthUnit> q1 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(new Quantity<>(-5.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_ResultingInZero() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(120.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(new Quantity<>(0.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_WithZeroOperand() {
        Quantity<LengthUnit> q1 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(0.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(new Quantity<>(5.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_WithNegativeValues() {
        Quantity<LengthUnit> q1 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(-2.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(new Quantity<>(7.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_NullOperand() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> q1.subtract(null));
    }

    @Test
    void testSubtraction_NullTargetUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> q1.subtract(q2, null));
    }

    @Test
    void testSubtraction_ChainedOperations() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(2.0, LengthUnit.FEET))
                        .subtract(new Quantity<>(1.0, LengthUnit.FEET));

        assertEquals(new Quantity<>(7.0, LengthUnit.FEET), result);
    }

    // ---------------- DIVISION TESTS ----------------

    @Test
    void testDivision_SameUnit_FeetDividedByFeet() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);

        assertEquals(5.0, q1.divide(q2));
    }

    @Test
    void testDivision_SameUnit_LitreDividedByLitre() {
        Quantity<VolumeUnit> q1 = new Quantity<>(10.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(5.0, VolumeUnit.LITRE);

        assertEquals(2.0, q1.divide(q2));
    }

    @Test
    void testDivision_CrossUnit_FeetDividedByInches() {
        Quantity<LengthUnit> q1 = new Quantity<>(24.0, LengthUnit.INCHES);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);

        assertEquals(1.0, q1.divide(q2));
    }

    @Test
    void testDivision_CrossUnit_KilogramDividedByGram() {
        Quantity<WeightUnit> q1 = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(2000.0, WeightUnit.GRAM);

        assertEquals(1.0, q1.divide(q2));
    }

    @Test
    void testDivision_RatioLessThanOne() {
        Quantity<LengthUnit> q1 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(10.0, LengthUnit.FEET);

        assertEquals(0.5, q1.divide(q2));
    }

    @Test
    void testDivision_ByZero() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(0.0, LengthUnit.FEET);

        assertThrows(ArithmeticException.class, () -> q1.divide(q2));
    }

    @Test
    void testDivision_NullOperand() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> q1.divide(null));
    }

    @Test
    void testDivision_WithLargeRatio() {
        Quantity<WeightUnit> q1 = new Quantity<>(1e6, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertEquals(1e6, q1.divide(q2));
    }

    @Test
    void testDivision_WithSmallRatio() {
        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1e6, WeightUnit.KILOGRAM);

        assertEquals(1e-6, q1.divide(q2));
    }
}
