package com.core;





public interface IMeasurable {
	
	
	// Default lambda – arithmetic supported
    SupportsArithmetic supportsArithmetic = () -> true;
	
	
	double getConversionFactor();
	
	double convertToBaseUnit(double value);
	
	double convertFromBaseUnit(double baseValue);
	
	String getUnitName();
	
	
	// Optional capability check
    default boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }
    
    // Runtime validation hook
    default void validateOperationSupport(String operation) {
        // Default: allow operations
    }
    
    //  returns class name e.g. "LengthUnit"
    String getMeasurementType();
    
    //  NEW - resolves unit name string to IMeasurable instance
    IMeasurable getUnitInstance(String unitName);
	
	
}

