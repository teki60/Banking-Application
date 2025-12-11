package com.example.banking_app_backend.entity;

import com.example.banking_app_backend.constant.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="account_ID",referencedColumnName = "id")
    private Account account;

    @Enumerated(EnumType.STRING)
    private Type type;

    private double amount;

    private LocalDateTime timeStamp;

}
