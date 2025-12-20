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

    @GetMapping("/test")
    public String test() {
        return chatClient.prompt()
                .user("Say hello in one sentence")
                .call()
                .content();
    }


}
