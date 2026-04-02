package com;

import com.controller.QuantityMeasurementController;
import com.dto.QuantityDTO;
import com.exception.QuantityMeasurementException;
import com.repository.IQuantityMeasurementRepository;
import com.repository.QuantityMeasurementCacheRepository;
import com.repository.QuantityMeasurementDatabaseRepository;
import com.service.IQuantityMeasurementService;
import com.service.QuantityMeasurementServiceImpl;
import com.util.ApplicationConfig;

/**
 * UC15 Entry Point — wire layers, delegate everything to controller.
 *
 */
public class QuantityMeasurementApp {

    public static void main(String[] args) {
        // ── Wire up N-Tier layers (Factory Pattern) 
        ApplicationConfig config = ApplicationConfig.getInstance();
        IQuantityMeasurementRepository repository;
        if (config.isDatabaseRepository()) {
            repository = QuantityMeasurementDatabaseRepository.getInstance();
        } else {
            repository = QuantityMeasurementCacheRepository.getInstance();
        }

        IQuantityMeasurementService service
                = new QuantityMeasurementServiceImpl(repository);

        QuantityMeasurementController controller
                = new QuantityMeasurementController(service);

        System.out.println(" N-Tier Quantity Measurement App ===\n");

        // ── UC1–UC5: Length Equality & Conversion 
        System.out.println("--- LENGTH COMPARISON ---");
        controller.performComparison(
                new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET),
                new QuantityDTO(12.0, QuantityDTO.LengthUnit.INCHES));
        controller.performComparison(
                new QuantityDTO(1.0, QuantityDTO.LengthUnit.YARDS),
                new QuantityDTO(3.0, QuantityDTO.LengthUnit.FEET));

        System.out.println("\n--- LENGTH CONVERSION ---");
        controller.performConversion(
                new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET),
                new QuantityDTO(0.0, QuantityDTO.LengthUnit.INCHES));
        controller.performConversion(
                new QuantityDTO(1.0, QuantityDTO.LengthUnit.YARDS),
                new QuantityDTO(0.0, QuantityDTO.LengthUnit.FEET));

        // ── UC6–UC7: Length Addition 
        System.out.println("\n--- LENGTH ADDITION ---");
        controller.performAddition(
                new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET),
                new QuantityDTO(12.0, QuantityDTO.LengthUnit.INCHES));
        controller.performAddition(
                new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET),
                new QuantityDTO(12.0, QuantityDTO.LengthUnit.INCHES),
                new QuantityDTO(0.0, QuantityDTO.LengthUnit.YARDS));  // target = YARDS

        // ── UC9–UC10: Weight 
        System.out.println("\n--- WEIGHT OPERATIONS ---");
        controller.performComparison(
                new QuantityDTO(1.0, QuantityDTO.WeightUnit.KILOGRAMS),
                new QuantityDTO(1000.0, QuantityDTO.WeightUnit.GRAMS));
        controller.performAddition(
                new QuantityDTO(1.0, QuantityDTO.WeightUnit.KILOGRAMS),
                new QuantityDTO(1000.0, QuantityDTO.WeightUnit.GRAMS));
        controller.performConversion(
                new QuantityDTO(1.0, QuantityDTO.WeightUnit.KILOGRAMS),
                new QuantityDTO(0.0, QuantityDTO.WeightUnit.GRAMS));

        // ── UC11: Volume 
        System.out.println("\n--- VOLUME OPERATIONS ---");
        controller.performComparison(
                new QuantityDTO(1.0, QuantityDTO.VolumeUnit.LITRE),
                new QuantityDTO(1000.0, QuantityDTO.VolumeUnit.MILLILITRE));
        controller.performAddition(
                new QuantityDTO(1.0, QuantityDTO.VolumeUnit.LITRE),
                new QuantityDTO(2.0, QuantityDTO.VolumeUnit.LITRE));
        controller.performConversion(
                new QuantityDTO(1.0, QuantityDTO.VolumeUnit.GALLON),
                new QuantityDTO(0.0, QuantityDTO.VolumeUnit.LITRE));

        // ── UC12: Subtraction & Division 
        System.out.println("\n--- SUBTRACTION ---");
        controller.performSubtraction(
                new QuantityDTO(10.0, QuantityDTO.LengthUnit.FEET),
                new QuantityDTO(6.0, QuantityDTO.LengthUnit.INCHES));
        controller.performSubtraction(
                new QuantityDTO(10.0, QuantityDTO.WeightUnit.KILOGRAMS),
                new QuantityDTO(5000.0, QuantityDTO.WeightUnit.GRAMS),
                new QuantityDTO(0.0, QuantityDTO.WeightUnit.GRAMS));

        System.out.println("\n--- DIVISION ---");
        controller.performDivision(
                new QuantityDTO(10.0, QuantityDTO.LengthUnit.FEET),
                new QuantityDTO(2.0, QuantityDTO.LengthUnit.FEET));
        controller.performDivision(
                new QuantityDTO(2000.0, QuantityDTO.WeightUnit.GRAMS),
                new QuantityDTO(1.0, QuantityDTO.WeightUnit.KILOGRAMS));

        // ── UC14 Temperature 
        System.out.println("\n TEMPERATURE");
        controller.performComparison(
                new QuantityDTO(0.0, QuantityDTO.TemperatureUnit.CELSIUS),
                new QuantityDTO(32.0, QuantityDTO.TemperatureUnit.FAHRENHEIT));
        controller.performConversion(
                new QuantityDTO(100.0, QuantityDTO.TemperatureUnit.CELSIUS),
                new QuantityDTO(0.0, QuantityDTO.TemperatureUnit.FAHRENHEIT));

        try {
            controller.performAddition(
                    new QuantityDTO(10.0, QuantityDTO.TemperatureUnit.CELSIUS),
                    new QuantityDTO(20.0, QuantityDTO.TemperatureUnit.CELSIUS));
        } catch (QuantityMeasurementException e) {
            System.out.println("Expected error → " + e.getMessage());
        }

        // ── Operation History 
        System.out.println("\n--- OPERATION HISTORY ---");
        repository.getAllMeasurements().forEach(System.out::println);
    }
}
