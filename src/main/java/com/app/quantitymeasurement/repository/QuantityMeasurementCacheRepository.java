package com.app.quantitymeasurement.repository;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository {

    private static QuantityMeasurementCacheRepository instance;

    private final List<QuantityMeasurementEntity> measurements;

    private QuantityMeasurementCacheRepository() {
        this.measurements = new ArrayList<QuantityMeasurementEntity>();
    }

    public static QuantityMeasurementCacheRepository getInstance() {
        if (instance == null) {
            synchronized (QuantityMeasurementCacheRepository.class) {
                if (instance == null) {
                    instance = new QuantityMeasurementCacheRepository();
                }
            }
        }
        return instance;
    }

    @Override
    public void save(QuantityMeasurementEntity entity) {
        if (entity != null) {
            measurements.add(entity);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {
        return Collections.unmodifiableList(measurements);
    }
}
