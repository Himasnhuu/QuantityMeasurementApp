package com;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.core.Quantity;
import com.unit.LengthUnit;
import com.unit.VolumeUnit;
import com.unit.WeightUnit;

class VolumeUnitTest {

    private static final double EPSILON = 1e-5;

    // =========================================================
    // EQUALITY TESTS
    // =========================================================

    @Test
    void testEquality_LitreToLitre_SameValue() {
        assertTrue(
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .equals(new Quantity<>(1.0, VolumeUnit.LITRE))
        );
    }

    @Test
    void testEquality_LitreToLitre_DifferentValue() {
        assertFalse(
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .equals(new Quantity<>(2.0, VolumeUnit.LITRE))
        );
    }

    @Test
    void testEquality_LitreToMillilitre_EquivalentValue() {
        assertTrue(
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .equals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE))
        );
    }

    @Test
    void testEquality_LitreToGallon_EquivalentValue() {
        assertTrue(
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .equals(new Quantity<>(0.264172, VolumeUnit.GALLON))
        );
    }

    @Test
    void testEquality_VolumeVsLength_Incompatible() {
        assertFalse(
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .equals(new Quantity<>(1.0, LengthUnit.FEET))
        );
    }

    @Test
    void testEquality_VolumeVsWeight_Incompatible() {
        assertFalse(
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .equals(new Quantity<>(1.0, WeightUnit.KILOGRAMS))
        );
    }

    @Test
    void testEquality_SameReference() {
        Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertTrue(v.equals(v));
    }

    @Test
    void testEquality_ZeroValue() {
        assertTrue(
                new Quantity<>(0.0, VolumeUnit.LITRE)
                        .equals(new Quantity<>(0.0, VolumeUnit.MILLILITRE))
        );
    }

    @Test
    void testEquality_NegativeVolume() {
        assertTrue(
                new Quantity<>(-1.0, VolumeUnit.LITRE)
                        .equals(new Quantity<>(-1000.0, VolumeUnit.MILLILITRE))
        );
    }

    // =========================================================
    // CONVERSION TESTS
    // =========================================================

    @Test
    void testConversion_LitreToMillilitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.MILLILITRE);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_GallonToLitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.GALLON)
                        .convertTo(VolumeUnit.LITRE);

        assertEquals(3.78541, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_RoundTrip() {
        Quantity<VolumeUnit> original =
                new Quantity<>(1.5, VolumeUnit.LITRE);

        Quantity<VolumeUnit> roundTrip =
                original.convertTo(VolumeUnit.MILLILITRE)
                        .convertTo(VolumeUnit.LITRE);

        assertEquals(original.getValue(), roundTrip.getValue(), EPSILON);
    }

    // =========================================================
    // ADDITION TESTS
    // =========================================================

    @Test
    void testAddition_SameUnit_LitrePlusLitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(2.0, VolumeUnit.LITRE));

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_CrossUnit_LitrePlusMillilitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE));

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Millilitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
                                VolumeUnit.MILLILITRE);

        assertEquals(2000.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_Commutativity() {
        Quantity<VolumeUnit> a =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> b =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertEquals(
                a.add(b).convertTo(VolumeUnit.LITRE).getValue(),
                b.add(a).convertTo(VolumeUnit.LITRE).getValue(),
                EPSILON
        );
    }

    @Test
    void testAddition_WithZero() {
        Quantity<VolumeUnit> result =
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(0.0, VolumeUnit.MILLILITRE));

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_NegativeValues() {
        Quantity<VolumeUnit> result =
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(-2000.0, VolumeUnit.MILLILITRE));

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    // =========================================================
    // ENUM VALIDATION TESTS
    // =========================================================

    @Test
    void testVolumeUnitEnum_LitreConstant() {
        assertEquals(1.0,
                VolumeUnit.LITRE.getConversionFactor(),
                EPSILON);
    }

    @Test
    void testConvertToBaseUnit_MillilitreToLitre() {
        assertEquals(1.0,
                VolumeUnit.MILLILITRE.convertToBaseUnit(1000.0),
                EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_LitreToGallon() {
        assertEquals(1.0,
                VolumeUnit.GALLON.convertFromBaseUnit(3.78541),
                EPSILON);
    }

    // =========================================================
    // GENERIC ARCHITECTURE TEST
    // =========================================================

    @Test
    void testGenericQuantity_VolumeOperations_Consistency() {
        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> ml =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertTrue(litre.equals(ml));
        assertEquals(2.0,
                litre.add(ml).getValue(),
                EPSILON);
    }

    @Test
    void testHashCode_Consistency() {
        Quantity<VolumeUnit> a =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> b =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertEquals(a.hashCode(), b.hashCode());
    }
}
