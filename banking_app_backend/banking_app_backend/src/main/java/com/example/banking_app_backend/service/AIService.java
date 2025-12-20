package com.example.banking_app_backend.service;

import com.example.banking_app_backend.dto.ai.AIResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface AIService {

    AIResponse explainTransactions(Long userId, int transactionCount) throws JsonProcessingException;

    AIResponse getSmartInsights(Long userId, int transactionCount);

    AIResponse checkFraudRisk(Long userId, int transactionCount);

}
