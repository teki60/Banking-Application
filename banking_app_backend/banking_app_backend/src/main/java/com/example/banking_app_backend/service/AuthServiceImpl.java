package com.example.banking_app_backend.service;

import com.example.banking_app_backend.entity.Account;
import com.example.banking_app_backend.entity.User;
import com.example.banking_app_backend.repository.AccountRepository;
import com.example.banking_app_backend.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Data
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    @Override
    public void register(String userName, String password) {
        if(userRepository.existsByUserName(userName)){
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setCreatedAt(LocalDateTime.now());
        User savedUser = userRepository.save(user);

        Account account = new Account();
        account.setBalance(0.0);
        account.setUser(savedUser);
        accountRepository.save(account);

    }

    @Override
    public boolean login(String userName, String password) {
        return userRepository.findByUserName(userName)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }
}
