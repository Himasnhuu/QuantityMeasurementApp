package com.app.quantitymeasurement;

import com.app.quantitymeasurement.unit.LengthUnit;
import com.app.quantitymeasurement.unit.TemperatureUnit;
import com.app.quantitymeasurement.unit.VolumeUnit;
import com.app.quantitymeasurement.unit.WeightUnit;

public class QuantityDemo {

    public static void runDemo() {

        System.out.println("----- UC1–UC16 Demonstration -----");

        // UC3 Length Equality
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);

        System.out.println("1 ft == 12 inches → " + l1.equals(l2));

        Quantity<LengthUnit> y1 = new Quantity<>(1.0, LengthUnit.YARDS);
        Quantity<LengthUnit> f1 = new Quantity<>(3.0, LengthUnit.FEET);

        System.out.println("1 yard == 3 feet → " + y1.equals(f1));

        Quantity<LengthUnit> cm1 = new Quantity<>(1.0, LengthUnit.CENTIMETERS);
        Quantity<LengthUnit> inch1 = new Quantity<>(0.393701, LengthUnit.INCHES);

        System.out.println("1 cm == 0.393701 inch → " + cm1.equals(inch1));

        // Conversion
        System.out.println("\n--- Conversion ---");

        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        System.out.println("1 ft to inches → " + feet.convertTo(LengthUnit.INCHES));

        Quantity<LengthUnit> yards = new Quantity<>(3.0, LengthUnit.YARDS);
        System.out.println("3 yards to feet → " + yards.convertTo(LengthUnit.FEET));

        // Addition
        System.out.println("\n--- Addition ---");

        Quantity<LengthUnit> addResult =
                new Quantity<>(1.0, LengthUnit.FEET)
                        .add(new Quantity<>(12.0, LengthUnit.INCHES));

        System.out.println("1 ft + 12 inches → " + addResult);

        // Subtraction
        System.out.println("\n--- Subtraction ---");

        Quantity<LengthUnit> subtractResult =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(6.0, LengthUnit.INCHES));

        System.out.println("10 ft - 6 inches → " + subtractResult);

        // Division
        System.out.println("\n--- Division ---");

        double divideResult =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(2.0, LengthUnit.FEET));

        System.out.println("10 ft / 2 ft → " + divideResult);

        // Weight
        System.out.println("\n--- Weight ---");

        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        System.out.println("1 kg == 1000 g → " + w1.equals(w2));

        // Volume
        System.out.println("\n--- Volume ---");

        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        System.out.println("1 L == 1000 mL → " + v1.equals(v2));

        // Temperature
        System.out.println("\n--- Temperature ---");

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        System.out.println("0 C to F → " +
                t1.convertTo(TemperatureUnit.FAHRENHEIT));

        System.out.println("\n----- Demo Finished -----");
    }
}