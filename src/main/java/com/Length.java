package com;

public class Length {
    
    private double value;
    private LengthUnit unit;

    //-----------------------------------------------------------------------------------------------
    //enum 
    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(1.0/2.54);
        
        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }
    // ----------------------------------------------------------------------------------------------------

    public Length(double value, LengthUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    private double convertToBaseUnit() {
        return this.value * this.unit.getConversionFactor();
    }

    public boolean compare(Length thatLength) {
        double thisBase = this.convertToBaseUnit();
        double thatBase = thatLength.convertToBaseUnit();
//        return Double.compare(thisBase, thatBase) == 0;
        
        double epsilon = 0.0001;
        return Math.abs(thisBase - thatBase) < epsilon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Length length = (Length) o;
        return compare(length);
    }

    public static void main(String[] args) {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        System.out.println("Are lengths equal? " + length1.equals(length2));
    }
}
