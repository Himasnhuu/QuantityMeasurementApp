package com.app.quantityservice.unit;

import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class UnitRegistry {

    // List of all enum classes that implement IMeasurable
    private final List<Class<? extends Enum<?>>> unitCategories = Arrays.asList(
            LengthUnit.class,
            VolumeUnit.class,
            WeightUnit.class,
            TemperatureUnit.class
    );

    /**
     * Finds the correct unit constant across all measurement categories.
     */
    public IMeasurable getUnit(String unitName) {

        if (unitName == null || unitName.isBlank()) {
            throw new IllegalArgumentException("Unit name cannot be empty");
        }

        String searchName = unitName.toUpperCase().trim();

        for (Class<? extends Enum<?>> category : unitCategories) {
            try {
                // ✅ FIX: use Enum.valueOf with proper generics
                Enum<?> enumConstant = Enum.valueOf((Class) category, searchName);
                return (IMeasurable) enumConstant;

            } catch (IllegalArgumentException ignored) {
                // try next category
            }
        }

        throw new IllegalArgumentException(
                "Invalid unit: '" + unitName +
                "'. Ensure the unit exists in Length, Volume, Weight, or Temperature categories."
        );
    }
}