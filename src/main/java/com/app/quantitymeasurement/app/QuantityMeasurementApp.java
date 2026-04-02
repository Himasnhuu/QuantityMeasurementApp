package com.app.quantitymeasurement.app;

import com.app.quantitymeasurement.controller.QuantityMeasurementController;
import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.app.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.app.quantitymeasurement.service.QuantityMeasurementService;
import com.app.quantitymeasurement.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        IQuantityMeasurementRepository repository = 
            QuantityMeasurementCacheRepository.getInstance();

        QuantityMeasurementService service = 
            new QuantityMeasurementServiceImpl(repository);

        QuantityMeasurementController controller = 
            new QuantityMeasurementController(service);

        System.out.println("Quantity Measurement Application Started");
        System.out.println("=========================================\n");

        // Demonstrate Length operations
        demonstrateLengthOperations(controller);

        // Demonstrate Weight operations
        demonstrateWeightOperations(controller);

        // Demonstrate Volume operations
        demonstrateVolumeOperations(controller);

        // Demonstrate Temperature operations
        demonstrateTemperatureOperations(controller);

        System.out.println("\n=========================================");
        System.out.println("Application completed successfully");
    }

    private static void demonstrateLengthOperations(QuantityMeasurementController controller) {
        System.out.println("--- LENGTH OPERATIONS ---");

        QuantityDTO length1 = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO length2 = new QuantityDTO(12.0, "INCHES", "LENGTH");

        boolean isEqual = controller.compare(length1, length2);
        System.out.println("1 FEET equals 12 INCHES: " + isEqual);

        QuantityDTO converted = controller.convert(length1, "INCHES");
        System.out.println("1 FEET converted to INCHES: " + converted.getValue() + " " + converted.getUnitName());

        QuantityDTO sum = controller.add(length1, length2);
        System.out.println("1 FEET + 12 INCHES = " + sum.getValue() + " " + sum.getUnitName());

        QuantityDTO difference = controller.subtract(length1, length2);
        System.out.println("1 FEET - 12 INCHES = " + difference.getValue() + " " + difference.getUnitName());

        double ratio = controller.divide(length1, length2);
        System.out.println("1 FEET / 12 INCHES = " + ratio);

        System.out.println();
    }

    private static void demonstrateWeightOperations(QuantityMeasurementController controller) {
        System.out.println("--- WEIGHT OPERATIONS ---");

        QuantityDTO weight1 = new QuantityDTO(1.0, "KILOGRAM", "WEIGHT");
        QuantityDTO weight2 = new QuantityDTO(1000.0, "GRAM", "WEIGHT");

        boolean isEqual = controller.compare(weight1, weight2);
        System.out.println("1 KILOGRAM equals 1000 GRAM: " + isEqual);

        QuantityDTO converted = controller.convert(weight1, "GRAM");
        System.out.println("1 KILOGRAM converted to GRAM: " + converted.getValue() + " " + converted.getUnitName());

        QuantityDTO sum = controller.add(weight1, weight2);
        System.out.println("1 KILOGRAM + 1000 GRAM = " + sum.getValue() + " " + sum.getUnitName());

        System.out.println();
    }

    private static void demonstrateVolumeOperations(QuantityMeasurementController controller) {
        System.out.println("--- VOLUME OPERATIONS ---");

        QuantityDTO volume1 = new QuantityDTO(1.0, "LITRE", "VOLUME");
        QuantityDTO volume2 = new QuantityDTO(1000.0, "MILLILITRE", "VOLUME");

        boolean isEqual = controller.compare(volume1, volume2);
        System.out.println("1 LITRE equals 1000 MILLILITRE: " + isEqual);

        QuantityDTO converted = controller.convert(volume1, "MILLILITRE");
        System.out.println("1 LITRE converted to MILLILITRE: " + converted.getValue() + " " + converted.getUnitName());

        QuantityDTO sum = controller.add(volume1, volume2);
        System.out.println("1 LITRE + 1000 MILLILITRE = " + sum.getValue() + " " + sum.getUnitName());

        System.out.println();
    }

    private static void demonstrateTemperatureOperations(QuantityMeasurementController controller) {
        System.out.println("--- TEMPERATURE OPERATIONS ---");

        QuantityDTO temp1 = new QuantityDTO(0.0, "CELSIUS", "TEMPERATURE");
        QuantityDTO temp2 = new QuantityDTO(32.0, "FAHRENHEIT", "TEMPERATURE");

        boolean isEqual = controller.compare(temp1, temp2);
        System.out.println("0 CELSIUS equals 32 FAHRENHEIT: " + isEqual);

        QuantityDTO converted = controller.convert(temp1, "FAHRENHEIT");
        System.out.println("0 CELSIUS converted to FAHRENHEIT: " + converted.getValue() + " " + converted.getUnitName());

        QuantityDTO temp3 = new QuantityDTO(100.0, "CELSIUS", "TEMPERATURE");
        QuantityDTO convertedTemp = controller.convert(temp3, "FAHRENHEIT");
        System.out.println("100 CELSIUS converted to FAHRENHEIT: " + convertedTemp.getValue() + " " + convertedTemp.getUnitName());

        try {
            controller.add(temp1, temp2);
        } catch (Exception e) {
            System.out.println("Temperature addition not supported: " + e.getMessage());
        }

        System.out.println();
    }
}
