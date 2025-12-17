package com.example.banking_app_backend.controller;

import com.example.banking_app_backend.dto.ai.AIResponse;
import com.example.banking_app_backend.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AIController {

    private final AIService aiService;

    @GetMapping("/explain-transactions/{userId}")
    public AIResponse explainTransaction(@PathVariable Long userId, @RequestParam int n){
    return null;
    }

}
