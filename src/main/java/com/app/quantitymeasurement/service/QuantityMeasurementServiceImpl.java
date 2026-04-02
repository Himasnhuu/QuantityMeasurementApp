package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.exception.QuantityMeasurementException;
import com.app.quantitymeasurement.model.QuantityModel;
import com.app.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.app.quantitymeasurement.units.*;

public class QuantityMeasurementServiceImpl implements QuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("Repository cannot be null");
        }
        this.repository = repository;
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {
        try {
            QuantityModel model1 = dtoToModel(q1);
            QuantityModel model2 = dtoToModel(q2);

            boolean result = model1.equals(model2);

            saveOperation(q1, q2, "COMPARE", String.valueOf(result), false, null);

            return result;

        } catch (Exception e) {
            saveOperation(q1, q2, "COMPARE", null, true, e.getMessage());
            throw new QuantityMeasurementException("Comparison failed: " + e.getMessage(), e);
        }
    }

    @Override
    public QuantityDTO convert(QuantityDTO quantity, String targetUnit) {
        try {
            QuantityModel model = dtoToModel(quantity);
            IMeasurable targetUnitEnum = resolveUnit(targetUnit, quantity.getMeasurementType());

            QuantityModel convertedModel = model.convertTo(targetUnitEnum);

            QuantityDTO result = modelToDto(convertedModel);

            QuantityDTO dummyDto = new QuantityDTO(0.0, targetUnit, quantity.getMeasurementType());
            saveOperation(quantity, dummyDto, "CONVERT", result.toString(), false, null);

            return result;

        } catch (Exception e) {
            QuantityDTO dummyDto = new QuantityDTO(0.0, targetUnit, quantity.getMeasurementType());
            saveOperation(quantity, dummyDto, "CONVERT", null, true, e.getMessage());
            throw new QuantityMeasurementException("Conversion failed: " + e.getMessage(), e);
        }
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {
        try {
            QuantityModel model1 = dtoToModel(q1);
            QuantityModel model2 = dtoToModel(q2);

            QuantityModel resultModel = model1.add(model2);

            QuantityDTO result = modelToDto(resultModel);

            saveOperation(q1, q2, "ADD", result.toString(), false, null);

            return result;

        } catch (Exception e) {
            saveOperation(q1, q2, "ADD", null, true, e.getMessage());
            throw new QuantityMeasurementException("Addition failed: " + e.getMessage(), e);
        }
    }

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {
        try {
            QuantityModel model1 = dtoToModel(q1);
            QuantityModel model2 = dtoToModel(q2);

            QuantityModel resultModel = model1.subtract(model2);

            QuantityDTO result = modelToDto(resultModel);

            saveOperation(q1, q2, "SUBTRACT", result.toString(), false, null);

            return result;

        } catch (Exception e) {
            saveOperation(q1, q2, "SUBTRACT", null, true, e.getMessage());
            throw new QuantityMeasurementException("Subtraction failed: " + e.getMessage(), e);
        }
    }

    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {
        try {
            QuantityModel model1 = dtoToModel(q1);
            QuantityModel model2 = dtoToModel(q2);

            double result = model1.divide(model2);

            saveOperation(q1, q2, "DIVIDE", String.valueOf(result), false, null);

            return result;

        } catch (Exception e) {
            saveOperation(q1, q2, "DIVIDE", null, true, e.getMessage());
            throw new QuantityMeasurementException("Division failed: " + e.getMessage(), e);
        }
    }

    // ---------------- HELPER METHODS ----------------

    private QuantityModel dtoToModel(QuantityDTO dto) {
        if (dto == null) {
            throw new QuantityMeasurementException("QuantityDTO cannot be null");
        }

        IMeasurable unit = resolveUnit(dto.getUnitName(), dto.getMeasurementType());
        return new QuantityModel(dto.getValue(), unit);
    }

    private QuantityDTO modelToDto(QuantityModel model) {
        if (model == null) {
            throw new QuantityMeasurementException("QuantityModel cannot be null");
        }

        String measurementType = getMeasurementType(model.getUnit());
        return new QuantityDTO(model.getValue(), model.getUnit().getUnitName(), measurementType);
    }

    private IMeasurable resolveUnit(String unitName, String measurementType) {
        if (unitName == null || measurementType == null) {
            throw new QuantityMeasurementException("Unit name and measurement type cannot be null");
        }

        try {
            if ("LENGTH".equalsIgnoreCase(measurementType)) {
                return LengthUnit.valueOf(unitName.toUpperCase());
            } else if ("WEIGHT".equalsIgnoreCase(measurementType)) {
                return WeightUnit.valueOf(unitName.toUpperCase());
            } else if ("VOLUME".equalsIgnoreCase(measurementType)) {
                return VolumeUnit.valueOf(unitName.toUpperCase());
            } else if ("TEMPERATURE".equalsIgnoreCase(measurementType)) {
                return TemperatureUnit.valueOf(unitName.toUpperCase());
            } else {
                throw new QuantityMeasurementException("Unknown measurement type: " + measurementType);
            }
        } catch (IllegalArgumentException e) {
            throw new QuantityMeasurementException("Invalid unit name: " + unitName + " for type: " + measurementType, e);
        }
    }

    private String getMeasurementType(IMeasurable unit) {
        if (unit instanceof LengthUnit) {
            return "LENGTH";
        } else if (unit instanceof WeightUnit) {
            return "WEIGHT";
        } else if (unit instanceof VolumeUnit) {
            return "VOLUME";
        } else if (unit instanceof TemperatureUnit) {
            return "TEMPERATURE";
        } else {
            throw new QuantityMeasurementException("Unknown unit type: " + unit.getClass().getName());
        }
    }

    private void saveOperation(QuantityDTO q1, QuantityDTO q2, String operation, 
                               String result, boolean isError, String errorMessage) {
        try {
            QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                q1.getValue(),
                q1.getUnitName(),
                q2 != null ? q2.getValue() : 0.0,
                q2 != null ? q2.getUnitName() : "",
                operation,
                result,
                isError,
                errorMessage
            );
            repository.save(entity);
        } catch (Exception e) {
            // Log but don't fail the operation
            System.err.println("Failed to save operation to repository: " + e.getMessage());
        }
    }
}
