package com;

public interface IMeasurable {

	double getConversionFactor();

	double convertToBaseUnit(double value);

	double convertFromBaseUnit(double baseValue);

	String getUnitName();

	SupportsArithmetic supportsArithmetic = () -> true;

	default boolean supportsArithmetic() {
		return supportsArithmetic.isSupported();
	}

	default void validateOperationSupport(String operation) {
		if (!supportsArithmetic()) {
			throw new UnsupportedOperationException(
					"Operation " + operation + " not supported for this measurement type");
		}
	}
}

@FunctionalInterface
interface SupportsArithmetic {
	boolean isSupported();
}