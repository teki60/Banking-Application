package com.example.banking_app_backend.dto.account;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositResponse {

    private boolean result;

    private String message;

    private double newBalance;
}
