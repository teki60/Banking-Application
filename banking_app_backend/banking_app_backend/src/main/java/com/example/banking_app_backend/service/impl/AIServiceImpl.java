package com.example.banking_app_backend.service.impl;

import com.example.banking_app_backend.dto.ai.AIExplanation;
import com.example.banking_app_backend.dto.ai.AIResponse;
import com.example.banking_app_backend.entity.Transaction;
import com.example.banking_app_backend.service.AIService;
import com.example.banking_app_backend.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AIServiceImpl implements AIService {

    private final AccountService accountService;
    private final ChatClient chatClient;
    private final ObjectMapper objectMapper;

    @Override
    public AIResponse explainTransactions(Long userId, int transactionCount) {


        List<Transaction> transactionList = accountService.getLastNTransactions(userId, transactionCount);
        double balance = accountService.getBalance(userId);

        StringBuilder transactionsString= new StringBuilder();

        for(int i=0;i<transactionList.size();i++){

            Transaction tx = transactionList.get(i);

            transactionsString.append("Transaction ").append(i+1).append(": ")
                                .append(" Type: ").append(tx.getType()).append(", ")
                                .append(" Amount: ").append(tx.getAmount()).append(", ")
                                .append(" Time Stamp: ").append(tx.getTimeStamp())
                                .append("\n");
        }



        String prompt = "You are a financial assistant for a banking application.\n" +
                "A user wants an explanation of their recent account activity.\n" +
                "\n" +
                "You are given:\n" +
                "The user’s current account balance " + balance + " \n"+
                "A list of their last N transactions (each with type, amount, and timestamp) " + transactionsString +"\n"+
                "\n" +
                "Analyze the transactions and explain, in simple and friendly language:\n" +
                "How the balance changed\n" +
                "Whether deposits or withdrawals are dominant\n" +
                "Any noticeable recent activity patterns\n" +
                "\n" +
                "The response must be suitable for a non-technical user.\n" +
                "\n" +
                "Return the output strictly in JSON format with only the following fields:\n" +
                "\n" +
                "{\n" +
                "  \"summary\": \"Brief explanation of balance change\",\n" +
                "  \"insight\": \"Key observation or pattern from the transactions\"\n" +
                "}\n" +
                "\n" +
                "\n" +
                "Do not include any additional text outside the JSON response.";

        String aiResponseText = chatClient.prompt()
                .system("You are a helpful financial assistant.")
                .user(prompt)
                .call()
                .content();

        log.info("\nAI RAW RESPONSE: {}", aiResponseText);

        AIResponse response = new AIResponse();
        response.setUserId(userId);
        response.setTransactionCount(transactionCount);
        AIExplanation aiExplanation = new AIExplanation();
        try {
            aiExplanation = objectMapper.readValue(aiResponseText, AIExplanation.class);
        } catch (JsonProcessingException e) {
            aiExplanation.setSummary("We’re unable to generate summary right now");
            aiExplanation.setInsight("We’re unable to generate insights right now");

        }
        response.setExplanation(aiExplanation);

        return response;
    }
}
