package com.app.quantitymeasurement.units;

public enum TemperatureUnit implements IMeasurable {

	CELSIUS(new TemperatureConverter() {
		@Override
		public double toCelsius(double value) {
			return value;
		}

		@Override
		public double fromCelsius(double value) {
			return value;
		}
	}),

	FAHRENHEIT(new TemperatureConverter() {
		@Override
		public double toCelsius(double value) {
			return (value - 32) * 5 / 9;
		}

		@Override
		public double fromCelsius(double value) {
			return (value * 9 / 5) + 32;
		}
	}),

	KELVIN(new TemperatureConverter() {
		@Override
		public double toCelsius(double value) {
			return value - 273.15;
		}

		@Override
		public double fromCelsius(double value) {
			return value + 273.15;
		}
	});

	private final TemperatureConverter converter;

	private final SupportsArithmetic supportsArithmetic = new SupportsArithmetic() {
		@Override
		public boolean isSupported() {
			return false;
		}
	};

	TemperatureUnit(TemperatureConverter converter) {
		this.converter = converter;
	}

	@Override
	public double getConversionFactor() {
		return 1.0;
	}

	@Override
	public double convertToBaseUnit(double value) {
		return converter.toCelsius(value);
	}

	@Override
	public double convertFromBaseUnit(double baseValue) {
		return converter.fromCelsius(baseValue);
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
		throw new UnsupportedOperationException("Temperature does not support " + operation + " operation");
	}
}

interface TemperatureConverter {
	double toCelsius(double value);
	double fromCelsius(double value);
}
