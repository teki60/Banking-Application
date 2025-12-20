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



        String explainTransactionsPrompt = "You are a financial assistant for a banking application.\n" +
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
                .user(explainTransactionsPrompt)
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

    @Override
    public AIResponse getSmartInsights(Long userId, int transactionCount) {

        List<Transaction> transactionList = accountService.getLastNTransactions(userId, transactionCount);
        double balance = accountService.getBalance(userId);
        int transactionFrequency = accountService.getTransactionFrequency(userId);
        double averageWithdrawal = accountService.getAverageWithdrawal(userId);

        StringBuilder transactionsString= new StringBuilder();

        for(int i=0;i<transactionList.size();i++){

            Transaction tx = transactionList.get(i);

            transactionsString.append("Transaction ").append(i+1).append(": ")
                    .append(" Type: ").append(tx.getType()).append(", ")
                    .append(" Amount: ").append(tx.getAmount()).append(", ")
                    .append(" Time Stamp: ").append(tx.getTimeStamp())
                    .append("\n");
        }

        String smartInsightsPrompt = "You are a financial advisor for a banking application.\n" +
                "Your role is to help users make better spending decisions and improve their financial habits.\n" +
                "\n" +
                "A user wants smart financial insights based on their account activity.\n" +
                "\n" +
                "You are given:\n" +
                "\n" +
                "The user’s current account balance " + balance +"\n" +
                "\n" +
                "A list of their last N transactions (each with type, amount, and timestamp)" + transactionsString + "\n" +
                "\n" +
                "The frequency of their transactions " + transactionFrequency + "\n" +
                "\n" +
                "The user’s average withdrawal amount" + averageWithdrawal + "\n" +
                "\n" +
                "Analyze this information and provide personalized, future-focused advice in simple and friendly language.\n" +
                "\n" +
                "Your advice should:\n" +
                "\n" +
                "Help the user understand where they may be overspending\n" +
                "\n" +
                "Suggest practical ways to save money\n" +
                "\n" +
                "Encourage healthier spending habits\n" +
                "\n" +
                "Sound supportive and motivating, like a trusted financial advisor\n" +
                "\n" +
                "Avoid judgmental or technical language\n" +
                "\n" +
                "The response must be suitable for a non-technical user.\n" +
                "\n" +
                "Return the output strictly in JSON format with only the following fields:\n" +
                "\n" +
                "{\n" +
                "  \"summary\": \"A short, encouraging overview of the user’s spending habits\",\n" +
                "  \"insight\": \"Clear, actionable advice to help the user save money and spend more wisely in the future\"\n" +
                "}\n" +
                "\n" +
                "\n" +
                "Do not include any additional text outside the JSON response.";

        String aiResponseText = chatClient.prompt()
                .system("You are a helpful financial assistant.")
                .user(smartInsightsPrompt)
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

    @Override
    public AIResponse checkFraudRisk(Long userId, int transactionCount) {

        List<Transaction> transactionList = accountService.getLastNTransactions(userId, transactionCount);
        double balance = accountService.getBalance(userId);
        int transactionFrequency = accountService.getTransactionFrequency(userId);
        double averageWithdrawal = accountService.getAverageWithdrawal(userId);
        double maxWithdrawal = accountService.getMaxWithdrawal(userId, transactionCount);
        int lateNightTransactionCount = accountService.getLateNightTransactionCount(userId);

        StringBuilder transactionsString= new StringBuilder();

        for(int i=0;i<transactionList.size();i++){

            Transaction tx = transactionList.get(i);

            transactionsString.append("Transaction ").append(i+1).append(": ")
                    .append(" Type: ").append(tx.getType()).append(", ")
                    .append(" Amount: ").append(tx.getAmount()).append(", ")
                    .append(" Time Stamp: ").append(tx.getTimeStamp())
                    .append("\n");
        }
        String checkFraudPrompt = "You are a financial risk advisor for a banking application.\n" +
                "\n" +
                "Your role is to help users stay financially safe by calmly highlighting\n" +
                "any unusual or potentially risky account activity, without causing fear\n" +
                "or panic.\n" +
                "\n" +
                "A user wants to understand whether their recent transactions look normal\n" +
                "or if anything may require attention.\n" +
                "\n" +
                "You are given:\n" +
                "\n" +
                "• The user’s current account balance " + balance + "\n" +
                "• A list of their recent transactions (each with type, amount, and timestamp)" + transactionsString + "\n" +
                "• The frequency of their transactions " + transactionFrequency + "\n" +
                "• The user’s average withdrawal amount " + averageWithdrawal + "\n" +
                "• The user’s maximum withdrawal amount " + maxWithdrawal + "\n" +
                "• The user’s late night withdrawal count " + lateNightTransactionCount + "\n" +
                "\n" +
                "Analyze this information carefully and determine whether the activity\n" +
                "appears normal or slightly unusual compared to typical behavior.\n" +
                "\n" +
                "While analyzing:\n" +
                "• Look for unusually large withdrawals\n" +
                "• Look for sudden increases in transaction frequency\n" +
                "• Look for transactions happening at uncommon times\n" +
                "• Look for patterns that differ from regular spending habits\n" +
                "\n" +
                "If everything looks normal, clearly reassure the user.\n" +
                "\n" +
                "If something looks unusual:\n" +
                "• Explain it calmly and clearly\n" +
                "• Avoid alarming or accusatory language\n" +
                "• Do NOT say “fraud detected”\n" +
                "• Do NOT make the user feel blamed or scared\n" +
                "\n" +
                "Your goal is to help the user stay aware and cautious, not afraid.\n" +
                "\n" +
                "Use a friendly, supportive, and professional tone, like a trusted bank advisor.\n" +
                "\n" +
                "The response must be suitable for a non-technical user.\n" +
                "\n" +
                "Return the output strictly in JSON format with only the following fields:\n" +
                "\n" +
                "{\n" +
                "  \"summary\": \"A calm explanation of whether the account activity looks normal or slightly unusual\",\n" +
                "  \"insight\": \"Clear and reassuring advice on what the user should do next, such as reviewing transactions or contacting support if something feels unfamiliar\"\n" +
                "}\n" +
                "\n" +
                "Do not include any additional text outside the JSON response.\n";

        String aiResponseText = chatClient.prompt()
                .system("You are a helpful financial assistant.")
                .user(checkFraudPrompt)
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
