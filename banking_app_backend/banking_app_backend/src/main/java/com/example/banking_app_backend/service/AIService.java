package com.example.banking_app_backend.service;

import com.example.banking_app_backend.dto.ai.AIResponse;

public interface AIService {

    AIResponse explainTransactions(Long userId, int transactionCount);

}
