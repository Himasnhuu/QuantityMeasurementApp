package com.app.quantitymeasurement.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.app.quantitymeasurement.Quantity;
import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.app.quantitymeasurement.unit.LengthUnit;
import com.app.quantitymeasurement.user.User;
import com.app.quantitymeasurement.user.UserRepository;

@Service
public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;
    private final UserRepository userRepository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repo,
                                          UserRepository userRepository) {
        this.repository = repo;
        this.userRepository = userRepository;
    }

    // Convert DTO → Quantity
    private Quantity<LengthUnit> toQuantity(QuantityDTO dto) {
        return new Quantity<>(
                dto.getValue(),
                LengthUnit.valueOf(dto.getUnit())
        );
    }

    // Get Logged-in User from JWT
    private User getCurrentUser() {

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return null;
        }

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElse(null);
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {

        Quantity<LengthUnit> quantity1 = toQuantity(q1);
        Quantity<LengthUnit> quantity2 = toQuantity(q2);

        boolean result = quantity1.equals(quantity2);

        User user = getCurrentUser();

        repository.save(new QuantityMeasurementEntity(
                "COMPARE",
                q1.toString(),
                q2.toString(),
                String.valueOf(result),
                user
        ));

        return result;
    }

    @Override
    public QuantityDTO convert(QuantityDTO q, String targetUnit) {

        Quantity<LengthUnit> quantity = toQuantity(q);

        Quantity<LengthUnit> result =
                quantity.convertTo(LengthUnit.valueOf(targetUnit));

        User user = getCurrentUser();

        repository.save(new QuantityMeasurementEntity(
                "CONVERT",
                q.toString(),
                targetUnit,
                result.toString(),
                user
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

        User user = getCurrentUser();

        repository.save(new QuantityMeasurementEntity(
                "ADD",
                q1.toString(),
                q2.toString(),
                result.toString(),
                user
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

        User user = getCurrentUser();

        repository.save(new QuantityMeasurementEntity(
                "SUBTRACT",
                q1.toString(),
                q2.toString(),
                result.toString(),
                user
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

        User user = getCurrentUser();

        repository.save(new QuantityMeasurementEntity(
                "DIVIDE",
                q1.toString(),
                q2.toString(),
                String.valueOf(result),
                user
        ));

        return result;
    }
}