package com.example.banking_app_backend.repository;

import com.example.banking_app_backend.entity.Account;
import com.example.banking_app_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {

    Optional<Account> findByUser(User user);


}
