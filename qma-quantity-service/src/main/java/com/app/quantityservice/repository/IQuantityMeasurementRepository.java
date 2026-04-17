package com.app.quantityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.quantityservice.entity.QuantityMeasurementEntity;

public interface IQuantityMeasurementRepository
        extends JpaRepository<QuantityMeasurementEntity, Long> {

}