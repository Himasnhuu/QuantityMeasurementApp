package com;

public class QuantityMeasurementApp {
    
    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }
    
    public static class Inches {
        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            Inches other = (Inches) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }
    
    public static void demonstrateFeetEquality() {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);
        System.out.println("Comparing 1.0 ft and 1.0 ft: " + feet1.equals(feet2));

        Feet feet3 = new Feet(1.0);
        Feet feet4 = new Feet(2.0);
        System.out.println("Comparing 1.0 ft and 2.0 ft: " + feet3.equals(feet4));
    }
    
    public static void demonstrateInchesEquality() {
        Inches inches1 = new Inches(1.0);
        Inches inches2 = new Inches(1.0);
        System.out.println("Comparing 1.0 inch and 1.0 inch: " + inches1.equals(inches2));

        Inches inches3 = new Inches(1.0);
        Inches inches4 = new Inches(2.0);
        System.out.println("Comparing 1.0 inch and 2.0 inch: " + inches3.equals(inches4));
    }

    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchesEquality();
    }
}
