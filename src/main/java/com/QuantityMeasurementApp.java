package com;

public class QuantityMeasurementApp {
    
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1.equals(length2);
    }
    
    public static void demonstrateFeetInchesComparison() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        Length inches = new Length(12.0, Length.LengthUnit.INCHES);
        System.out.println("Comparing 1.0 ft and 12.0 inches: " + demonstrateLengthEquality(feet, inches));
    }
    
    public static void demonstrateYardsComparison() {
        // Demonstrate Yards and Inches comparison
        Length length = new Length(1.0, Length.LengthUnit.YARDS);
        Length inches = new Length(36.0, Length.LengthUnit.INCHES);
        System.out.println("Comparing 1.0 yd and 36.0 inches: " + demonstrateLengthEquality(length, inches));

        // Demonstrate Yards and Feet comparison
        Length yard1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length feet = new Length(3.0, Length.LengthUnit.FEET);
        System.out.println("Comparing 1.0 yd and 3.0 ft: " + demonstrateLengthEquality(yard1, feet));
    }

    public static void demonstrateCentimetersComparison() {
        // Demonstrate Centimeters and Inches comparison
        Length cm = new Length(1.0, Length.LengthUnit.CENTIMETERS);
        Length inches = new Length(0.393701, Length.LengthUnit.INCHES);
        System.out.println("Comparing 1.0 cm and 0.393701 inches: " + demonstrateLengthEquality(cm, inches));

        // Demonstrate Centimeters and Feet comparison
        Length cm2 = new Length(30.48, Length.LengthUnit.CENTIMETERS);
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        System.out.println("Comparing 30.48 cm and 1.0 ft: " + demonstrateLengthEquality(cm2, feet));
    }

    public static void main(String[] args) {
        demonstrateFeetInchesComparison();
        demonstrateYardsComparison();
        demonstrateCentimetersComparison();
    }
}
