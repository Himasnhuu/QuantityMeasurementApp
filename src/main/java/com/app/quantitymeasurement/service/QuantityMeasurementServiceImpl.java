package com.app.quantitymeasurement.service;

import org.springframework.stereotype.Service;

import com.app.quantitymeasurement.Quantity;
import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.app.quantitymeasurement.unit.LengthUnit;

@Service
public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repo) {
        this.repository = repo;
    }

    private Quantity<LengthUnit> toQuantity(QuantityDTO dto) {
        return new Quantity<>(
                dto.getValue(),
                LengthUnit.valueOf(dto.getUnit())
        );
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {

        Quantity<LengthUnit> quantity1 = toQuantity(q1);
        Quantity<LengthUnit> quantity2 = toQuantity(q2);

        boolean result = quantity1.equals(quantity2);

        repository.save(new QuantityMeasurementEntity(
                "COMPARE",
                q1.toString(),
                q2.toString(),
                String.valueOf(result)
        ));

        return result;
    }

    @Override
    public QuantityDTO convert(QuantityDTO q, String targetUnit) {

        Quantity<LengthUnit> quantity = toQuantity(q);

        Quantity<LengthUnit> result =
                quantity.convertTo(LengthUnit.valueOf(targetUnit));

        repository.save(new QuantityMeasurementEntity(
                "CONVERT",
                q.toString(),
                targetUnit,
                result.toString()
        ));

        return new QuantityDTO(
                result.getValue(),
                result.getUnit().name()
        );
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {

        Quantity<LengthUnit> quantity1 = toQuantity(q1);
        Quantity<LengthUnit> quantity2 = toQuantity(q2);

        Quantity<LengthUnit> result = quantity1.add(quantity2);

        repository.save(new QuantityMeasurementEntity(
                "ADD",
                q1.toString(),
                q2.toString(),
                result.toString()
        ));

        return new QuantityDTO(
                result.getValue(),
                result.getUnit().name()
        );
    }

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {

        Quantity<LengthUnit> quantity1 = toQuantity(q1);
        Quantity<LengthUnit> quantity2 = toQuantity(q2);

        Quantity<LengthUnit> result = quantity1.subtract(quantity2);

        repository.save(new QuantityMeasurementEntity(
                "SUBTRACT",
                q1.toString(),
                q2.toString(),
                result.toString()
        ));

        return new QuantityDTO(
                result.getValue(),
                result.getUnit().name()
        );
    }

    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {

        Quantity<LengthUnit> quantity1 = toQuantity(q1);
        Quantity<LengthUnit> quantity2 = toQuantity(q2);

        double result = quantity1.divide(quantity2);

        repository.save(new QuantityMeasurementEntity(
                "DIVIDE",
                q1.toString(),
                q2.toString(),
                String.valueOf(result)
        ));

        return result;
    }
}