package com.example.banking_app_backend.dto.account;


import com.example.banking_app_backend.constant.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {

    private Long id;

    private Type type;

    private double amount;

    private LocalDateTime timeStamp;
}
