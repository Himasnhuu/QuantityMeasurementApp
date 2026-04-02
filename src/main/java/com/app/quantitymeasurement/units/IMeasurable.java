package com.app.quantitymeasurement.units;

public interface IMeasurable {

	double getConversionFactor();

	double convertToBaseUnit(double value);

	double convertFromBaseUnit(double baseValue);

	String getUnitName();

	SupportsArithmetic supportsArithmetic = new SupportsArithmetic() {
		@Override
		public boolean isSupported() {
			return true;
		}
	};

	boolean supportsArithmetic();

	void validateOperationSupport(String operation);
}

interface SupportsArithmetic {
	boolean isSupported();
}
