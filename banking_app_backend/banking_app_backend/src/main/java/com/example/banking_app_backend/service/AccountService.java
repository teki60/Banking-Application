package com.example.banking_app_backend.service;

import com.example.banking_app_backend.entity.Account;
import com.example.banking_app_backend.entity.Transaction;

import java.util.List;

public interface AccountService {

    double getBalance(Long userId);

    void deposit(Long userId, double amount);

    void withdraw(Long userId, double amount);

    Account getAccountByUserId(Long userId);

    List<Transaction> getTransactionsByUserId(Long userId);
}
