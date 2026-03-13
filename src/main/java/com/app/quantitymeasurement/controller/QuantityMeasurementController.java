package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.service.QuantityMeasurementService;

public class QuantityMeasurementController {

    private final QuantityMeasurementService service;

    public QuantityMeasurementController(QuantityMeasurementService service) {
        if (service == null) {
            throw new IllegalArgumentException("Service cannot be null");
        }
        this.service = service;
    }

    public boolean compare(QuantityDTO q1, QuantityDTO q2) {
        return service.compare(q1, q2);
    }

    public QuantityDTO convert(QuantityDTO quantity, String targetUnit) {
        return service.convert(quantity, targetUnit);
    }

    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {
        return service.add(q1, q2);
    }

    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {
        return service.subtract(q1, q2);
    }

    public double divide(QuantityDTO q1, QuantityDTO q2) {
        return service.divide(q1, q2);
    }
}
