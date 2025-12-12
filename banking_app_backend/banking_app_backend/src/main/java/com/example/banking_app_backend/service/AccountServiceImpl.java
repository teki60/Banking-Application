package com.example.banking_app_backend.service;

import com.example.banking_app_backend.constant.Type;
import com.example.banking_app_backend.entity.Account;
import com.example.banking_app_backend.entity.Transaction;
import com.example.banking_app_backend.entity.User;
import com.example.banking_app_backend.repository.AccountRepository;
import com.example.banking_app_backend.repository.TransactionRepository;
import com.example.banking_app_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    @Override
    public double getBalance(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Invalid User"));
        Account account = accountRepository.findByUser(user).orElseThrow(()->new RuntimeException("Account does not exist"));
        return account.getBalance();
    }

    @Override
    public void deposit(Long userId, double amount) {

        if(amount <= 0) {
            throw new RuntimeException("Amount must be greater than zero");
        }

        Account account = getAccountByUserId(userId);
        account.setBalance(account.getBalance()+amount);
        accountRepository.save(account);
        saveTransaction(account, Type.DEPOSIT, amount);
    }

    @Override
    public void withdraw(Long userId, double amount) {

        if(amount <= 0) {
            throw new RuntimeException("Amount must be greater than zero");
        }

        Account account = getAccountByUserId(userId);
        if(account.getBalance()<amount){
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(account.getBalance()-amount);
        accountRepository.save(account);
        saveTransaction(account, Type.WITHDRAW, amount);
    }

    public Account getAccountByUserId(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Invalid User"));
        return accountRepository.findByUser(user).orElseThrow(()->new RuntimeException("Account does not exist"));
    }

    @Override
    public List<Transaction> getTransactionsByUserId(Long userId) {
        Account account = getAccountByUserId(userId);
        Long accountId = account.getId();
        return transactionRepository.findByAccountId(accountId);
    }

    private void saveTransaction(Account account, Type type, double amount){
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setType(type);
        transaction.setAmount(amount);
        transaction.setTimeStamp(LocalDateTime.now());
        transactionRepository.save(transaction);
    }

}
