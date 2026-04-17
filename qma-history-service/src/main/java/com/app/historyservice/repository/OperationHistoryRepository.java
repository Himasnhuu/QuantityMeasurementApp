package com.app.historyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.app.historyservice.entity.OperationHistory;

public interface OperationHistoryRepository 
        extends JpaRepository<OperationHistory, Long> {

    List<OperationHistory> findByUserEmail(String userEmail);
}