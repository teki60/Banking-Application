package com.example.banking_app_backend.controller;

import com.example.banking_app_backend.dto.ai.AIResponse;
import com.example.banking_app_backend.service.AIService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AIController {

    private final AIService aiService;

    private final ChatClient chatClient;

    @GetMapping("/explain-transactions/{userId}")
    public AIResponse explainTransactions(@PathVariable Long userId, @RequestParam int n) throws JsonProcessingException {
    return aiService.explainTransactions(userId,n);
    }

    @GetMapping("/smart-insights/{userId}")
    public AIResponse smartInsights(@PathVariable Long userId, @RequestParam int n) throws JsonProcessingException{
        return aiService.getSmartInsights(userId, n);
    }

    @GetMapping("/check-fraud/{userId}")
    public AIResponse checkFraud(@PathVariable Long userId, @RequestParam int n){
        return aiService.checkFraudRisk(userId,n);


    }


}
