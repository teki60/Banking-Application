package com.example.banking_app_backend.repository;

import com.example.banking_app_backend.entity.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByAccountId(Long userId);

    List<Transaction> findByAccountIdOrderByTimeStampDesc(Long accountId);

    List<Transaction> findByAccountIdOrderByTimeStampDesc(Long accountId, Pageable pageable);
}
