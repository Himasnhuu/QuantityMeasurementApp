package com.app.quantityservice.service;

import com.app.quantityservice.dto.QuantityDTO;
import com.app.quantityservice.entity.QuantityMeasurementEntity;
import com.app.quantityservice.repository.IQuantityMeasurementRepository;
import com.app.quantityservice.unit.IMeasurable;
import com.app.quantityservice.unit.UnitRegistry;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

	private final IQuantityMeasurementRepository repository;
	private final UnitRegistry unitRegistry;
	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper = new ObjectMapper();

	public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository, UnitRegistry unitRegistry,
			RestTemplate restTemplate) {

		this.repository = repository;
		this.unitRegistry = unitRegistry;
		this.restTemplate = restTemplate;
	}

	private void saveHistory(String userEmail, String operation, Object input, String resultValue) {

		try {
			String url = "http://history-service/history/save";

			String inputJson = objectMapper.writeValueAsString(input);
//			String email = request.getHeader("X-User-Email");

			HttpHeaders headers = new HttpHeaders();
//			headers.set("Authorization", authHeader);
			headers.set("X-User-Email", userEmail);
			headers.setContentType(MediaType.APPLICATION_JSON);

			Map<String, String> body = new HashMap<>();
			body.put("operationType", operation);
			body.put("inputData", inputJson);
			body.put("result", resultValue);

			HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

			restTemplate.postForEntity(url, request, String.class);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("History service failed: " + e.getMessage());

		}
	}

	/**
	 * Convert DTO to domain Quantity model
	 */
	private Quantity<IMeasurable> toQuantity(QuantityDTO dto) {
		return new Quantity<>(dto.getValue(), unitRegistry.getUnit(dto.getUnit()));
	}

	@Override
//	public boolean compare(QuantityDTO q1, QuantityDTO q2) {
//		Quantity<IMeasurable> quantity1 = toQuantity(q1);
//		Quantity<IMeasurable> quantity2 = toQuantity(q2);
//
//		boolean result = quantity1.equals(quantity2);
//		saveAudit("COMPARE", q1.toString(), q2.toString(), String.valueOf(result));
//
//		return result;
//	}

	public boolean compare(QuantityDTO q1, QuantityDTO q2, String userEmail) {

		Quantity<IMeasurable> quantity1 = toQuantity(q1);
		Quantity<IMeasurable> quantity2 = toQuantity(q2);

		boolean result = quantity1.equals(quantity2);

		Map<String, Object> input = Map.of("q1", q1, "q2", q2);
		if (!isGuest(userEmail)) {
			saveHistory(userEmail, "COMPARE", input, String.valueOf(result));
		}

		return result;
	}

	@Override
//	public QuantityDTO convert(QuantityDTO q, String targetUnitName) {
//		Quantity<IMeasurable> quantity = toQuantity(q);
//		IMeasurable targetUnit = unitRegistry.getUnit(targetUnitName);
//
//		Quantity<IMeasurable> result = quantity.convertTo(targetUnit);
//
//		saveAudit("CONVERT", q.toString(), targetUnitName, result.toString());
//
//		return new QuantityDTO(result.getValue(), result.getUnit().getUnitName());
//	}

	public QuantityDTO convert(QuantityDTO q, String targetUnitName, String userEmail) {

		Quantity<IMeasurable> quantity = toQuantity(q);
		IMeasurable targetUnit = unitRegistry.getUnit(targetUnitName);

		Quantity<IMeasurable> result = quantity.convertTo(targetUnit);

		Map<String, Object> input = Map.of("q", q, "targetUnit", targetUnitName);

//		saveHistory(authHeader, "CONVERT", input, result.toString());
		String resultFormatted = result.getValue() + ", " + result.getUnit().getUnitName();

		if (!isGuest(userEmail)) {
			saveHistory(userEmail, "CONVERT", input, resultFormatted);
		}

		return new QuantityDTO(result.getValue(), result.getUnit().getUnitName());
	}

	@Override
//	public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {
//		Quantity<IMeasurable> quantity1 = toQuantity(q1);
//		Quantity<IMeasurable> quantity2 = toQuantity(q2);
//
//		Quantity<IMeasurable> result = quantity1.add(quantity2);
//
//		saveAudit("ADD", q1.toString(), q2.toString(), result.toString());
//
//		return new QuantityDTO(result.getValue(), result.getUnit().getUnitName());
//	}

	public QuantityDTO add(QuantityDTO q1, QuantityDTO q2, String userEmail) {

		Quantity<IMeasurable> quantity1 = toQuantity(q1);
		Quantity<IMeasurable> quantity2 = toQuantity(q2);

		Quantity<IMeasurable> result = quantity1.add(quantity2);

		Map<String, Object> input = Map.of("q1", q1, "q2", q2);

//		saveHistory(authHeader, "ADD", input, result.toString());
		String resultFormatted = result.getValue() + ", " + result.getUnit().getUnitName();

		if (!isGuest(userEmail)) {
			saveHistory(userEmail, "ADD", input, resultFormatted);
		}

		return new QuantityDTO(result.getValue(), result.getUnit().getUnitName());
	}

	@Override
//	public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {
//		Quantity<IMeasurable> quantity1 = toQuantity(q1);
//		Quantity<IMeasurable> quantity2 = toQuantity(q2);
//
//		Quantity<IMeasurable> result = quantity1.subtract(quantity2);
//
//		saveAudit("SUBTRACT", q1.toString(), q2.toString(), result.toString());
//
//		return new QuantityDTO(result.getValue(), result.getUnit().getUnitName());
//	}

	public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2, String userEmail) {

		Quantity<IMeasurable> quantity1 = toQuantity(q1);
		Quantity<IMeasurable> quantity2 = toQuantity(q2);

		Quantity<IMeasurable> result = quantity1.subtract(quantity2);

		Map<String, Object> input = Map.of("q1", q1, "q2", q2);

//		saveHistory(authHeader, "SUBTRACT", input, result.toString());
		String resultFormatted = result.getValue() + ", " + result.getUnit().getUnitName();

		if (!isGuest(userEmail)) {
			saveHistory(userEmail, "SUBTRACT", input, resultFormatted);
		}
//		saveHistory();

		return new QuantityDTO(result.getValue(), result.getUnit().getUnitName());
	}

	@Override
//	public QuantityDTO divide(QuantityDTO q1, QuantityDTO q2) {
//		Quantity<IMeasurable> quantity1 = toQuantity(q1);
//		Quantity<IMeasurable> quantity2 = toQuantity(q2);
//
//		Quantity<IMeasurable> result = quantity1.divide(quantity2, quantity1.getUnit());
//
//		saveAudit("DIVIDE", q1.toString(), q2.toString(), result.toString());
//
//		return new QuantityDTO(result.getValue(), result.getUnit().getUnitName());
//	}

	public QuantityDTO divide(QuantityDTO q1, QuantityDTO q2, String userEmail) {

		Quantity<IMeasurable> quantity1 = toQuantity(q1);
		Quantity<IMeasurable> quantity2 = toQuantity(q2);

		Quantity<IMeasurable> result = quantity1.divide(quantity2, quantity1.getUnit());

		Map<String, Object> input = Map.of("q1", q1, "q2", q2);

//		saveHistory(authHeader, "DIVIDE", input, result.toString());
		String resultFormatted = result.getValue() + ", " + result.getUnit().getUnitName();

		if (!isGuest(userEmail)) {
			saveHistory(userEmail, "DIVIDE", input, resultFormatted);
		}
//		saveHistory();

		return new QuantityDTO(result.getValue(), result.getUnit().getUnitName());
	}

	// ================= HELPER METHODS =================

	private boolean isGuest(String userEmail) {
		return (userEmail == null || userEmail.isEmpty());
	}

//	private void saveAudit(String operation, String param1, String param2, String resultValue) {
//		repository.save(new QuantityMeasurementEntity(operation, param1, param2, resultValue));
//	}
}