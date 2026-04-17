package com.app.quantityservice.service;

import com.app.quantityservice.dto.QuantityDTO;

public interface IQuantityMeasurementService {

//	boolean compare(QuantityDTO q1, QuantityDTO q2);
//
//	QuantityDTO convert(QuantityDTO q, String targetUnit);
//
//	QuantityDTO add(QuantityDTO q1, QuantityDTO q2);
//
//	QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2);
//
////    double divide(QuantityDTO q1, QuantityDTO q2);
//	QuantityDTO divide(QuantityDTO q1, QuantityDTO q2);
	
	QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2, String userEmail);
	QuantityDTO divide(QuantityDTO q1, QuantityDTO q2, String userEmail);

	boolean compare(QuantityDTO q1, QuantityDTO q2, String userEmail);

	QuantityDTO convert(QuantityDTO q, String targetUnitName, String userEmail);

	QuantityDTO add(QuantityDTO q1, QuantityDTO q2, String userEmail);
}