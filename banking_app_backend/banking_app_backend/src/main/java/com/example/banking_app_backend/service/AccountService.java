package com.example.banking_app_backend.service;

public interface AccountService {

    double getBalance(Long userId);

    void deposit(Long userId, double amount);

    void withdraw(Long userId, double amount);
}
