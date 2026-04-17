package com.app.historyservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import com.app.historyservice.dto.OperationRequestDTO;
import com.app.historyservice.service.OperationHistoryService;

@RestController
@RequestMapping("/history")
public class OperationHistoryController {

    private final OperationHistoryService service;

    public OperationHistoryController(OperationHistoryService service) {
        this.service = service;
    }

    // ✅ SAVE HISTORY
    @PostMapping("/save")
    public ResponseEntity<?> save(
            @RequestBody OperationRequestDTO dto,
            HttpServletRequest request) {

//        String email = (String) request.getAttribute("userEmail");
    	String email = request.getHeader("X-User-Email");
    	System.out.println("EMAIL HEADER: " + request.getHeader("X-User-Email"));
    	System.out.println("BODY: " + dto);

        return ResponseEntity.ok(
                service.save(email,
                        dto.getOperationType(),
                        dto.getInputData(),
                        dto.getResult())
        );
    }

    // ✅ GET USER HISTORY
    @GetMapping("/user")
    public ResponseEntity<?> getUserHistory(HttpServletRequest request) {

//        String email = (String) request.getAttribute("userEmail");
        String email = request.getHeader("X-User-Email");

        return ResponseEntity.ok(service.getByUser(email));
    }
}