package com.example.banking_app_backend.repository;

import com.example.banking_app_backend.entity.Transaction;
import com.example.banking_app_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>  {
    boolean existsByUserName(String userName);

    Optional<User> findByUserName(String userName);
}
