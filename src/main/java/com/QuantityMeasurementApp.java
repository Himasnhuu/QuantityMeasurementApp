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

    public static void main(String[] args) {
        demonstrateFeetInchesComparison();
    }
}
