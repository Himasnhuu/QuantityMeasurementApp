package com.app.historyservice.service;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

import com.app.historyservice.entity.OperationHistory;
import com.app.historyservice.repository.OperationHistoryRepository;

@Service
public class OperationHistoryService {

    private final OperationHistoryRepository repo;

    public OperationHistoryService(OperationHistoryRepository repo) {
        this.repo = repo;
    }

    public OperationHistory save(String email, String op, String input, String result) {

        OperationHistory entity = new OperationHistory();
        entity.setUserEmail(email);
        entity.setOperationType(op);
        entity.setInputData(input);
        entity.setResult(result);
        entity.setCreatedAt(LocalDateTime.now());

        return repo.save(entity);
    }

    public List<OperationHistory> getByUser(String email) {
        return repo.findByUserEmail(email);
    }
}