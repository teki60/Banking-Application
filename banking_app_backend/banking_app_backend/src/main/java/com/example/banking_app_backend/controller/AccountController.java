package com.example.banking_app_backend.controller;

import com.example.banking_app_backend.dto.account.*;
import com.example.banking_app_backend.entity.Account;
import com.example.banking_app_backend.entity.Transaction;
import com.example.banking_app_backend.repository.AccountRepository;
import com.example.banking_app_backend.repository.TransactionRepository;
import com.example.banking_app_backend.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    private final TransactionRepository transactionRepository;

    @GetMapping("/balance/{userId}")
    public BalanceResponse getBalance(@PathVariable Long userId){

        BalanceResponse balanceResponse = new BalanceResponse();
        double balance = accountService.getBalance(userId);
        balanceResponse.setUserId(userId);
        balanceResponse.setBalance(balance);

        return balanceResponse;
    }

    @PostMapping("/deposit")
    public DepositResponse depositAmount(@RequestBody DepositRequest depositRequest){
        Long userId = depositRequest.getUserId();
        double amount = depositRequest.getAmount();
        accountService.deposit(userId, amount);

        double balance = accountService.getBalance(userId);

        DepositResponse depositResponse = new DepositResponse();

        depositResponse.setResult(true);
        depositResponse.setMessage("Deposit successful");
        depositResponse.setNewBalance(balance);

        return depositResponse;
    }

    @PostMapping("/withdraw")
    public WithdrawResponse withdrawAmount(@RequestBody WithdrawRequest withdrawRequest){
        Long userId = withdrawRequest.getUserId();
        double amount = withdrawRequest.getAmount();

        accountService.withdraw(userId,amount);
        double newBalance = accountService.getBalance(userId);

        WithdrawResponse withdrawResponse = new WithdrawResponse();
        withdrawResponse.setResult(true);
        withdrawResponse.setMessage("Withdraw Successful");
        withdrawResponse.setNewBalance(newBalance);

        return withdrawResponse;
    }

    @GetMapping("/transactions/{userId}")
    public List<TransactionResponse> getTransactions(@PathVariable Long userId){

        Account account = accountService.getAccountByUserId(userId);
        Long accountId = account.getId();

        List<Transaction> transactionList = transactionRepository.findByAccountId(accountId);

        List<TransactionResponse> transactionResponseList = new ArrayList<>();

        for(Transaction transaction : transactionList){
            transactionResponseList.add(
                    new TransactionResponse(
                            transaction.getId(),
                            transaction.getType(),
                            transaction.getAmount(),
                            transaction.getTimeStamp()
                    )
            );
        }

        return transactionResponseList;
    }


}
