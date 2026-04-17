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
     * @param unitName The string name of the unit (e.g., "LITRE", "INCHES")
     * @return The IMeasurable unit instance
     * @throws IllegalArgumentException if the unit is not found in any category
     */
    public IMeasurable getUnit(String unitName) {
        if (unitName == null || unitName.isBlank()) {
            throw new IllegalArgumentException("Unit name cannot be empty");
        }

        String searchName = unitName.toUpperCase().trim();

        for (Class<? extends Enum<?>> category : unitCategories) {
            try {
                // Java Enums have a built-in valueOf method
                return (IMeasurable) Enum.valueOf((Class<Enum>) category, searchName);
            } catch (IllegalArgumentException ignored) {
                // Move to the next category if not found in this one
            }
        }

        throw new IllegalArgumentException("Invalid unit: '" + unitName + 
                "'. Ensure the unit exists in Length, Volume, Weight, or Temperature categories.");
    }
}