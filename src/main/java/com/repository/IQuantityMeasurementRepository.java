package com.repository;

import java.util.List;

import com.entity.QuantityMeasurementEntity;

/**
 * Data access contract — ISP: only exposes what is needed.
 * Swap implementations (cache → DB) without touching service layer.
 */
public interface IQuantityMeasurementRepository {

    void save(QuantityMeasurementEntity entity);

    List<QuantityMeasurementEntity> getAllMeasurements();
    
 
    void deleteAll();

}
