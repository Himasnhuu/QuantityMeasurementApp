//package com.app.quantityservice.controller;
//
//import org.springframework.web.bind.annotation.*;
//
//import com.app.quantityservice.*;
//import com.app.quantityservice.dto.QuantityDTO;
//import com.app.quantityservice.service.IQuantityMeasurementService;
//
//@RestController
//@RequestMapping("/quantity")
//public class QuantityMeasurementController {
//
//	private final IQuantityMeasurementService service;
//
//	public QuantityMeasurementController(IQuantityMeasurementService service) {
//		this.service = service;
//	}
//
//	@GetMapping("/test")
//	public String test() {
//		return "Protected API working";
//	}
//
//	@PostMapping("/compare")
//	public boolean compare(@RequestBody QuantityDTO[] quantities) {
//		return service.compare(quantities[0], quantities[1]);
//	}
//
//	@PostMapping("/add")
//	public QuantityDTO add(@RequestBody QuantityDTO[] quantities) {
//		return service.add(quantities[0], quantities[1]);
//	}
//
//	@PostMapping("/subtract")
//	public QuantityDTO subtract(@RequestBody QuantityDTO[] quantities) {
//		return service.subtract(quantities[0], quantities[1]);
//	}
//
//	@PostMapping("/divide")
//	public QuantityDTO divide(@RequestBody QuantityDTO[] quantities) {
//		return service.divide(quantities[0], quantities[1]);
//	}
//
////	public double divide(@RequestBody QuantityDTO[] quantities) {
////		return service.divide(quantities[0], quantities[1]);
////	}
//
//	@PostMapping("/convert")
//	public QuantityDTO convert(@RequestBody QuantityDTO quantity, @RequestParam String targetUnit) {
//		return service.convert(quantity, targetUnit);
//	}
//}

package com.app.quantityservice.controller;

import org.springframework.web.bind.annotation.*;

import com.app.quantityservice.dto.QuantityDTO;
import com.app.quantityservice.service.IQuantityMeasurementService;

@RestController
@RequestMapping("/quantity")
public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    @GetMapping("/test")
    public String test() {
        return "Protected API working";
    }

    // ================= COMPARE =================
    @PostMapping("/compare")
    public boolean compare(
    		@RequestHeader(value = "X-User-Email", required = false) String userEmail,
            @RequestBody QuantityDTO[] quantities) {

        return service.compare(quantities[0], quantities[1], userEmail);
    }

    // ================= ADD =================
    @PostMapping("/add")
    public QuantityDTO add(
    		@RequestHeader(value = "X-User-Email", required = false) String userEmail,
            @RequestBody QuantityDTO[] quantities) {

        return service.add(quantities[0], quantities[1], userEmail);
    }

    // ================= SUBTRACT =================
    @PostMapping("/subtract")
    public QuantityDTO subtract(
    		@RequestHeader(value = "X-User-Email", required = false) String userEmail,
            @RequestBody QuantityDTO[] quantities) {

        return service.subtract(quantities[0], quantities[1], userEmail);
    }

    // ================= DIVIDE =================
    @PostMapping("/divide")
    public QuantityDTO divide(
    		@RequestHeader(value = "X-User-Email", required = false) String userEmail,
            @RequestBody QuantityDTO[] quantities) {

        return service.divide(quantities[0], quantities[1], userEmail);
    }

    // ================= CONVERT =================
    @PostMapping("/convert")
    public QuantityDTO convert(
    		@RequestHeader(value = "X-User-Email", required = false) String userEmail,
            @RequestBody QuantityDTO quantity,
            @RequestParam String targetUnit) {

        return service.convert(quantity, targetUnit, userEmail);
    }
}