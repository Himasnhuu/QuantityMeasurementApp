package com.app.quantitymeasurement.units;

public enum LengthUnit implements IMeasurable {

	FEET(1.0), INCHES(1.0 / 12.0), YARDS(3.0), CENTIMETERS(1.0 / 30.48);

	private final double conversionFactor;

	LengthUnit(double conversionFactor) {
		this.conversionFactor = conversionFactor;
	}

	@Override
	public double getConversionFactor() {
		return conversionFactor;
	}

	@Override
	public double convertToBaseUnit(double value) {
		return value * conversionFactor;
	}

	@Override
	public double convertFromBaseUnit(double baseValue) {
		return baseValue / conversionFactor;
	}
	
	@Override
	public String getUnitName() {
		return name();
	}

	@Override
	public boolean supportsArithmetic() {
		return supportsArithmetic.isSupported();
	}

	@Override
	public void validateOperationSupport(String operation) {
		if (!supportsArithmetic()) {
			throw new UnsupportedOperationException(
					"Operation " + operation + " not supported for this measurement type");
		}
	}
}
