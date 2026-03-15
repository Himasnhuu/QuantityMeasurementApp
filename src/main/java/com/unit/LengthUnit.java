package com.unit;

import com.core.IMeasurable;

public enum LengthUnit implements IMeasurable{
	FEET(12.0),
	INCHES(1.0),
	YARDS(36.0),             // 1 yd = 36 inches 
    CENTIMETERS(0.0328084);// 1 cm=0.3 inches 
	
	private final double conversionFactor;
	
	LengthUnit(double conversionFactor){
		this.conversionFactor=conversionFactor;
	}
	
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
		return this.toString();
	}

	@Override
	public String getMeasurementType() {
		return this.getClass().getSimpleName();
	}

	
	@Override
    public IMeasurable getUnitInstance(String unitName) {
        for (LengthUnit unit : LengthUnit.values()) {
            if (unit.getUnitName().equalsIgnoreCase(unitName)) return unit;
        }
        throw new IllegalArgumentException("Invalid length unit: " + unitName);
    }
}
