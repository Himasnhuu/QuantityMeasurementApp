package com.app.quantitymeasurement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;

public interface IQuantityMeasurementRepository
        extends JpaRepository<QuantityMeasurementEntity, Long> {

}