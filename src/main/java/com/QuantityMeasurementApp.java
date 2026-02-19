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

    public static void main(String[] args) {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);
        System.out.println("Comparing 1.0 ft and 1.0 ft: " + feet1.equals(feet2));

        Feet feet3 = new Feet(1.0);
        Feet feet4 = new Feet(2.0);
        System.out.println("Comparing 1.0 ft and 2.0 ft: " + feet3.equals(feet4));

        Feet feet5 = new Feet(1.0);
        System.out.println("Comparing 1.0 ft and null: " + feet5.equals(null));

        Feet feet6 = new Feet(1.0);
        System.out.println("Comparing 1.0 ft with itself: " + feet6.equals(feet6));
    }
}
