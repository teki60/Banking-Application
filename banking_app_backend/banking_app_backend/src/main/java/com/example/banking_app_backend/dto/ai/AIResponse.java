package com.example.banking_app_backend.dto.ai;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AIResponse {

    private Long userId;

    private int transactionCount;

    private AIExplanation explanation;

}
